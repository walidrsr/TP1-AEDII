package aed.tp2;

public class ABB<T extends Comparable<T>> {
    private Nodo raiz;
    private int  cardinal;


    private class Nodo {
        T valor;
        Nodo hijo_der;
        Nodo hijo_izq;
        Nodo padre;

        Nodo(T v) {
            valor = v;
            hijo_der = null;
            hijo_izq = null;
            padre = null;
        }
    }   

    public ABB() {
        this.raiz = null;
        this.cardinal = 0;

    }

    public int cardinal() {
        return this.cardinal;
    }

    public T minimo(){
        Nodo actualMin = raiz;
        while( actualMin.hijo_izq != null){
            actualMin = actualMin.hijo_izq;
        }
       
        return  actualMin.valor;
    }

    public T maximo(){
        Nodo actualMax = raiz;
        while(actualMax.hijo_der != null){
            actualMax = actualMax.hijo_der;
        }
        return  actualMax.valor;
    }

    public void insertar(T elem){ // O(N) y promedio O(log N)
        Nodo nuevoNodo = new Nodo(elem);
        
        if (raiz== null){
            raiz = nuevoNodo;
            cardinal = 1;
            return;
        }
        Nodo actual = raiz;
        Nodo padreActual = null;
        int comparar = 0;
        
        while(actual != null){
            padreActual = actual;
            comparar = elem.compareTo(actual.valor);

            if (comparar <0){
                actual = actual.hijo_izq;
            } else if (comparar >0) {
                actual = actual.hijo_der;
            } else {
                return;
            }

        }
        nuevoNodo.padre = padreActual;

        if (comparar <0){
            padreActual.hijo_izq = nuevoNodo;
        } else {
            padreActual.hijo_der = nuevoNodo;
        }

        cardinal ++;
    }

    private Nodo buscarUsuario(int id){
        Nodo actual = this.raiz;
        while (actual != null){
            if ((actual.valor.compareTo(elem) < 0 && actual.hijo_der == null )  || (actual.valor.compareTo(elem) > 0 && actual.hijo_izq == null)  || (actual.valor.compareTo(elem) == 0)){
                break;
            }
            else if (actual.valor.compareTo(elem) < 0){
                actual = actual.der;
            }
            else{
                actual = actual.izq;
            }
        }
        return actual;
    }
}




