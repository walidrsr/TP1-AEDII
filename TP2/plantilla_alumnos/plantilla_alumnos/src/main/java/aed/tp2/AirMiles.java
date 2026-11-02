package aed.tp2;

public class AirMiles {
    private ABBConj usuarios;
    private Usuario maxAcumulador;
    private Usuario maxCanjeador;
    private Trie promociones;
    private MaxHeap heap;


    public AirMiles()
    {
        this.usuarios= new ABBConj();
        this.maxCanjeador = null;
        this.maxAcumulador = null;
        this.promociones = new Trie();
        this.heap = new MaxHeap();
    }
    
    public void registrarUsuario(int id) // O(log U)
    {
        Usuario nuevo = new Usuario(id,false);
        usuarios.insertar(nuevo); // O(log U)
        heap.insertar(nuevo); // O(log U)
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
        usuarios.insertar(nuevoUsuario); // O(log U)
        heap.insertar(nuevoUsuario); // O(log U)
        Usuario referidor = usuarios.buscarUsuario(ref); // O(log U)
        referidor.millasDisponibles += 500;
        referidor.millasTotales += 500;
        // actualizar en heap con handle
        if (nuevoUsuario.millasTotales > maxAcumulador.millasTotales){
            maxAcumulador = nuevoUsuario;
        }
          
        if (nuevoUsuario.millasTotales > maxCanjeador.millasTotales){
            maxCanjeador= nuevoUsuario;
        }
    }

    public void acumularMillas(int id, double distancia, String nombrePromo) 
    {
        Usuario u = usuarios.buscarUsuario(id); // O(log U)
        // actualizar en heap con handle
        u.ganarMillasPorVuelo(distancia, promociones.buscarNodoFinDeNombre(nombrePromo).factor); // no se si está bien
        if (maxAcumulador.millasTotales < u.millasTotales) {
            maxAcumulador = u;
        }
    }

    public void canjearMillas(int id, double cantidad) 
    {
        Usuario u = usuarios.buscarUsuario(id); // O(log U)
        u.canjear(cantidad);
        // actualizar en el heap con handle
        if (maxCanjeador.millasCanjeadas < u.millasCanjeadas){
            maxCanjeador = u;
        }
    }

    public void transferirMillas(int origen, int destino, double cantidad) 
    {
        Usuario UsuarioDa = usuarios.buscarUsuario(origen);
        Usuario UsuarioRecibe = usuarios.buscarUsuario(destino);
        UsuarioDa.darMillas(cantidad, destino);
        UsuarioRecibe.recibirMillas(cantidad, origen);
        // actualizar en el heap con handle
        if (maxAcumulador.millasTotales < UsuarioRecibe.millasTotales) {
            maxAcumulador = UsuarioRecibe;
        }
    }

    public double consultarSaldo(int id) 
    {
       Usuario u = usuarios.buscarUsuario(id); // O(log U)
       return u.millasDisponibles;
    }

    public String consultarCategoria(int id) 
    {
        Usuario u = usuarios.buscarUsuario(id); // O(log U)
        return u.categoria();
    }

    public int[] rankingMillas(int n) // O(N log U), supongo que n < |array|
    {
        Usuario[] listaUsuarios = new Usuario[n];
        int[] resultado = new int[n];

        for (int i = 0; i < n; i++){
            Usuario u = heap.extraerMax();
            if (u != null) {
                listaUsuarios[i] = u;
            }
        }
        for (int j = 0; j < listaUsuarios.length; j++) {
            heap.insertar(listaUsuarios[j]);
            resultado[j] = listaUsuarios[j].idUsuario;
        }
        
        return resultado;
    }

    public void iniciarPromocion(String nombre, double factor) 
    {
        promociones.agregarPromo(nombre, factor, 0);
    }

    public void iniciarPromocionConTope(String nombre, double factor, double tope) 
    {
        promociones.agregarPromo(nombre, factor, tope);
    }

    public void finalizarPromocion(String nombre) 
    {
        
    }

    public int elMasAcumulador() // O(1)
    {
        if (maxAcumulador == null) {
            return -1;
        }
        return maxAcumulador.idUsuario;
    }

    public int elMasCanjeador()  // O(1)
    {
        if (maxCanjeador == null) {
            return -1;
        }
        return maxCanjeador.idUsuario;
    }

    public int[][] paresTransferidoresExclusivos()
    {
        throw new UnsupportedOperationException("Sin implementar");
    }
}
