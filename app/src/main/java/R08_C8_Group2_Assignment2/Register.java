package R08_C8_Group2_Assignment2;

public class Register {

    public Register(){

    }

    public Register(RegisterStatus registerStatus){
        this.registerStatus = registerStatus;
    }

    public enum RegisterStatus{
        SUCCESSFUL,
        DUPLICATE_USERNAME,
        EMPTY_USERNAME,
        EMPTY_PASSWORD,
        INVALID_PASSWORD
    }

    private RegisterStatus registerStatus;

    public RegisterStatus getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(RegisterStatus registerStatus) {
        this.registerStatus = registerStatus;
    }
}
