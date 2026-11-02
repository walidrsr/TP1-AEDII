package aed.tp2;

import java.util.ArrayList;

public class MaxHeap { // la prioridad es millasDisponibles
    private ArrayList<Usuario> elementos;

    public MaxHeap (){
        this.elementos = new ArrayList<>();
    }

    private int IndicePadre(int i){
        return Math.floorDiv(i-1,2);
    }

    private int IndiceHijoIzq(int i){
        return (2*i) +1;
    }
    
    private int IndiceHijoDer(int i){
        return (2*i) +2;
    }

    private void intercambiar(int hijo, int padre) { // necesariamente tiene que haber indices hijo y padre validos
        Usuario valorhijo = elementos.get(hijo);
        elementos.set(hijo,elementos.get(padre));
        elementos.set(padre, valorhijo);
    }

    private void siftUp(int i) {
        while (i != 0 && elementos.get(IndicePadre(i)).millasDisponibles < elementos.get(i).millasDisponibles) { // mientars que hijo > padre y no sea la raiz
            intercambiar(i, IndicePadre(i));
            i = IndicePadre(i);
        }
    }

    private void siftDown() {
        if (elementos.size() <= 1) {
            if (!elementos.isEmpty()) {
                elementos.remove(0);
            }
            return;
        }
        elementos.set(0, elementos.getLast()); // intercambio la raiz con el ultimo
        elementos.remove(elementos.size()-1); // saco la raiz
        int i = 0;
       
        while (true) {
            if (IndiceHijoIzq(i) >= elementos.size()) {
                break; // si no hay hijo izq, no hay hijo
            }

            int hijoMayor = IndiceHijoIzq(i);
            if (IndiceHijoDer(i) < elementos.size() && elementos.get(IndiceHijoDer(i)).millasDisponibles > elementos.get(IndiceHijoIzq(i)).millasDisponibles) {
                hijoMayor = IndiceHijoDer(i);
            }

            if (elementos.get(i).millasDisponibles >= elementos.get(hijoMayor).millasDisponibles) {
                break; // entonces ningun hijo es mayor que el actual
            }

            intercambiar(i, hijoMayor);
            i = hijoMayor;
        }
    }
    
    public void insertar(Usuario usuario) { // O (???)
        elementos.add(usuario);
        siftUp(elementos.size()-1); //agrego un elem y lo meto donde tiene que estar
    }

    public Usuario extraerMax(){
        Usuario res = elementos.getFirst();
        siftDown();
        return res;
    }
}