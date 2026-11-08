package aed.tp2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class usuarioTest {
    @Test 
    void usuarioNoReferido(){
        Usuario usuario = new Usuario(0, false);
        assertEquals(0, usuario.millasDisponibles);
        assertEquals(0, usuario.millasTotales);
        assertEquals(0, usuario.millasCanjeadas);
        assertEquals(null, usuario.usuarioExclusivo);
        assertEquals("Bronce", usuario.categoria());
        assertEquals(0, usuario.saldo());
        
    }
    @Test 
    void usuarioReferido(){
        Usuario usuario = new Usuario(1, true);
        assertEquals(500, usuario.millasDisponibles);
        assertEquals(500, usuario.millasTotales);
        assertEquals(0, usuario.millasCanjeadas);
        assertEquals(null, usuario.usuarioExclusivo);
        assertEquals("Bronce", usuario.categoria());
        assertEquals(500, usuario.saldo());
    }
  
    @Test 
    void categoriaYmultiplicadores(){
        Usuario u = new Usuario(3, false);
        // caso BRONCE
        u.millasTotales = 9999;
        assertEquals("Bronce", u.categoria());
        assertEquals(1.0, u.multiplicadorCategoria());
        
        // caso PLATA
         u.millasTotales = 10000;
        assertEquals("Plata", u.categoria());
        assertEquals(1.5, u.multiplicadorCategoria());

        // caso ORO
        u.millasTotales = 50001;
        assertEquals("Oro", u.categoria());
        assertEquals(2.0, u.multiplicadorCategoria());
        
        u.millasTotales = 999999;  //// hay un error. platino /= oro
        assertEquals("Oro", u.categoria());
        assertEquals(2.0, u.multiplicadorCategoria());

        // caso PLATINO
        u.millasTotales = 1000000;
        assertEquals("Platino", u.categoria());
        assertEquals(0.0, u.multiplicadorCategoria());

         u.millasTotales = 1254141;
        assertEquals("Platino", u.categoria());
        assertEquals(0.0, u.multiplicadorCategoria());
    }

    @Test
    void cambioDeCategoria(){
        Usuario u = new Usuario(3, true);
        assertEquals("Bronce", u.categoria());
        // totales=disponibles= 500
        // vuela varias veces -> ganaMillas -> cambiaDeCategoria


        u.ganarMillasPorVuelo(5.0,5.0);
        u.ganarMillasPorVuelo(2500,4.0);
       
        assertEquals("Plata", u.categoria());
        assertEquals(1.5, u.multiplicadorCategoria());

        u.ganarMillasPorVuelo(25000, 1.5);
        assertEquals("Oro", u.categoria());
        assertEquals(2.0, u.multiplicadorCategoria());

    }
    
    




}