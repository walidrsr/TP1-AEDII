package aed.tp2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MaxHeapTests {

    @Test
    void insertar_y_obtener_maximo() {
        MaxHeap<Integer> heap = new MaxHeap<>();

        heap.insertar(10);
        heap.insertar(20);
        heap.insertar(5);

        // El elemento máximo debería ser 20
        assertEquals(20, heap.getMaximo());
        assertEquals(3, heap.cardinal());
    }

}