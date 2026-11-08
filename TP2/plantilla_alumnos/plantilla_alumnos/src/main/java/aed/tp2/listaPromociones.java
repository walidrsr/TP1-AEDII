package aed.tp2;

public class listaPromociones 
{
    public Trie<promocion> listadoPromos;

    public listaPromociones()
    {
        listadoPromos = new Trie<promocion>();
        promocion nopromo = new promocion(1.0);
        listadoPromos.agregar("NoPromo", nopromo);
    }

    public promocion buscarPromo(String nombrePromo)
    {
        return (promocion) listadoPromos.buscar(nombrePromo);
    }

    public void agregarPromo(String nombrePromo, promocion promo)
    {
        listadoPromos.agregar(nombrePromo, promo);
    }

    public void eliminarPromo(String nombrePromo)
    {
        listadoPromos.eliminar(nombrePromo);
    }

    public void actualizarPromo(String nombrePromo, promocion promo, double millasUsadas)
    {
        double millasGastadas = promo.gastadas + millasUsadas;
        if (millasGastadas == promo.tope)
        {
            eliminarPromo(nombrePromo);
        }
        else
        {
            promo.usarPromocion(millasUsadas);
        }
    }
}
