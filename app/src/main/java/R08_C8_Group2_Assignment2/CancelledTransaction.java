package R08_C8_Group2_Assignment2;

public class CancelledTransaction {


    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    // "timeout", "user cancelled", "card payment failed"
    enum Reason{
        TIME_OUT,
        USER_CANCELLED,
        CREDIT_CARD_PAYMENT_FAILED,
        GIFT_CARD_PAYMENT_FAILED,
        SYSTEM_CRUSHED
    }

    private int transactionId;
    private Reason reason;
    private String user;
    private String time;


    public CancelledTransaction(){}

    public CancelledTransaction(int transactionId,  Reason reason){
        this.transactionId = transactionId;
        this.reason = reason;
    }

    public CancelledTransaction(int transactionId,  Reason reason, String user, String time){
        this.transactionId = transactionId;
        this.reason = reason;
        this.user = user;
        this.time = time;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }




}
