package aed.tp2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import aed.tp2.Usuario.PorMillasDisponibles;

public class MaxHeapTests {

    @Test
    void insertar_y_obtener_maximo() {
        MaxHeap<Usuario> heap = new MaxHeap<Usuario>(new Usuario.PorMillasDisponibles());

        Usuario a = new Usuario(10, false);
        Usuario b = new Usuario(20, false);
        Usuario c = new Usuario(5, false);
        a.millasDisponibles = 200;
        b.millasDisponibles = 100;
        c.millasDisponibles = 50;
        heap.insertar(a);
        heap.insertar(b);
        heap.insertar(c);

        // El elemento máximo debería ser a
        assertEquals(a, heap.maximo());
        assertEquals(3, heap.cardinal());
    }

    @Test 
    void extraerMaxTest() {
        MaxHeap<Usuario> heap = new MaxHeap<Usuario>(new Usuario.PorMillasDisponibles());

        Usuario a = new Usuario(10, false);
        Usuario b = new Usuario(20, false);
        Usuario c = new Usuario(5, false);
        a.millasDisponibles = 200;
        b.millasDisponibles = 100;
        c.millasDisponibles = 50;
        heap.insertar(a);
        heap.insertar(b);
        heap.insertar(c);

    
        assertEquals(a, heap.maximo());
        assertEquals(3, heap.cardinal());
        assertEquals(a, heap.extraerMax().valor());
        assertEquals(2, heap.cardinal());
    }

    @Test
    void insertarYExtraerMaximoUsuarios() {
        MaxHeap<Usuario> heap = new MaxHeap<Usuario>(new Usuario.PorMillasDisponibles());


        Usuario u1 = new Usuario(1, false);
        u1.ganarMillasPorVuelo(1000, 1.0); // 1000 millas

        Usuario u2 = new Usuario(2, false);
        u2.ganarMillasPorVuelo(5000, 1.0); // 5000 millas

        Usuario u3 = new Usuario(3, false);
        u3.ganarMillasPorVuelo(3000, 1.0); // 3000 millas

        heap.insertar(u1);
        heap.insertar(u2);
        heap.insertar(u3);

        // El de mayor millas disponibles debe salir primero (u2 con 5000)
        assertEquals(u2, heap.extraerMax().valor());
        // Luego u3 (3000)
        assertEquals(u3, heap.extraerMax().valor());
        // Por último u1 (1000)
        assertEquals(u1, heap.extraerMax().valor());
        assertNull(heap.extraerMax());
    }



}