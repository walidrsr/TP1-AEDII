package aed.tp2;

public class ABB<T extends Comparable<T>> {
    private Nodo raiz;
    private int cardinal;


    private class Nodo implements Handle<T> {
        public T valor;
        public Nodo hijo_der;
        public Nodo hijo_izq;
        public Nodo padre;

        Nodo(T v) {
            valor = v;
            hijo_der = null;
            hijo_izq = null;
            padre = null;
        }

        public T valor() {
            return this.valor;
        }
    
        public void modificar(T v) {
            this.valor = v;
        }

        public void eliminar() {
        }

        public boolean equals(Handle<T> h) {
            if (h == null) {
                return false;
            }
            return this.valor.equals(h.valor());
        }
    } 

    public ABB() {
        this.raiz = null;
        this.cardinal = 0;
    }

    public int cardinal() {
        return this.cardinal;
    }

    public boolean pertenece(T elem){
        Nodo actual = raiz;
        while(actual != null){
            int comparar = elem.compareTo(actual.valor);
            if(comparar == 0){
                return true;
            } else if (comparar <0){ 
                actual = actual.hijo_izq;
            } else {
                actual = actual.hijo_der;
            }
        }
        return false;
    }

    public T minimo() {
        if (raiz == null) return null;
        Nodo actualMin = raiz;
        while (actualMin.hijo_izq != null) {
            actualMin = actualMin.hijo_izq;
        }
        return actualMin.valor;
    }

    public T maximo() {
        if (raiz == null) return null;
        Nodo actualMax = raiz;
        while (actualMax.hijo_der != null) {
            actualMax = actualMax.hijo_der;
        }
        return actualMax.valor;
    }

    public Handle<T> insertarConHandle(T elem) {
        Nodo nuevoNodo = new Nodo(elem);
        
        if (raiz == null) {
            raiz = nuevoNodo;
            cardinal = 1;
            return nuevoNodo;
        }
        Nodo actual = raiz;
        Nodo padreActual = null;
        int comparar = 0;
        
        while (actual != null) {
            padreActual = actual;
            comparar = elem.compareTo(actual.valor);

            if (comparar < 0) {
                actual = actual.hijo_izq;
            } else if (comparar > 0) {
                actual = actual.hijo_der;
            } else {
                return actual; // Ya existe
            }
        }
        nuevoNodo.padre = padreActual;

        if (comparar < 0) {
            padreActual.hijo_izq = nuevoNodo;
        } else {
            padreActual.hijo_der = nuevoNodo;
        }

        cardinal++;
        return nuevoNodo;
    }
    
    public void insertar(T elem) {
        insertarConHandle(elem);
    }
  
    public T buscarUsuario(T elem) {
    Nodo actual = this.raiz;
    while (actual != null) {
        int cmp = elem.compareTo(actual.valor);
        if (cmp == 0) return actual.valor;
        else if (cmp < 0) actual = actual.hijo_izq;
        else actual = actual.hijo_der;
    }
    return null;
    }

}
