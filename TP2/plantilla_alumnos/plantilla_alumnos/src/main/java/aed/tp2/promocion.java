package aed.tp2;

public class promocion {
        String nombre;
        double tope;
        double factor;
        double gastadas;

        public void Promocion (String nombrePromocion, double factorPromocion)
        {
            nombre = nombrePromocion;
            factor = factorPromocion;
        }

        public void PromocionConTope(String nombrePromocion, double factorPromocion, double topePromocion)
        {
            nombre = nombrePromocion;
            factor = factorPromocion;
            tope = topePromocion;
        }

        public void usarPromocion(double millas)
        {
            gastadas += millas;
        }
}
