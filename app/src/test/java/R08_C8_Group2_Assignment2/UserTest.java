package R08_C8_Group2_Assignment2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserTest {
    @Test
    void construct() {
        User u = new User();
        assertNotNull(u);
    }

    @Test
    void constructWithData() {
        User u = new User("Bob", "Bobb1234");
        assertEquals("Bob", u.getUsername());

        assertEquals("Bobb1234", u.getPassword());
    }

    @Test
    void loggedIn() {
        User u = new User("Bob", "Bobb1234");
        assertFalse(u.getLoggedIn());
        u.setLoggedIn(true);

        assertTrue(u.getLoggedIn());
    }
}
