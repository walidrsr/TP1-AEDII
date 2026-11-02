package aed.tp2;

public class ABBConj {
    private Nodo raiz;
    private int cardinal;


    private class Nodo {
        Usuario valor;
        Nodo hijo_der;
        Nodo hijo_izq;
        Nodo padre;

        Nodo(Usuario v) {
            valor = v;
            hijo_der = null;
            hijo_izq = null;
            padre = null;
        }
    }   
/*
    private class HandleAM implements Handle<T> {
        private Nodo nodo;

        private HandleAM(Nodo n){
            this.nodo = n;
        }
        public Nodo valor() {
        }
        public void eliminar() {
        }
        public void modificar(Usuario u) {
        }
        public boolean equals(Handle<Usuario> h) {
        }
    }  
*/
    public ABBConj() {
        this.raiz = null;
        this.cardinal = 0;

    }

    public int cardinal() {
        return this.cardinal;
    }

    public void insertar(Usuario elem){ // O(U) peor y promedio O(log U)
        Nodo nuevoNodo = new Nodo(elem);
        
        if (raiz == null){
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

            if (comparar<0){
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

    public Usuario buscarUsuario(int id){ // O(U) peor y promedio O(log U)
        Nodo actual = this.raiz;
        while (actual != null){
            if ((actual.valor.idUsuario < id && actual.hijo_der == null)  || (actual.valor.idUsuario > id && actual.hijo_izq == null)  || (actual.valor.idUsuario == id)){
                break;
            }
            else if (actual.valor.idUsuario < id){
                actual = actual.hijo_der;
            }
            else{
                actual = actual.hijo_izq;
            }
        }
        return actual.valor;
    }
}
