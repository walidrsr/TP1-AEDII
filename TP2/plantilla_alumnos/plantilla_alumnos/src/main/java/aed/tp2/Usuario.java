
package aed.tp2;

import java.util.Comparator;

public class Usuario
    {
        int idUsuario;
        double millasTotales;
        double millasDisponibles;
        double millasCanjeadas;
        Usuario usuarioExclusivo; // 3 estados -> null, id = -1 o id = parTransferidor
 
        public Usuario(int id, boolean referido)
        {
            if (referido)
            {
                millasDisponibles = 500;
                millasTotales = 500;
            }
            else
            {
                millasDisponibles = 0;
                millasTotales = 0;
            }
            idUsuario = id;
            millasCanjeadas = 0;
            usuarioExclusivo = null;
        }

        public double saldo()
        {
            return millasDisponibles;
        }

        public String categoria()
        {
            String categoria;
            if (millasTotales < 10000)
            {
                categoria = "Bronce";
            }
            else if (millasTotales < 50000)
            {
                categoria = "Plata";
            }
            else if (millasTotales < 1000000)
            {
                categoria = "Oro";
            }
            else
            {
                categoria = "Platino";
            }
            return categoria;
        }

        public double multiplicadorCategoria()
        {
            double multiplicador = 1.0;
            String categoria = this.categoria();
            if (categoria == "Plata")
            {
                multiplicador = 1.5;
            }
            else if (categoria == "Oro")
            {
                multiplicador = 2.0;
            }
            else if (categoria == "Platino")
            {
                multiplicador = 0.0;
            }
            return multiplicador;
        }

        public void ganarMillasPorVuelo(double distancia, double factorPromo)
        {
            double multiplicador = this.multiplicadorCategoria();
            double millasGanadas = distancia * multiplicador * factorPromo;
            millasDisponibles += millasGanadas;
            millasTotales += millasGanadas;
        }

        public void canjear(double distancia)
        {
            millasDisponibles -= distancia;
            millasCanjeadas += distancia;
        }

        public void darMillas(double cantidad, int idOtroUsuario)
        {
            millasDisponibles -= cantidad;
            millasCanjeadas += cantidad;
            /*
            if (idExclusivo == 0)
            {
                idExclusivo = idOtroUsuario;
            }
            else if (idExclusivo != idOtroUsuario)
            {
                idExclusivo = -1;
            }
                */
        }

        public void recibirMillas(double cantidad, int idOtroUsuario)
        {
            millasDisponibles += cantidad;
            /*
            if (idExclusivo == 0)
            {
                idExclusivo = idOtroUsuario;
            }
            else if (idExclusivo != idOtroUsuario)
            {
                idExclusivo = -1;
            }
                */
        }
    
        public void cambioUsuarioExclusivo(Usuario otroUsuario)
        {
            Usuario noPuedeSerTransferidorExclusivo = new Usuario(-1,false);
            boolean ambosSinTransferencias = this.usuarioExclusivo == null && otroUsuario.usuarioExclusivo == null;
            boolean yaSonTransferidores = this.usuarioExclusivo == otroUsuario && otroUsuario.usuarioExclusivo == this;
            if (ambosSinTransferencias)
            {
                this.usuarioExclusivo = otroUsuario;
                otroUsuario.usuarioExclusivo = this;
            }
            else if (yaSonTransferidores)
            {
                return;
            }
            else
            {
                if (this.usuarioExclusivo != null) // cambio el usuarioExlusivo del usuarioExclusivo pq ese tampoco peude ser par exclusivo
                {
                    this.usuarioExclusivo.usuarioExclusivo = noPuedeSerTransferidorExclusivo;
                }
                if (otroUsuario.usuarioExclusivo != null) // cambio el usuarioExlusivo del usuarioExclusivo pq ese tampoco peude ser par exclusivo
                {
                    otroUsuario.usuarioExclusivo.usuarioExclusivo = noPuedeSerTransferidorExclusivo;
                }
                this.usuarioExclusivo = noPuedeSerTransferidorExclusivo;
                otroUsuario.usuarioExclusivo = noPuedeSerTransferidorExclusivo;
            }
        }

        public static class PorMillasDisponibles implements Comparator<Usuario> // comparacion para el heap
        {
            @Override
            public int compare(Usuario a, Usuario b) {
                return Double.compare(a.millasDisponibles, b.millasDisponibles);
            }
        }
    }