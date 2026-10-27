package aed.tp2;
import aed.tp2.Usuario;

public class AirMiles {
    private ABBConj<Usuario> usuarios ; // 
    private Usuario maxAcumulador ;
    private Usuario maxCanjeador;
    private Tries<promocion> promociones; // --> creo que seria un tries;


    public AirMiles() 
    {
        this.usuarios= new ABBConj();
        this.maxCanjeador = null;
        this.maxAcumulador = null;
        this.promociones = new Tries(); 
    }

    public void registrarUsuario(int id) 
    {
        Usuario nuevo = new Usuario(id,false);
        usuarios.insertar(nuevo); // O(U) 
        if (maxAcumulador == null){
            maxAcumulador = nuevo;
        }
        if (maxCanjeador == null){
            maxCanjeador = nuevo;
        }
      
    }

    public void registrarUsuarioConReferidor(int id, int ref) 
    {
    
        Usuario nuevoUsuario = new Usuario(id,true);
        usuarios.insertar(nuevoUsuario);
          if(nuevoUsuario.millasTotales > maxAcumulador.millasTotales){
            maxAcumulador = nuevoUsuario;
        }
          
           if(nuevoUsuario.millasTotales > maxCanjeador.millasTotales){
            maxCanjeador= nuevoUsuario;
        }
    }
    private boolean esMaximo(double actual, double nuevo){ // O(1)
        return actual < nuevo;
    }
   

    public void acumularMillas(int id, double distancia, String nombrePromo) 
    {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void canjearMillas(int id, double cantidad) 
    {
        Usuario u = usuarios.buscarPorId(id); //// porqueeeeeeeeeeee
        
        u.millasDisponibles -= cantidad;
        u.millasCanjeadas += cantidad;

        if (esMaximo(maxCanjeador.millasCanjeadas, u.millasCanjeadas)){
            maxCanjeador = u;
        }

    }

    public void transferirMillas(int origen, int destino, double cantidad) 
    {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public double consultarSaldo(int id) 
    {
       Usuario u = usuarios.buscarUsuario(id); // O (log U) x ser ABB /// porque no si es generico buscar y en la def es de tipo T
       return u.millasDisponibles;
    }

    public String consultarCategoria(int id) 
    {
        Usuario u = usuarios.buscarUsuario(id); // O(log U)
        return u.categoria;

    }

    public int[] rankingMillas(int n) // array redimensionalbe
    {
        MaxHeap heap = new MaxHeap(Usuarios.cardinal); // 

        ArregloRedimensionabla<usuario> todosLosUsuarios = new ArregloRedimensionable .... ;
        usuario.recorrer(todosLosUsuarios); // O (N)

        /* recorrer todo el arreglo O(N) y por cada usuario lo insertas en el heap O(log U)*/ 
        for (bla bla)
            {insertar usuario al heap };

        ArregloRedimensionabla<usuario> resultado =  new ArregloRedimensionable .... ;

        for bla bla (){
            Usuario u = heap.extraerMax();
            if (u != null){
                resultado.agregar(u.id);
            }
        }
        return resultado;


    }

    public void iniciarPromocion(String nombre, double factor) 
    {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void iniciarPromocionConTope(String nombre, double factor, double tope) 
    {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void finalizarPromocion(String nombre) 
    {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int elMasAcumulador()  /* el que tiene mas millas totales */ // nos puesieron int se puede cambiar a double ya qu enuestra especificacion usamso reales?
    {
        return  maxAcumulador.millasTotales;
    }

    public int elMasCanjeador()  /* en nuestra especificacion habiamos colocado que el mas canjeador es el que tiene mas ( millasCanjeadas del usuario + las millasDadas del historial de transacciones )*/
    {
        return maxCanjeado.millasCanjeadas;
    }

    public int[][] paresTransferidoresExclusivos() // arrayRedimensionable
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
}
