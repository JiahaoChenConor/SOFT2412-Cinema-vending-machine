package R08_C8_Group2_Assignment2;

public class Login {

    private User user;
    private LoginStatus loginStatus;

    public Login(){

    }

    public Login(User user, LoginStatus loginStatus){
        this.user = user;
        this.loginStatus = loginStatus;
    }

    public enum LoginStatus{
        SUCCESSFUL,
        INPUT_ERROR,
        EMPTY_ERROR
    }

    public LoginStatus getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(LoginStatus loginStatus) {
        this.loginStatus = loginStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static Login currentLogin(String username, String password) {
        if (username == null || password == null || username.strip().equals("") || password.strip().equals("")){
            return new Login(null, LoginStatus.EMPTY_ERROR);
        }else if (!SqlController.login(username,password).getLoggedIn()){
            return new Login(null, LoginStatus.INPUT_ERROR);
        }

        return new Login(SqlController.login(username,password),LoginStatus.SUCCESSFUL);
    }
}
