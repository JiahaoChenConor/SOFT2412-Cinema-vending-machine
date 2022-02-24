package R08_C8_Group2_Assignment2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilmShowTest {
    @Test
    void construct() {
        FilmShow fs = new FilmShow();
        assertNotNull(fs);
    }

    @Test
    void constructWithData() {
        FilmShow fs = new FilmShow(1,
                1,
                "ACE GOLD FINGER",
                "TownHall",
                "2021-12-01 18:00:00.0",
                "Gold",
                30.0);
        assertEquals(1, fs.getFilmId());
        assertEquals(1, fs.getCinemaId());
        assertEquals("ACE GOLD FINGER", fs.getFilmName());
        assertEquals("TownHall", fs.getLocation());
        assertEquals("2021-12-01 18:00:00.0", fs.getTime());
        assertEquals("Gold", fs.getScreen());
        assertEquals(30.0, fs.getPrice());
    }

    @Test
    void equals() {
        FilmShow fs = new FilmShow(1,
                1,
                "ACE GOLD FINGER",
                "TownHall",
                "2021-12-01 18:00:00.0",
                "Gold",
                30.0);
        FilmShow fs1 = new FilmShow(1,
                1,
                "ACE GOLD FINGER",
                "TownHall",
                "2021-12-01 18:00:00.0",
                "Gold",
                30.0);
        assertEquals(fs, fs1);
    }

    @Test
    void notEquals() {
        FilmShow fs = new FilmShow(1,
                1,
                "ACE GOLD FINGER",
                "TownHall",
                "2021-12-01 18:00:00.0",
                "Gold",
                30.0);
        FilmShow fs1 = new FilmShow(1,
                2,
                "ACE GOLD FINGER",
                "TownHall",
                "2021-12-01 18:00:00.0",
                "Gold",
                30.0);
        assertNotEquals(fs, fs1);
    }


}
