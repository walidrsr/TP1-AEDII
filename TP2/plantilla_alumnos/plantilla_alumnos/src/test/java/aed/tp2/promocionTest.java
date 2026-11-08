package aed.tp2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class promocionTest 
{
    public listaPromociones listaTest;

    @BeforeEach
    void setUp() {
        listaTest = new listaPromociones();
    }

    @Test 
    void verificarNoPromo()
    {
        assertEquals(1.0, listaTest.buscarPromo("NoPromo").factor);
        assertEquals(0.0, listaTest.buscarPromo("NoPromo").gastadas);
    }

    @Test 
    void creaPromocionBasica()
    {
        promocion promoNueva = new promocion(1.5);
        listaTest.agregarPromo("promoTest1", promoNueva);
        promocion promoDesdeLista = listaTest.buscarPromo("promoTest1");
        assertEquals(1.5, promoDesdeLista.factor);
        assertEquals(0.0, promoDesdeLista.gastadas);
    }

    @Test 
    void  creaPromocionConTope()
    {
        promocion promoNuevaConTope = new promocion(2.0, 5999);
        listaTest.agregarPromo("promoTest2", promoNuevaConTope);
        promocion promoConTopeDesdeLista = listaTest.buscarPromo("promoTest2");
        assertEquals(2.0, promoConTopeDesdeLista.factor);
        assertEquals(5999, promoConTopeDesdeLista.tope);
        assertEquals(0.0, promoConTopeDesdeLista.gastadas);
    }

    @Test
    void creoPromoConTopeYlaUso()
    {
        promocion promoNuevaConTope = new promocion(1.5, 3000);
        listaTest.agregarPromo("promoTest3", promoNuevaConTope);
        promocion promoConTopeDesdeLista = listaTest.buscarPromo("promoTest3");

        assertEquals(1.5, promoConTopeDesdeLista.factor);
        assertEquals(3000, promoConTopeDesdeLista.tope);
        assertEquals(0.0, promoConTopeDesdeLista.gastadas);

        listaTest.actualizarPromo("promoTest3",promoConTopeDesdeLista, 2999);
        assertEquals(2999, promoConTopeDesdeLista.gastadas);
    }

    @Test 
    void usarPromocionMultipleVeces()
    {
        promocion promoNueva = new promocion(1);
        listaTest.agregarPromo("promoTest4", promoNueva);
        promocion promoDesdeLista = listaTest.buscarPromo("promoTest4");     

        listaTest.actualizarPromo("promoTest4",promoDesdeLista, 25);
        listaTest.actualizarPromo("promoTest4",promoDesdeLista, 2);
        listaTest.actualizarPromo("promoTest4",promoDesdeLista, 5);
        listaTest.actualizarPromo("promoTest4",promoDesdeLista, 3);
        listaTest.actualizarPromo("promoTest4",promoDesdeLista, 18);

        promocion promoDesdeListaActualizada = listaTest.buscarPromo("promoTest4");  

        assertEquals(53,promoDesdeListaActualizada.gastadas);
    }

    @Test 
    void crearPromoParaEliminar()
    {
        promocion promoNueva = new promocion(2);
        assertEquals(1,listaTest.listadoPromos.root.cantidadHijos);
        listaTest.agregarPromo("promoAEliminar", promoNueva);
        assertEquals(2,listaTest.listadoPromos.root.cantidadHijos);
        listaTest.eliminarPromo("promoAEliminar");    
        assertEquals(1,listaTest.listadoPromos.root.cantidadHijos);
    }

    @Test 
    void crearPromoUsarlaYSeAutoelimina()
    {
        promocion promoNueva = new promocion(2,600);
        listaTest.agregarPromo("promoAEliminar", promoNueva);
        promocion promoNuevaDesdeLista = listaTest.buscarPromo("promoAEliminar");
        assertEquals(2, promoNuevaDesdeLista.factor);
        assertEquals(600, promoNuevaDesdeLista.tope);
        assertEquals(0.0, promoNuevaDesdeLista.gastadas);
        listaTest.actualizarPromo("promoAEliminar",promoNuevaDesdeLista, 200);
        assertEquals(200.0, promoNuevaDesdeLista.gastadas);
        assertEquals(2,listaTest.listadoPromos.root.cantidadHijos);
        listaTest.actualizarPromo("promoAEliminar",promoNuevaDesdeLista, 400);
        assertEquals(1,listaTest.listadoPromos.root.cantidadHijos);
    }

    @Test 
    void creaPromocionesConMismaInicial()
    {
        promocion promoNueva1 = new promocion(1.5);
        listaTest.agregarPromo("pA1", promoNueva1);
        assertEquals(2,listaTest.listadoPromos.root.cantidadHijos);
        promocion promoNueva2 = new promocion(3);
        listaTest.agregarPromo("pA2", promoNueva2);
        assertEquals(2,listaTest.listadoPromos.root.cantidadHijos);
    }

    @Test 
    void creaPromoQueEsPrefijo()
    {
        promocion promoNueva = new promocion(1.5);
        listaTest.agregarPromo("A112", promoNueva);
        assertEquals(2,listaTest.listadoPromos.root.cantidadHijos);
        promocion promoPrefijo = new promocion(3);
        listaTest.agregarPromo("A11", promoPrefijo);
        assertEquals(2,listaTest.listadoPromos.root.cantidadHijos);

        promocion promoNuevaDesdeLista = listaTest.buscarPromo("A112");
        assertEquals(1.5, promoNuevaDesdeLista.factor);
        promocion promoPrefijoDesdeLista = listaTest.buscarPromo("A11");
        assertEquals(3, promoPrefijoDesdeLista.factor);
    }
}