package aed.tp2;

public class AirMiles implements SistemaAirMiles
{
    private ABBConj<Handle<Usuario>> usuarios;
    private Usuario maxAcumulador;
    private Usuario maxCanjeador;
    private Trie<promocion> promociones;
    private MaxHeap<Usuario> heap;


    public AirMiles()
    {
        this.usuarios = new ABBConj<>();
        this.maxCanjeador = null;
        this.maxAcumulador = null;
        this.promociones = new Trie<>();
        this.heap = new MaxHeap<>(new Usuario.PorMillasDisponibles());
    }
    
    public void registrarUsuario(int id) // O(log U)
    {
        Usuario nuevo = new Usuario(id,false);
        Handle<Usuario> handle = heap.insertar(nuevo); // O(log U)
        usuarios.insertar(id, handle); // O(log U)
        if (maxAcumulador == null)
            {
            maxAcumulador = nuevo;
        }
        if (maxCanjeador == null)
            {
            maxCanjeador = nuevo;
        }
    }

    public void registrarUsuarioConReferidor(int id, int ref) 
    {
        Usuario nuevoUsuario = new Usuario(id,true);
        Handle<Usuario> handle = heap.insertar(nuevoUsuario); // O(log U)
        usuarios.insertar(id, handle); // O(log U)
        Handle<Usuario> handleReferidor = usuarios.buscar(ref); // O(log U)
        Usuario usuarioReferidor = handleReferidor.valor();
        usuarioReferidor.millasDisponibles += 500;
        usuarioReferidor.millasTotales += 500;
        handleReferidor.modificar(usuarioReferidor);
        if (usuarioReferidor.millasTotales > maxAcumulador.millasTotales) // el nuevoUsuario nunca puede ser UNICO maxAcumulador, como minimo tiene que estar compartiendo ese puesto con usuarioReferidor
            { 
            maxAcumulador = usuarioReferidor;
        }
    }

    public void acumularMillas(int id, double distancia, String nombrePromo) 
    {
        Handle<Usuario> handle = usuarios.buscar(id); // O(log U)
        Usuario usuario = handle.valor();
        promocion promo  = promociones.buscar(nombrePromo);
        usuario.ganarMillasPorVuelo(distancia, promo.factor); 
        handle.modificar(usuario);
        if (maxAcumulador.millasTotales < usuario.millasTotales)
            {
            maxAcumulador = usuario;
        }
    }

    public void canjearMillas(int id, double cantidad) 
    {
        Handle<Usuario> handle = usuarios.buscar(id); // O(log U)
        Usuario usuario = handle.valor();
        usuario.canjear(cantidad);
        handle.modificar(usuario);
        if (maxCanjeador.millasCanjeadas < usuario.millasCanjeadas)
            {
            maxCanjeador = usuario;
        }
    }

    public void transferirMillas(int origen, int destino, double cantidad) 
    {
        Handle<Usuario> handleDa = usuarios.buscar(origen);
        Handle<Usuario> handleRecibe = usuarios.buscar(destino);
        Usuario usuarioDa = handleDa.valor();
        Usuario usuarioRecibe = handleRecibe.valor();
        usuarioDa.darMillas(cantidad, destino);
        usuarioRecibe.recibirMillas(cantidad, origen);
        handleDa.modificar(usuarioDa);
        handleRecibe.modificar(usuarioRecibe);
        usuarioDa.cambioUsuarioExclusivo(usuarioRecibe);
        if (maxCanjeador.millasCanjeadas < usuarioDa.millasCanjeadas){
            maxCanjeador = usuarioDa;
        }
        // tomar en cuenta que no se pueden modificar las millasTotales de usuarioRecibe
    }

    public double consultarSaldo(int id) 
    {
       Handle<Usuario> handle = usuarios.buscar(id); // O(log U)
       return handle.valor().millasDisponibles;
    }

    public String consultarCategoria(int id) 
    {
        Handle<Usuario> handle = usuarios.buscar(id); // O(log U)
        return handle.valor().categoria();
    }

    public int[] rankingMillas(int n) // O(N log U), supongo que n < |array|
    {
        Handle<Usuario>[] listaUsuarios = new Handle[n];

        int cantidadElems = 0;
        while (cantidadElems < n)
            {
            Handle<Usuario> handle = heap.extraerMax();
            if (handle == null) {
                break;
            }
            listaUsuarios[cantidadElems] = handle;
            cantidadElems++;
        }
        int[] resultado = new int[cantidadElems];
        for (int i = 0; i < cantidadElems; i++)
            {
            resultado[i] = listaUsuarios[i].valor().idUsuario;
            heap.reinsertar(listaUsuarios[i]);
        }
        return resultado;
    }

    public void iniciarPromocion(String nombre, double factor) 
    {
        // promociones.agregar(nombre, factor);
    }

    public void iniciarPromocionConTope(String nombre, double factor, double tope) 
    {
        // promociones.agregarPromo(nombre, factor, tope);
    }

    public void finalizarPromocion(String nombre) 
    {
        //
    }

    public int elMasAcumulador() // O(1)
    {
        if (maxAcumulador == null)
            {
            return -1;
        }
        return maxAcumulador.idUsuario;
    }

    public int elMasCanjeador()  // O(1)
    {
        if (maxCanjeador == null)
            {
            return -1;
        }
        return maxCanjeador.idUsuario;
    }

    public int[][] paresTransferidoresExclusivos()
    {
        int[][] pares = new int[usuarios.cardinal()][]; // acá pongo los pares
        ABBConj<Handle<Usuario>>.ABB_Iterador iterador = usuarios.iterador();
        int cantidadPares = 0;
        while (iterador.haySiguiente())
        {
            Usuario actual = iterador.siguiente().valor();
            if ((actual.usuarioExclusivo != null && actual.usuarioExclusivo.idUsuario != -1) && actual.idUsuario > actual.usuarioExclusivo.idUsuario) {
                pares[cantidadPares] = new int[]{actual.idUsuario, actual.usuarioExclusivo.idUsuario};
                cantidadPares++;
            }
        }
        int[][] resultado = new int[cantidadPares][]; // que tenga la longitud necesaria
        for (int j = 0; j < cantidadPares; j++)
        {
            resultado[j] = pares[j];
        }
        return resultado;
    }
}