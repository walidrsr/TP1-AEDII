package aed.tp2;
import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
    private ArrayList<T> elementos;

    public MaxHeap() {
        this.elementos = new ArrayList<>();
    }

    private int indicePadre(int i) {
        return Math.floorDiv(i - 1, 2);
    }

    private int indiceHijoIzq(int i) {
        return (2 * i) + 1;
    }
    
    private int indiceHijoDer(int i) {
        return (2 * i) + 2;
    }

    private void intercambiar(int hijo, int padre) {
        T valorHijo = elementos.get(hijo);
        elementos.set(hijo, elementos.get(padre));
        elementos.set(padre, valorHijo);
    }

    private void siftUp(int i) {
        // Mientras no sea la raíz y el padre sea menor que el hijo (padre.compareTo(hijo) < 0)
        while (i != 0 && elementos.get(indicePadre(i)).compareTo(elementos.get(i)) < 0) {
            intercambiar(i, indicePadre(i));
            i = indicePadre(i);
        }
    }

    private void siftDown() {
        if (elementos.size() <= 1) {
            if (!elementos.isEmpty()) {
                elementos.remove(0);
            }
            return;
        }
        elementos.set(0, elementos.getLast()); // Intercambio la raíz con el último
        elementos.remove(elementos.size() - 1); // Saco la raíz vieja
        int i = 0;
       
        while (true) {
            int hijoIzq = indiceHijoIzq(i);
            if (hijoIzq >= elementos.size()) {
                break; // Si no hay hijo izq, no hay hijos
            }

            int hijoMayor = hijoIzq;
            int hijoDer = indiceHijoDer(i);
            
            // Si hay hijo derecho y es mayor que el izquierdo
            if (hijoDer < elementos.size() && elementos.get(hijoDer).compareTo(elementos.get(hijoIzq)) > 0) {
                hijoMayor = hijoDer;
            }

            // Si el actual es mayor o igual al mayor de sus hijos, terminó
            if (elementos.get(i).compareTo(elementos.get(hijoMayor)) >= 0) {
                break; 
            }

            intercambiar(i, hijoMayor);
            i = hijoMayor;
        }
    }
    
    public void insertar(T elem) {
        elementos.add(elem);
        siftUp(elementos.size() - 1);
    }

    public T extraerMax() {
        if (elementos.isEmpty()) {
            return null;
        }
        T res = elementos.getFirst();
        siftDown();
        return res;
    }

    public T getMaximo() {
        if (elementos.isEmpty()) {
            return null;
        }
        return elementos.getFirst();
    }

    public boolean esVacio() {
        return elementos.isEmpty();
    }
    

    public int cardinal() {
        return elementos.size();
    }
}