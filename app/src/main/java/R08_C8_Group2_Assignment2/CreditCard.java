package R08_C8_Group2_Assignment2;

public class CreditCard {
    enum CreditStatus{
        DELETED,
        VALID,
        NOT_EXISTS
    }
    public String name;
    public String number;
    public CreditStatus creditStatus;

    public CreditCard(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public CreditCard(String name, String number, CreditStatus creditStatus) {
        this.name = name;
        this.number = number;
        this.creditStatus = creditStatus;
    }

    @Override
    public boolean equals(Object o){
        if (o == null || o.getClass() != this.getClass())
            return false;
        final CreditCard that = (CreditCard) o;
        return this.name.equals(that.name) && this.number.equals(that.number);
    }

    public CreditStatus getCreditStatus() {
        return creditStatus;
    }

    public void SetCreditStatus(CreditStatus s) {
        this.creditStatus = s;
    }
}
