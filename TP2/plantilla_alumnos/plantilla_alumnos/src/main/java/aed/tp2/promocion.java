package aed.tp2;

public class Promocion {
        String nombre;
        double tope;
        double factor;
        double gastadas;

        public Promocion(String nombrePromocion, double factorPromocion)
        {
            nombre = nombrePromocion;
            factor = factorPromocion;
        }

        public Promocion(String nombrePromocion, double factorPromocion, double topePromocion)
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
