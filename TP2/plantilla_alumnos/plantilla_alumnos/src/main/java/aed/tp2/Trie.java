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
        while (nodoActual.hijos[posicionEnArray] != null && i < largoNombre - 1) 
        {
            nodoActual = nodoActual.hijos[posicionEnArray];
            i++;
            posicionEnArray = (int)nombre.charAt(i);
        }
        if (nodoActual.hijos[posicionEnArray] != null){
            nodoActual = nodoActual.hijos[posicionEnArray];
            if (nodoActual.fin == true && i == largoNombre -1)
            {
                valor = nodoActual.valor;
            }
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

        //caso hay una promo que contiene el nombre de esta
        if(i == largoNombre -1)
        {
            if (nodoActual.hijos[posicionEnArray] == null)
            {
                nodoActual.hijos[posicionEnArray] = new TrieNodo();
            }
            else
            {
                nodoActual = nodoActual.hijos[posicionEnArray];
            }
            nodoActual.fin = true;
            nodoActual.valor = valor;
            nodoActual.cantidadHijos ++;
        }
        //caso nueva palabra
        else
        {
            TrieNodo primerLetra = new TrieNodo();
            nodoActual.cantidadHijos++;
            nodoActual.hijos[posicionEnArray] = primerLetra;
            nodoActual = primerLetra;

            String pal = nombre.substring(i+1);
            TrieNodo nodoFinal = guardarPalabra(nodoActual, pal);
            nodoFinal.fin = true;
            nodoFinal.valor = valor;       
        }
    }

    public TrieNodo guardarPalabra(TrieNodo nod, String pal)
    {   
        TrieNodo nodoActual = nod;

        //recorre la palabra y a su vez el Trie
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

    public void eliminar(String nombre)
    {
        TrieNodo nodoActual = root;
        TrieNodo nodoPadre = root;
        int posicionPadre = 0;
        int i = 0;
        int posicionEnArray = (int)nombre.charAt(i);
        int largoNombre = nombre.length();
        while (i < largoNombre - 1)
        {
            if (nodoActual.cantidadHijos > 1 || nodoActual.fin == true)
            {
                nodoPadre = nodoActual;
                posicionPadre = posicionEnArray;
            }
            nodoActual = nodoActual.hijos[posicionEnArray];
            i++;
            posicionEnArray = (int)nombre.charAt(i);
        }
        nodoActual = nodoActual.hijos[posicionEnArray];
        if(nodoActual.cantidadHijos == 0)
        {
            nodoPadre.hijos[posicionPadre] = null;
            nodoPadre.cantidadHijos --;
        }
        else 
        {
            nodoActual.fin = false;
            nodoActual.valor = null;
        }
    }
}