package R08_C8_Group2_Assignment2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    @Test
    void construct() {
        Login l = new Login();
        assertNotNull(l);
    }

    @Test
    void constructWithData() {
        User u = new User("Bob", "Bobb1234");
        Login l = new Login(u, Login.LoginStatus.SUCCESSFUL);
        assertEquals(Login.LoginStatus.SUCCESSFUL, l.getLoginStatus());
        assertEquals("Bob", l.getUser().getUsername());
    }

    @Test
    void setLoginStatus() {
        User u = new User("Bob", "Bobb1234");
        Login l = new Login(u, Login.LoginStatus.SUCCESSFUL);
        assertEquals(Login.LoginStatus.SUCCESSFUL, l.getLoginStatus());
        l.setLoginStatus(Login.LoginStatus.EMPTY_ERROR);
        assertEquals(Login.LoginStatus.EMPTY_ERROR, l.getLoginStatus());
    }

    @Test
    void currentLogin() {
        SqlController.connectServer();
        SqlController.prepareForStatement();
        SqlController.initDataBase();
        SqlController.dropDatabase();
        SqlController.initTable();
        SqlController.resetData();
        Login l = Login.currentLogin("Conor", "123456abc");
        assertEquals(Login.LoginStatus.SUCCESSFUL, l.getLoginStatus());
        SqlController.disconnectDataBase();
    }

    @Test
    void currentLoginEmpty() {
        SqlController.connectServer();
        SqlController.prepareForStatement();
        SqlController.initDataBase();
        SqlController.dropDatabase();
        SqlController.initTable();
        SqlController.resetData();
        Login l = Login.currentLogin("Conor", "");
        assertEquals(Login.LoginStatus.EMPTY_ERROR, l.getLoginStatus());
        SqlController.disconnectDataBase();
    }

    @Test
    void currentLoginWrong() {
        SqlController.connectServer();
        SqlController.prepareForStatement();
        SqlController.initDataBase();
        SqlController.dropDatabase();
        SqlController.initTable();
        SqlController.resetData();
        Login l = Login.currentLogin("Conor", "1234");
        assertEquals(Login.LoginStatus.INPUT_ERROR, l.getLoginStatus());
        SqlController.disconnectDataBase();
    }
}
