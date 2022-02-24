package R08_C8_Group2_Assignment2;

public class GiftCard {
    private String cardNumber; // 16 chars
    private String PIN; // 4 chars
    private double balance;
    private cardLoginStatus loginStatus;
    private buyStatus buyStatus;

    public GiftCard () {}

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setPIN(String pin) {
        this.PIN = pin;
    }

    public String getPIN() {
        return this.PIN;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setLoginStatus(cardLoginStatus loginStatus) {
        this.loginStatus = loginStatus;
    }

    public cardLoginStatus getLoginStatus() {
        return this.loginStatus;
    }

    public void setBuyStatus(buyStatus buyStatus) {
        this.buyStatus = buyStatus;
    }

    public buyStatus getBuyStatus() {
        return this.buyStatus;
    }

    public enum cardLoginStatus {
        LOST,
        INSUFFICIENT_BALANCE,
        NONEXISTENT_CARD_NUMBER,
        INCORRECT_PIN,
        LOGGED_IN
    }

    public enum buyStatus {
        DUPLICATE_CARD,
        INCORRECT_FORMAT_OF_CARD_NUMBER,
        INCORRECT_FORMAT_OF_PIN,
        LARGE_AMOUNT, // should be <= 1000 and > 0
        INAPPROPRIATE_AMOUNT, // should be Multiples of 5
        SUCCESS
    }
}
