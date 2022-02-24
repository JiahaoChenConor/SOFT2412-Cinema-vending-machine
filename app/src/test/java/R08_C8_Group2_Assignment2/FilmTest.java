package R08_C8_Group2_Assignment2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilmTest {
    @Test
    void construct() {
        Film film = new Film();
        assertNotNull(film);
    }

    @Test
    void constructWithData() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        assertEquals("ACADEMY DINOSAUR", film.getName());
        assertEquals("PG", film.getClassification());
        assertEquals("A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies", film.getSynopsis());
        assertEquals("2021-01-01", film.getReleaseDate());
        assertEquals("Jack", film.getCasts().get(0));
        assertEquals("Julia", film.getCasts().get(1));
        assertEquals("Brad Pit", film.getCasts().get(2));
    }

    @Test
    void getLocations() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        List<FilmShow> filmShows = new ArrayList<>();
        FilmShow fs1 = new FilmShow(1,
                1,
                null,
                "TownHall",
                "2021-12-01 18:00:00.0",
                "Gold",
                30.0);
        FilmShow fs2 = new FilmShow(1,
                1,
                null,
                "TownHall",
                "2021-12-02 18:00:00.0",
                "Gold",
                30.0);
        filmShows.add(fs1);
        filmShows.add(fs2);
        film.setFilmShows(filmShows);
        assertEquals(fs1, film.getFilmShows().get(0));
        assertEquals(fs2, film.getFilmShows().get(1));
    }

    @Test
    void showDetails() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        List<FilmShow> filmShows = new ArrayList<>();
        FilmShow fs1 = new FilmShow(1,
                1,
                null,
                "TownHall",
                "2021-12-01 18:00:00.0",
                "Gold",
                30.0);
        FilmShow fs2 = new FilmShow(1,
                1,
                null,
                "TownHall",
                "2021-12-02 18:00:00.0",
                "Gold",
                30.0);
        filmShows.add(fs1);
        filmShows.add(fs2);
        film.setFilmShows(filmShows);
        assertEquals("######<< ACADEMY DINOSAUR >>######\n" +
                "\n" +
                "Classification: PG\n" +
                "\n" +
                "Synopsis: A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies \n" +
                "\n" +
                "Release Date: 2021-01-01\n" +
                "\n" +
                "Director: Alan\n" +
                "\n" +
                "Casts: Jack   Julia   Brad Pit    \n" +
                "\n" +
                "Upcoming times and screen sizes: \n" +
                "2021-12-01 18:00:00.0 Screen Size: Gold Location: TownHall\n" +
                "2021-12-02 18:00:00.0 Screen Size: Gold Location: TownHall\n" +
                "\n", film.showDetails());
    }

    @Test
    void setFilmId() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        assertEquals(1, film.getFilmId());
        film.setFilmId(2);
        assertEquals(2, film.getFilmId());
    }

    @Test
    void setClassification() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        assertEquals("PG", film.getClassification());
        film.setClassification("R");
        assertEquals("R", film.getClassification());
    }

    @Test
    void setSynopsis() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        assertEquals("A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies", film.getSynopsis());
        film.setSynopsis("A Astounding Epistle of a Database Administrator And a Explorer who must Find a Car in Ancient China");
        assertEquals("A Astounding Epistle of a Database Administrator And a Explorer who must Find a Car in Ancient China", film.getSynopsis());
    }

    @Test
    void setReleaseDate() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        assertEquals("2021-01-01", film.getReleaseDate());
        film.setReleaseDate("2022-01-01");
        assertEquals("2022-01-01", film.getReleaseDate());
    }

    @Test
    void setDirector() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        assertEquals("Alan", film.getDirector());
        film.setDirector("Daniel");
        assertEquals("Daniel", film.getDirector());
    }

    @Test
    void addFilmShow() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        List<FilmShow> filmShows = new ArrayList<>();
        FilmShow fs1 = new FilmShow(1,
                1,
                null,
                "TownHall",
                "2021-12-01 18:00:00.0",
                "Gold",
                30.0);
        FilmShow fs2 = new FilmShow(1,
                1,
                null,
                "TownHall",
                "2021-12-02 18:00:00.0",
                "Gold",
                30.0);
        filmShows.add(fs1);
        filmShows.add(fs2);
        film.setFilmShows(filmShows);
        film.addFilmShow(fs1);
        assertEquals(3, film.getFilmShows().size());
    }

    @Test
    void deleteFilmShow() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        List<FilmShow> filmShows = new ArrayList<>();
        FilmShow fs1 = new FilmShow(1,
                1,
                null,
                "TownHall",
                "2021-12-01 18:00:00.0",
                "Gold",
                30.0);
        FilmShow fs2 = new FilmShow(1,
                1,
                null,
                "TownHall",
                "2021-12-02 18:00:00.0",
                "Gold",
                30.0);
        filmShows.add(fs1);
        filmShows.add(fs2);
        film.setFilmShows(filmShows);
        film.deleteFilmShow(fs1);
        assertEquals(1, film.getFilmShows().size());
    }

    @Test
    void addCast() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        film.addCast("Conor");
        assertTrue(film.getCasts().contains("Conor"));
    }

    @Test
    void removeCast() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        film.deleteCast("Jack");
        assertFalse(film.getCasts().contains("Jack"));
    }

    @Test
    void setCast() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        film.deleteCast("Jack");
        List<String> newCasts = new ArrayList<>();
        newCasts.add("Conor");
        film.setCasts(newCasts);
        assertTrue(newCasts.contains("Conor"));
        assertFalse(film.getCasts().contains("Jack"));
    }

    @Test
    void setName() {
        List<String> casts = new ArrayList<>();
        casts.add("Jack");
        casts.add("Julia");
        casts.add("Brad Pit");
        Film film = new Film(1,
                "ACADEMY DINOSAUR",
                "PG",
                "A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies",
                "2021-01-01",
                "Alan",
                casts);
        assertEquals("ACADEMY DINOSAUR", film.getName());
        film.setName("ACE GOLDFINGER");
        assertEquals("ACE GOLDFINGER", film.getName());
    }
}
