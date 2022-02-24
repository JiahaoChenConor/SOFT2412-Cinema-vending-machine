package R08_C8_Group2_Assignment2;

import java.util.List;

public class User {
    // ####### Sprint 3 #############
    enum UserType{
        CUSTOMER,
        MANAGER,
        STAFF
    }
    // ###############################

    private String username;
    private String password;
    private boolean isLoggedIn = false;
    public List<CreditCard> creditCards;

    private UserType userType;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    // ####### Sprint 3 #############
    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    // ###############################


    public User() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getLoggedIn() {
        return this.isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}