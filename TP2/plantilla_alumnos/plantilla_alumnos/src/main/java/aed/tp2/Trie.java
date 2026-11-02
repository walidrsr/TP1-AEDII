package aed.tp2;

public class Trie 
{
    public TrieNodo root;
    
    public class TrieNodo
    {
        boolean fin;
        Double tope;
        Double factor;
        Double gastadas;
        TrieNodo[] hijos;
        // [a b c d e f g h i j l m nñ o p q r s t u v w k x y z *_-:.
        public TrieNodo()
        {
            hijos = new TrieNodo[27]; 
            fin = false;
            tope = null;
            factor = null;
            gastadas = null;
        }

        public void darValor(double fact)
        {
            factor = fact;
        }

        public void darValorTope(double fact, double top)
        {
            factor = fact;
            tope = top;
        }
    }

    public Trie()
    {
        root = null;
    }

    // array de 27 lugares, 'a' vale 97 'z' vale 122
    public TrieNodo buscarNodoFinDeNombre(String nombrePromo)
    {
        TrieNodo nodo = null;
        if(root != null)
        {
            String palabra = normalizarPalabra(nombrePromo);

            int i = 0;
            TrieNodo nodoActual = root;
            int posicionEnArray = (int)palabra.charAt(0) - (int)'a';
            if (posicionEnArray > 25)
            {
                posicionEnArray = 26;
            }
            //ve hasta cuanto se puede encontrar la palabra en el trie

            while (nodoActual.hijos[posicionEnArray] != null && i < palabra.length()) 
            {
                nodoActual = nodoActual.hijos[posicionEnArray];
                i++;
                posicionEnArray = (int)palabra.charAt(i) - (int)'a';
                if (posicionEnArray > 25)
                {
                    posicionEnArray = 26; 
                }
            }
            if (nodoActual.fin == true && i == palabra.length())
            {
                nodo = nodoActual;
            }
        }
        return nodo;
    }

    public void agregarPromo(String nombrePromo, double fact, double top)
    {
        //pasa a minúscula y quita acentos/ñ
        String palabra = normalizarPalabra(nombrePromo);

        //se fija si es la primera promo (de NoPromo se encarga AirMiles)
        if (root == null)
        {
            root = new TrieNodo();
            TrieNodo nuevoNodo = new TrieNodo();

            //lo primero es una letra válida o no?
            int posicionEnArray = (int)palabra.charAt(0) - (int)'a';
            if (posicionEnArray > 25)
            {
                root.hijos[26] = nuevoNodo; 
            }
            else
            {
                root.hijos[posicionEnArray] = nuevoNodo; 
            }

            // al array hijos de la raiz en la posicion correspondiente a la primera letra
            // le hace apuntar a nuevoNodo
            String pal = palabra.substring(1);
            TrieNodo nodoFinal = guardarPalabra(nuevoNodo, pal);
            nodoFinal.fin = true;
            nodoFinal.factor = fact;
            nodoFinal.tope = top;           
        }
        else
        {
            int i = 0;
            TrieNodo nodoActual = root;
            int posicionEnArray = (int)palabra.charAt(0) - (int)'a';

            if (posicionEnArray > 25)
            {
                posicionEnArray = 26;
            }
            //ve hasta cuanto se puede encontrar la palabra en el trie
            while (nodoActual.hijos[posicionEnArray] != null && i < palabra.length()) 
            {
                nodoActual = nodoActual.hijos[posicionEnArray];
                i++;
                posicionEnArray = (int)palabra.charAt(i) - (int)'a';
                if (posicionEnArray > 25)
                {
                    posicionEnArray = 26; 
                }
            }

            //caso hay una promo que contiene el nombre de esta
            if(i == palabra.length() -1)
            {
                nodoActual.fin = true;
                nodoActual.factor = fact;
                nodoActual.tope = top;   
            }
            //caso nueva palabra
            else
            {
                String pal = palabra.substring(i);
                TrieNodo nodoFinal = guardarPalabra(nodoActual, pal);
                nodoFinal.fin = true;
                nodoFinal.factor = fact;
                nodoFinal.tope = top;           
            }
        }

    }

    public TrieNodo guardarPalabra(TrieNodo nod, String pal)
    {   
        TrieNodo nodoActual = nod;

        //recorre la palabra y a su vez el Trie
        for (int i = 0;i < pal.length(); i++)
        {
            TrieNodo nuevo = new TrieNodo();
            int posicionEnArray = (int)pal.charAt(i) - (int)'a';
            //lo primero es una letra válida o no?
            if (posicionEnArray > 25)
            {
                nodoActual.hijos[26] = nuevo; 
            }
            else
            {
                nodoActual.hijos[posicionEnArray] = nuevo; 
            }
            nodoActual = nuevo;
        }
        //devuelve el nodo donde finaliza la palabra
        return nodoActual;
    }

    public String normalizarPalabra(String pal)
    {
        pal.toLowerCase();
        pal.replace('á', 'a');
        pal.replace('é', 'e');
        pal.replace('í', 'i');
        pal.replace('ó', 'o');
        pal.replace('ú', 'u');
        pal.replace('ñ', 'n');

        return pal;
    }
}