package aed.tp2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ABBConjUsuarioTest {

    @Test
    void testArbolUsuariosVacio() {
        ABBConj<Usuario> arbol = new ABBConj<>();
        assertEquals(0, arbol.cardinal());
        assertNull(arbol.buscar(1));
        
        ABBConj<Usuario>.ABB_Iterador iter = arbol.iterador();
        assertFalse(iter.haySiguiente());
    }

    @Test
    void testInsertarYBuscarUsuarios() {
        ABBConj<Usuario> arbol = new ABBConj<>();

        Usuario u1 = new Usuario(10, false);
        Usuario u2 = new Usuario(5, true);
        Usuario u3 = new Usuario(20, false);

        arbol.insertar(u1.idUsuario, u1);
        arbol.insertar(u2.idUsuario, u2);
        arbol.insertar(u3.idUsuario, u3);

        assertEquals(3, arbol.cardinal());

        // Buscar por ID y verificar que sea el usuario correcto
        Usuario encontrado = arbol.buscar(5);
        assertNotNull(encontrado);
        assertEquals(5, encontrado.idUsuario);
        assertEquals(500.0, encontrado.saldo()); // Usuario referido arranca con 500

        assertNull(arbol.buscar(99)); // ID inexistente
    }

    @Test
    void testInsertarUsuarioDuplicadoNoModifica() {
        ABBConj<Usuario> arbol = new ABBConj<>();

        Usuario uOriginal = new Usuario(1, false);
        uOriginal.ganarMillasPorVuelo(1000, 1.0); // 1000 millas

        arbol.insertar(uOriginal.idUsuario, uOriginal);
        assertEquals(1, arbol.cardinal());

        // Intentar insertar otro usuario con el mismo ID (1)
        Usuario uDuplicado = new Usuario(1, true); // con 500 millas iniciales
        arbol.insertar(uDuplicado.idUsuario, uDuplicado);

        assertEquals(1, arbol.cardinal());
        // Debe conservar el valor del primero insertado
        assertEquals(1000.0, arbol.buscar(1).millasTotales);
    }

    @Test
    void testIteradorUsuariosOrdenadoPorId() {
        ABBConj<Usuario> arbol = new ABBConj<>();

        // Insertar desordenados por ID
        Usuario u50 = new Usuario(50, false);
        Usuario u10 = new Usuario(10, false);
        Usuario u30 = new Usuario(30, false);
        Usuario u5  = new Usuario(5, false);

        arbol.insertar(u50.idUsuario, u50);
        arbol.insertar(u10.idUsuario, u10);
        arbol.insertar(u30.idUsuario, u30);
        arbol.insertar(u5.idUsuario, u5);

        ABBConj<Usuario>.ABB_Iterador iter = arbol.iterador();

        // Debe recorrer de menor a mayor ID (5, 10, 30, 50)
        assertTrue(iter.haySiguiente());
        assertEquals(5, iter.siguiente().idUsuario);

        assertTrue(iter.haySiguiente());
        assertEquals(10, iter.siguiente().idUsuario);

        assertTrue(iter.haySiguiente());
        assertEquals(30, iter.siguiente().idUsuario);

        assertTrue(iter.haySiguiente());
        assertEquals(50, iter.siguiente().idUsuario);

        assertFalse(iter.haySiguiente());
    }
}