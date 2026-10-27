package aed.tp2;

import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
    private ArrayList<T> elementos;

    public MaxHeap (){
        this.elementos = new ArrayList<>();
    }

    private int  IndicePadre(int i){
        return (i-1) /2;
    }
    private int IndiceHijoIzq(int i){
        return (2*i) +1;
    }
    
    private int IndiceHijoDer(int i){
        return (2*i) +2;
    }

    private void intercambiar(int i, int j) {

    }
    private void siftUp(int i) {

    }

    private void siftDown(int i) {

    }
    
    public void insertar(T elemento) { // O (???)
        elementos.add(elemento);
        siftUp(elementos.size() - 1); 
    }
    public T extraerMax(){
        return maximo;
    }

}