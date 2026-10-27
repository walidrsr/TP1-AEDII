package aed.tp2;
import aed.tp2.Usuario;

public class AirMiles {
    private ABBConj<Usuario> usarios ; // 
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
        usarios.insertar(nuevo); // O(U) 
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
        usarios.insertar(nuevoUsuario);
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
        usuario u = usuarios.buscarPorId(id);
        
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
       usuario u = usarios.buscarPorId(id); // O (log U) x ser ABB
       return u.millasDisponibles;
    }

    public String consultarCategoria(int id) 
    {
        usuario u = usarios.buscarPorId(id); // O(log U)
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
            usuario u = heap.extraerMayor();
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

    public int elMasAcumulador()  /* el que tiene mas millas totales */
    {
        return  maxAcumulador;
    }

    public int elMasCanjeador()  /* en nuestra especificacion habiamos colocado que el mas canjeador es el que tiene mas ( millasCanjeadas del usuario + las millasDadas del historial de transacciones )*/
    {
        return maxCanjeador;
    }

    public int[][] paresTransferidoresExclusivos() // arrayRedimensionable
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
}
