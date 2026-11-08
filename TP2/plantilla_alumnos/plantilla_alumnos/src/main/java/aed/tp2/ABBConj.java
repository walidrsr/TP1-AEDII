package aed.tp2;

public class ABBConj<T>
{
    private Nodo raiz;
    private int cardinal;


    private class Nodo
    {
        T valor;
        int id;
        Nodo hijo_der;
        Nodo hijo_izq;
        Nodo padre;

        Nodo(int id, T v)
        {
            this.valor = v;
            this.id = id;
            this.hijo_der = null;
            this.hijo_izq = null;
            this.padre = null;
        }
    }   

    public ABBConj()
    {
        this.raiz = null;
        this.cardinal = 0;
    }

    public int cardinal()
    {
        return this.cardinal;
    }

    public void insertar(int id, T elem)
    { // O(U) peor y promedio O(log U)
        Nodo nuevoNodo = new Nodo(id, elem);
        
        if (raiz == null)
            {
            raiz = nuevoNodo;
            cardinal = 1;
            return;
        }
        Nodo actual = raiz;
        Nodo padreActual = null;
        int comparar = 0;
        
        while(actual != null)
            {
            padreActual = actual;
            comparar = Integer.compare(id, actual.id);

            if (comparar<0)
                {
                actual = actual.hijo_izq;
            }
            else if (comparar >0)
                {
                actual = actual.hijo_der;
            }
            else
                {
                return;
            }

        }
        nuevoNodo.padre = padreActual;

        if (comparar <0)
            {
            padreActual.hijo_izq = nuevoNodo;
        } else
            {
            padreActual.hijo_der = nuevoNodo;
        }

        cardinal ++;
    }

    public T buscar(int id) // O(U) peor y promedio O(log U)
    {
        Nodo actual = this.raiz;
        while (actual != null)
            {
            int comparar = Integer.compare(id, actual.id);
            if (comparar == 0)
                {
                return actual.valor;
            }
            else if (comparar < 0)
                {
                actual = actual.hijo_izq;
            }
            else
                {
                actual = actual.hijo_der;
            }
        }
        return null;
    }

    private Nodo maximaId()
    {
        Nodo nodo = this.raiz;
        if (nodo == null)
        {
            return null;
        }
        while (nodo.hijo_der != null)
        {
            nodo = nodo.hijo_der;
        }
        return nodo;
    }

    private Nodo menorId() // O(log U) en caso promedio y O(U) en el peor caso
    {
        Nodo nodo = this.raiz;
        if (nodo == null) // caso vacio
        {
            return null;
        }
        while (nodo.hijo_izq != null) {
            nodo = nodo.hijo_izq;
        }
        return nodo;
    }

    public ABB_Iterador iterador()
        {
        return new ABB_Iterador();
    }

    public class ABB_Iterador
    {
        private Nodo actual = menorId();

        public boolean haySiguiente()
        {            
            return actual != null;
        }
    
        public T siguiente()
        {
            T res = this.actual.valor;
            Nodo sucesor = null;
            if (this.actual.hijo_der != null) /* si tiene subárbol derecho */
                {
                sucesor = this.actual.hijo_der;
                while (sucesor.hijo_izq != null)
                    {
                    sucesor = sucesor.hijo_izq;
                }
            }
            else if (this.actual != maximaId()) /* busco primer ancestro derecho */
                {
                sucesor = this.actual.padre;
                while (Integer.compare(sucesor.id, actual.id) > 0 && sucesor.padre != null)
                    {
                    sucesor = sucesor.padre;
                } 
            }
            this.actual = sucesor;
            return res;
        }
    }
}