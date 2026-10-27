
package aed.tp2;

public class Usuario implements Comparable<Usuario>
    {
        int idUsuario;
        double millasTotales;
        double millasDisponibles;
        double millasCanjeadas;
        int idExclusivo;
        //T historialVuelos;
        //T historialTransacciones;
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
            idExclusivo = 0;
            //historialVuelos = vacio
            //historialTransacciones = vacio
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
            else if (millasTotales < 100000)
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
            // ACTUALIZAR HISTORIAL VUELOS
        }

        public void canjear(double distancia)
        {
            millasDisponibles -= distancia;
            millasCanjeadas += distancia;
        }

        public void darMillas(double cantidad, int idOtroUsuario)
        {
            millasDisponibles -= cantidad;
            if (idExclusivo == 0)
            {
                idExclusivo = idOtroUsuario;
            }
            else if (idExclusivo != idOtroUsuario)
            {
                idExclusivo = -1;
            }
            // ACTUALIZAR HISTORIAL TRANSACCIONES
        }

        public void recibirMillas(double cantidad, int idOtroUsuario)
        {
            millasDisponibles += cantidad;
            if (idExclusivo == 0)
            {
                idExclusivo = idOtroUsuario;
            }
            else if (idExclusivo != idOtroUsuario)
            {
                idExclusivo = -1;
            }
            // ACTUALIZAR HISTORIAL TRANSACCIONES
        }
    
        public boolean esExclusivo() // 
        {
            boolean exclusivo = true;
            // recorre el historial y se fija que todos tengan el mismo usuario 
            return exclusivo;
        }



        public int transferidorUnico()
        {
            int id;
            if (esExclusivo())
            {
                id = 0; // el id del otro usuario
            }
            else
            {
                id = -1;
            }
            return id;
        }
        @Override 
        public int compareTo(Usuario OtroUsuario){//ID
            return Integer.compare(this.idUsuario,OtroUsuario.idUsuario);
        } 




    }