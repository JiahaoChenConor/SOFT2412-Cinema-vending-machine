package R08_C8_Group2_Assignment2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GiftCardTest {

    @Test
    public void testCardNumber() {
        GiftCard card = new GiftCard();
        card.setCardNumber("12345678909876GC");
        assertEquals("12345678909876GC", card.getCardNumber());
    }

    @Test
    public void testPIN() {
        GiftCard card = new GiftCard();
        card.setPIN("8765");
        assertEquals("8765", card.getPIN());
    }

    @Test
    public void testBalance() {
        GiftCard card = new GiftCard();
        card.setBalance(500);
        assertEquals(500, card.getBalance());
    }

    @Test
    public void testLoginStatus() {
        GiftCard card = new GiftCard();
        card.setLoginStatus(GiftCard.cardLoginStatus.INSUFFICIENT_BALANCE);
        assertEquals(GiftCard.cardLoginStatus.INSUFFICIENT_BALANCE, card.getLoginStatus());
    }

    @Test
    public void testBuyStatus() {
        GiftCard card = new GiftCard();
        card.setBuyStatus(GiftCard.buyStatus.DUPLICATE_CARD);
        assertEquals(GiftCard.buyStatus.DUPLICATE_CARD, card.getBuyStatus());
    }
}
