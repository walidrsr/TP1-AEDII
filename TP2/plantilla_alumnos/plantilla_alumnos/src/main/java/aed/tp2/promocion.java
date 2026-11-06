package aed.tp2; /// esto se saca por el trie

public class promocion {
        double tope;
        double factor;
        double gastadas;

        public void  promocion (double factorPromocion)
        {
            factor = factorPromocion;
            tope = 0;
        }

        public void PromocionConTope(double factorPromocion, double topePromocion)
        {
            factor = factorPromocion;
            tope = topePromocion;
            gastadas = 0;
        }

        public void usarPromocion(double millas)
        {
            gastadas += millas;
        }
}
