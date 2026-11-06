package aed.tp2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TrieTests {

    @Test
    void testInsertarYBuscarPalabras() {
        Trie<Integer> trie = new Trie<>();

        trie.agregar("promo1", 1);
        trie.agregar("Miami", 2);
        trie.agregar("Europa2023", 3);

        // Verificar que las palabras insertadas existan
        assertEquals(1, trie.buscar("promo1"));
        assertEquals(2, trie.buscar("Miami"));
        assertEquals(3, trie.buscar("Europa2023"));

        assertEquals(null, trie.buscar("promo1"));
        
    
    }
    
}
