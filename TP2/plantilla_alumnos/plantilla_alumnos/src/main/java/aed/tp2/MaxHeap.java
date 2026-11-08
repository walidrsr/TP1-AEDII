package aed.tp2;
import java.util.ArrayList;
import java.util.Comparator;

public class MaxHeap<T> // la prioridad es millasDisponibles
{
    private ArrayList<HandleAM> elementos;
    private Comparator<T> comparador;

    public MaxHeap(Comparator<T> comparator)
    {
        this.elementos = new ArrayList<>();
        this.comparador = comparator;
    }

    public int cardinal(){
        return elementos.size();
    }

    public  class HandleAM implements Handle<T>
    {
        private T valor;
        private int indice;

        private HandleAM(T valor, int indice)
        {
            this.valor = valor;
            this.indice = indice;
        }

        public T valor()
        {
            return valor;
        }

        public void modificar(T v) // hace los intercambios del heap
        {
            valor = v;
            siftUp(indice);
            siftDown(indice);
        }
    } 

    private int IndicePadre(int i)
    {
        return Math.floorDiv(i-1,2);
    }

    private int IndiceHijoIzq(int i)
    {
        return (2*i) +1;
    }
    
    private int IndiceHijoDer(int i)
    {
        return (2*i) +2;
    }

    public HandleAM insertar(T usuario) // O (???)
    {
        HandleAM handle = new HandleAM(usuario, elementos.size());
        elementos.add(handle); // agrego un elem y lo meto donde tiene que estar
        siftUp(handle.indice);
        return handle;
    }

    private void intercambiar(int hijo, int padre) // necesariamente tiene que haber indices hijo y padre validos
    {
        HandleAM handleHijo = elementos.get(hijo);
        HandleAM handlePadre = elementos.get(padre);
        elementos.set(hijo, handlePadre);
        elementos.set(padre, handleHijo);
        handleHijo.indice = padre;
        handlePadre.indice = hijo;
    }

    public void siftUp(int i) // O(log U)
    {
        while (i != 0 && comparador.compare(elementos.get(i).valor, elementos.get(IndicePadre(i)).valor) > 0) { // mientars que hijo > padre y no sea la raiz
            intercambiar(i, IndicePadre(i));
            i = IndicePadre(i);
        }
    }

    private void siftDown(int i) // O(log U)
    {
        while (true)
            {
            if (IndiceHijoIzq(i) >= elementos.size())
                {
                break; // si no hay hijo izq, no hay hijo
            }
            int hijoMayor = IndiceHijoIzq(i);
            if (IndiceHijoDer(i) < elementos.size() && comparador.compare(elementos.get(IndiceHijoDer(i)).valor,elementos.get(hijoMayor).valor) > 0)
                {
                hijoMayor = IndiceHijoDer(i);
            }

            if (comparador.compare(elementos.get(hijoMayor).valor, elementos.get(i).valor) <= 0)
                {
                break; // entonces ningun hijo es mayor que el padre
            }

            intercambiar(i, hijoMayor);
            i = hijoMayor;
        }
    }

    public T maximo(){
        return elementos.get(0).valor;
    }


    public HandleAM extraerMax()
    {
        if (elementos.isEmpty()) // en este caso no tengo elem para sacar
            {
            return null;
        }
        HandleAM maximo = elementos.get(0);

        if (elementos.size() == 1) // en este caso no tengo nada con lo que intercambiar
            {
            elementos.remove(0);
            return maximo;
        }
        HandleAM ultimo = elementos.remove(elementos.size() - 1);
        elementos.set(0, ultimo);
        ultimo.indice = 0;
        siftDown(0);
        return maximo;
    }

    public void reinsertar(Handle<T> h)
    {
        HandleAM handle = (HandleAM) h;
        handle.indice = elementos.size();
        elementos.add(handle);
        siftUp(handle.indice);
    }
}