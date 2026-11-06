package aed.tp2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class promocionTest {
    @Test 
    void creaPromocionBasica(){
        promocion promo = new promocion();
        promo.promocion(1.5);
        assertEquals(1.5, promo.factor);
        assertEquals(0.0, promo.gastadas);
    }

    @Test 
    void  creaPromocionConTope(){
        promocion promo = new promocion();
        promo.PromocionConTope( 2.0, 5999);


        assertEquals(2.0, promo.factor);
        assertEquals(5999, promo.tope);
        assertEquals(0.0, promo.gastadas);
    }
    @Test
    void creoPromoConTopeYlaUso(){
        promocion promo = new promocion();
        promo.PromocionConTope( 1.5, 3000);

  
        assertEquals(1.5, promo.factor);
        assertEquals(3000, promo.tope);
        assertEquals(0.0, promo.gastadas);

        promo.usarPromocion(2999);
        assertEquals(2999, promo.gastadas);
    }

    @Test 
    void usarPromocionMultipleVeces(){
        promocion promo = new promocion();
     

        promo.usarPromocion(25.0);
        promo.usarPromocion(2.0);
        promo.usarPromocion(5.0);
        promo.usarPromocion(3.0);
        promo.usarPromocion(18);

        assertEquals(53,promo.gastadas);
    }

}
