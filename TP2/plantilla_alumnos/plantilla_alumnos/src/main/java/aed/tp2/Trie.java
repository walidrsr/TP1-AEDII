package aed.tp2;

public class Trie<T> 
{
    public TrieNodo root;
    
    public Trie()
    {
        root = new TrieNodo();
    }

    public class TrieNodo
    {
        T valor;
        boolean fin;
        TrieNodo[] hijos;
        int cantidadHijos;

        @SuppressWarnings("unchecked")
        public TrieNodo()
        {
            hijos = new Trie.TrieNodo[255];
            fin = false;
            valor = null;
            cantidadHijos = 0;
        }
    }

    public T buscar(String nombre)
    {
        T valor = null;
        int i = 0;
        TrieNodo nodoActual = root;
        int posicionEnArray = (int)nombre.charAt(0);
        int largoNombre = nombre.length();
        while (nodoActual.hijos[posicionEnArray] != null && i < largoNombre) 
        {
            nodoActual = nodoActual.hijos[posicionEnArray];
            i++;
            posicionEnArray = (int)nombre.charAt(i);
        }
        if (nodoActual.fin == true && i == largoNombre)
        {
            valor = nodoActual.valor;
        }
        return valor;
    }
public void agregar(String nombre, T valor)
    {
        int i = 0;
        TrieNodo nodoActual = root;
        int posicionEnArray = (int)nombre.charAt(0);
        int largoNombre = nombre.length();
        //ve hasta cuanto se puede encontrar la palabra en el trie
        while (nodoActual.hijos[posicionEnArray] != null && i < largoNombre - 1) 
        {
            nodoActual = nodoActual.hijos[posicionEnArray];
            i++;
            posicionEnArray = (int)nombre.charAt(i);
        }
        nodoActual = nodoActual.hijos[posicionEnArray];
        //caso hay una promo que contiene el nombre de esta
        if(i == largoNombre -1)
        {
            nodoActual.fin = true;
            nodoActual.valor = valor;
            nodoActual.cantidadHijos ++;
        }
        //caso nueva palabra
        else
        {
            String pal = nombre.substring(i+1);
            TrieNodo nodoFinal = guardarPalabra(nodoActual, pal);
            nodoFinal.fin = true;
            nodoFinal.valor = valor;
            nodoFinal.cantidadHijos ++;
        }
    }

    public TrieNodo guardarPalabra(TrieNodo nod, String pal)
    {
        TrieNodo nodoActual = nod;

    
        for (int i = 0;i < pal.length(); i++)
        {
            TrieNodo nuevo = new TrieNodo();
            int posicionEnArray = (int)pal.charAt(i);
            nodoActual.hijos[posicionEnArray] = nuevo; 
            nodoActual.cantidadHijos ++;
            nodoActual = nuevo;
        }
        //devuelve el nodo donde finaliza la palabra
        return nodoActual;
    }
}