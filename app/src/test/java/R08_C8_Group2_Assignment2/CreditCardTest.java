package R08_C8_Group2_Assignment2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {

    @Test
    public void testCreditCard() {
        CreditCard card = new CreditCard("knight", "123456");
        assertEquals("123456", card.number);
        assertEquals("knight", card.name);
    }

    @Test
    public void testEqual() {
        CreditCard card = new CreditCard("knight", "123456");
        CreditCard card_1 = new CreditCard("knight", "123456");
        assertTrue(card.equals(card_1));
    }
}
