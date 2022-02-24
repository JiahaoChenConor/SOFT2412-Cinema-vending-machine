package R08_C8_Group2_Assignment2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * SqlController Tester.
 *
 */
public class SqlControllerTest {

    @BeforeEach
    public void before() {
        SqlController.connectServer();
        SqlController.prepareForStatement();
        SqlController.initDataBase();
        SqlController.dropDatabase();
        SqlController.initTable();
        SqlController.resetData();
        SqlController.importValidCard();
    }

    @AfterEach
    public void after() {
        SqlController.disconnectDataBase();
    }

    /**
     *
     * Method: getLocations()
     *
     */
    @Test
    public void testGetLocations() {
        List<String> locations = SqlController.getLocations();
        assertEquals("TownHall", locations.get(0));
        assertEquals("Burwood", locations.get(1));
    }

    /**
     *
     * Method: getFilms()
     *
     */
    @Test
    public void testGetFilms() {
        List<Film> films = SqlController.getFilms();
        assertEquals(1, films.get(0).getFilmId());
        assertEquals("ACADEMY DINOSAUR", films.get(0).getName());
        assertEquals("PG", films.get(0).getClassification());
        assertEquals("A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies", films.get(0).getSynopsis());
        assertEquals("2021-01-01", films.get(0).getReleaseDate());
        assertEquals("Alan", films.get(0).getDirector());
    }

    /**
     *
     * Method: getFilmDetails(int filmId)
     *
     */
    @Test
    public void testGetFilmDetails() {
        Film film = SqlController.getFilmDetails(1);
        assertEquals(1, film.getFilmId());
        assertEquals("ACADEMY DINOSAUR", film.getName());
        assertEquals("PG", film.getClassification());
        assertEquals("A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies", film.getSynopsis());
        assertEquals("2021-01-01", film.getReleaseDate());
        assertEquals("Alan", film.getDirector());
    }

    /**
     *
     * Method: filter(String location)
     *
     */
    @Test
    public void testFilter() {
        List<FilmShow> filmShows = SqlController.filter("Burwood");
        assertEquals(2, filmShows.get(0).getFilmId());
        assertEquals("ACE GOLDFINGER", filmShows.get(0).getFilmName());
        assertEquals(2, filmShows.get(0).getCinemaId());
        assertEquals("2021-12-02 18:00:00.0", filmShows.get(0).getTime());
        assertEquals("Silver", filmShows.get(0).getScreen());
        assertEquals(30, filmShows.get(0).getPrice());
        assertEquals("Burwood", filmShows.get(0).getLocation());
        assertEquals(3, filmShows.get(0).getShowId());
        assertEquals(12, filmShows.get(0).getNumSeatRows());
        assertEquals(10, filmShows.get(0).getNumSeatColumns());
    }

    /**
     *
     * Method: checkUserExists(String username)
     *
     */
    @Test
    public void testCheckUserExists() {
        assertTrue(SqlController.checkUserExists("Conor"));
        assertFalse(SqlController.checkUserExists("Jack"));
    }

    /**
     *
     * Method: checkValidPassword(String password)
     *
     */
    @Test
    public void testCheckValidPassword_1() {
        assertFalse(SqlController.checkValidPassword("123abc"));
    }

    @Test
    public void testCheckValidPassword_2() {
        assertFalse(SqlController.checkValidPassword("asdabc"));
    }

    @Test
    public void testCheckValidPassword_0() {
        assertTrue(SqlController.checkValidPassword("abcdefg2345"));
    }

    @Test
    public void testCheckValidPassword_3() {
        assertFalse(SqlController.checkValidPassword("123345"));
    }

    @Test
    public void testCheckValidPassword_4(){assertFalse(SqlController.checkValidPassword(null));}

    /**
     *
     * Method: registerUser(String username, String password)
     *
     */
    @Test
    public void testRegisterUser_0() {
        Register register = SqlController.registerUser("", "123456abcd");
        assertEquals(Register.RegisterStatus.EMPTY_USERNAME, register.getRegisterStatus());
    }

    @Test
    public void testRegisterUser_1() {
        Register register = SqlController.registerUser("Bob", "");
        assertEquals(Register.RegisterStatus.EMPTY_PASSWORD, register.getRegisterStatus());
    }

    @Test
    public void testRegisterUser_2() {
        Register register = SqlController.registerUser("Conor", "123456abcd");
        assertEquals(Register.RegisterStatus.DUPLICATE_USERNAME, register.getRegisterStatus());
    }

    @Test
    public void testRegisterUser_3() {
        Register register = SqlController.registerUser("Bob", "123456");
        assertEquals(Register.RegisterStatus.INVALID_PASSWORD, register.getRegisterStatus());
    }

    /**
     *
     * Method: login(String username, String password)
     *
     */
    @Test
    public void testLogin_0() {
        User user = SqlController.login("Conor", "123456abc");
        assertTrue(user.getLoggedIn());
    }

    @Test
    public void testLogin_1() {
        User user = SqlController.login("Conor", "12345");
        assertFalse(user.getLoggedIn());
    }

    @Test
    public void testLogin_2() {
        User user = SqlController.login("Notexists", "12345");
        assertFalse(user.getLoggedIn());
    }

    /**
     *
     * Method: getFilmShowsByFilm(int filmId)
     *
     */
    @Test
    public void testGetFilmShowsByFilm() {
        List<FilmShow> filmShows = SqlController.getFilmShowsByFilm(2);
        assertEquals(2, filmShows.get(0).getFilmId());
        assertEquals("ACE GOLDFINGER", filmShows.get(0).getFilmName());
        assertEquals(2, filmShows.get(0).getCinemaId());
        assertEquals("2021-12-02 18:00:00.0", filmShows.get(0).getTime());
        assertEquals("Silver", filmShows.get(0).getScreen());
        assertEquals(30, filmShows.get(0).getPrice());
        assertEquals("Burwood", filmShows.get(0).getLocation());
        assertEquals(3, filmShows.get(0).getShowId());
        assertEquals(12, filmShows.get(0).getNumSeatRows());
        assertEquals(10, filmShows.get(0).getNumSeatColumns());
    }

    /**
     *
     * Method: getMaxTicketId()
     *
     */
    @Test
    public void testGetMaxTicketId() {
        int max = SqlController.getMaxTicketId();
        assertEquals(3, max);
    }

    /**
     *
     * Method: buyTicket(int showId, Ticket.Person personType, int seat_row, int seat_column, String username)
     *
     */
    @Test
    public void testBuyTicketSuccess() {
        Ticket ticket = SqlController.buyTicket(1, Ticket.Person.ADULT, 3, 5, "Conor");
        assertEquals(Ticket.State.SUCCESS, ticket.getState());
        assertEquals(4, ticket.getTicket_id());
        assertEquals(Ticket.Person.ADULT, ticket.getPerson());
        assertEquals(3, ticket.getSeat_row());
        assertEquals(5, ticket.getSeat_column());
    }

    @Test
    public void textBuyTicketError() {
        Ticket ticket = SqlController.buyTicket(10, Ticket.Person.ADULT, 3, 3, "jack");
        assertEquals(Ticket.State.ERROR, ticket.getState());
    }

    @Test
    public void textBuyTicketOutOfRange() {
        Ticket ticket = SqlController.buyTicket(1, Ticket.Person.ADULT, 53, 5, "Conor");
        assertEquals(Ticket.State.OUT_OF_RANGE, ticket.getState());
    }

    @Test
    public void textBuyTicketOccupied() {
        Ticket ticket = SqlController.buyTicket(1, Ticket.Person.ADULT, 1, 1, "Conor");
        assertEquals(Ticket.State.OCCUPIED, ticket.getState());
    }

    /**
     *
     * Method: cancelTicketTransaction(int ticketId)
     *
     */
    @Test
    public void testCancelTicketTransaction() {
        SqlController.cancelTicketTransaction(3);
    }

    /**
     *
     * Method: refundTicketTransaction(int ticketId)
     *
     */
    @Test
    public void testRefundTicketTransaction() {
        SqlController.refundTicketTransaction(2);
    }

    /**
     *
     * Method: getBookingInformationOfShow(int showId)
     *
     */
    @Test
    public void testGetBookingInformationOfShow() {
        String s = SqlController.getBookingInformationOfShow(1);
    }

    /**
     *
     * Method: getPurchasedTickets(String username)
     *
     */
    @Test
    public void testGetPurchasedTickets() {
        List<Ticket> tickets = SqlController.getPurchasedTickets("Conor");
        assertEquals(3, tickets.size());
        assertEquals(Ticket.State.SUCCESS, tickets.get(0).getState());
        assertEquals(Ticket.Person.CHILD, tickets.get(0).getPerson());
        assertEquals(Ticket.Person.ADULT, tickets.get(1).getPerson());
        assertEquals(Ticket.Person.STUDENT, tickets.get(2).getPerson());
    }

    /**
     *
     * Method: getMaxTranId()
     *
     */
    @Test
    public void testGetMaxTranId() {
        assertEquals(0, SqlController.getMaxTranId());
    }

    /**
     *
     * Method: cancelTransaction(CancelledTransaction.Reason reason)
     *
     */
    @Test
    public void testCancelTransaction() {
        CancelledTransaction cancel = SqlController.cancelTransaction(CancelledTransaction.Reason.TIME_OUT, "ZhiQian");
        assertEquals(1, cancel.getTransactionId());
        assertEquals(CancelledTransaction.Reason.TIME_OUT, cancel.getReason());
    }

    /**
     *
     * Method: reportOfCancelledTransaction()
     *
     */
    @Test
    public void testReportOfCancelledTransaction() {
        CancelledTransaction cancel_0 = SqlController.cancelTransaction(CancelledTransaction.Reason.USER_CANCELLED, "ZhiQian");
        CancelledTransaction cancel_1 = SqlController.cancelTransaction(CancelledTransaction.Reason.CREDIT_CARD_PAYMENT_FAILED, "ZhiQian");
        String report = SqlController.reportOfCancelledTransaction();
    }

    /**
     *
     *
     * Method: updateFilmDetails(int filmId, String newFilmName, String newClassification, String newSynopsis, String newReleaseDate, String newDirector)
     *
     */
    @Test
    public void testUpdateFilmDetails() {
        SqlController.updateFilmDetails(1, "abc", "PG+++", "test", "2001-01-29", "CCConor");
    }

    /**
     *
     * Method: checkGiftCardExists(String cardNumber)
     *
     */
    @Test
    public void testCheckGiftCardExists() {
        assertTrue(SqlController.checkGiftCardExists("11111111111111GC"));
        assertFalse(SqlController.checkGiftCardExists("123446"));
    }

    /**
     *
     * Method: buyGiftCard(String cardNumber, String pin, double amount)
     *
     */
    @Test
    public void testBuyGiftCard_0() {
        GiftCard card = SqlController.buyGiftCard("12345678901234GC", "2333", 450);
        assertEquals(GiftCard.buyStatus.SUCCESS, card.getBuyStatus());
    }

    @Test
    public void testBuyGiftCard_1() {
        GiftCard card = SqlController.buyGiftCard("123456789012GC", "2333", 450);
        assertEquals(GiftCard.buyStatus.INCORRECT_FORMAT_OF_CARD_NUMBER, card.getBuyStatus());
    }

    @Test
    public void testBuyGiftCard_2() {
        GiftCard card = SqlController.buyGiftCard("123456789012348C", "2333", 450);
        assertEquals(GiftCard.buyStatus.INCORRECT_FORMAT_OF_CARD_NUMBER, card.getBuyStatus());
    }

    @Test
    public void testBuyGiftCard_3() {
        GiftCard card = SqlController.buyGiftCard("12345678901234GC", "233", 450);
        assertEquals(GiftCard.buyStatus.INCORRECT_FORMAT_OF_PIN, card.getBuyStatus());
    }

    @Test
    public void testBuyGiftCard_4() {
        GiftCard card = SqlController.buyGiftCard("11111111111111GC", "2333", 450);
        assertEquals(GiftCard.buyStatus.DUPLICATE_CARD, card.getBuyStatus());
    }

    @Test
    public void testBuyGiftCard_5() {
        GiftCard card = SqlController.buyGiftCard("12345678901234GC", "2333", 1450);
        assertEquals(GiftCard.buyStatus.LARGE_AMOUNT, card.getBuyStatus());
    }

    @Test
    public void testBuyGiftCard_6() {
        GiftCard card = SqlController.buyGiftCard("12345678901234GC", "2333", -450);
        assertEquals(GiftCard.buyStatus.LARGE_AMOUNT, card.getBuyStatus());
    }

    @Test
    public void testBuyGiftCard_7() {
        GiftCard card = SqlController.buyGiftCard("12345678901234GC", "2333", 452);
        assertEquals(GiftCard.buyStatus.INAPPROPRIATE_AMOUNT, card.getBuyStatus());
    }

    /**
     *
     * Method: useGiftCard(String cardNumber, String pin, double price)
     *
     */
    @Test
    public void testUseGiftCard_0() {
        GiftCard card = SqlController.useGiftCard("11111111111111GC", "5432", 30);
        assertEquals(GiftCard.cardLoginStatus.LOGGED_IN, card.getLoginStatus());
    }

    @Test
    public void testUseGiftCard_1() {
        GiftCard card = SqlController.useGiftCard("22222222222222GC", "8765", 30);
        assertEquals(GiftCard.cardLoginStatus.LOST, card.getLoginStatus());
    }

    @Test
    public void testUseGiftCard_2() {
        GiftCard card = SqlController.useGiftCard("11111112211111GC", "5432", 30);
        assertEquals(GiftCard.cardLoginStatus.NONEXISTENT_CARD_NUMBER, card.getLoginStatus());
    }

    @Test
    public void testUseGiftCard_3() {
        GiftCard card = SqlController.useGiftCard("11111111111111GC", "5436", 30);
        assertEquals(GiftCard.cardLoginStatus.INCORRECT_PIN, card.getLoginStatus());
    }

    @Test
    public void testUseGiftCard_4() {
        GiftCard card = SqlController.useGiftCard("11111111111111GC", "5432", 1300);
        assertEquals(GiftCard.cardLoginStatus.INSUFFICIENT_BALANCE, card.getLoginStatus());
    }

    /**
     *
     * Method: saveCreditCard(User user, CreditCard card)
     *
     */
    @Test
    public void testSaveCreditCard() {
        User user = new User();
        CreditCard card = new CreditCard("Simp1e", "123456");
        SqlController.saveCreditCard(user, card);
    }

    /**
     *
     * Method: showAllCreditCards()
     *
     */
    @Test
    public void testShowAllCreditCards() {
        assertEquals("Holder Name: Andy, creditCardNumber: 82050, Status: valid\n" +
                "Holder Name: Arthur, creditCardNumber: 41696, Status: valid\n" +
                "Holder Name: Audrey, creditCardNumber: 45925, Status: valid\n" +
                "Holder Name: Blake, creditCardNumber: 14138, Status: valid\n" +
                "Holder Name: Brian, creditCardNumber: 44756, Status: valid\n" +
                "Holder Name: Chad, creditCardNumber: 34572, Status: valid\n" +
                "Holder Name: Charles, creditCardNumber: 40691, Status: valid\n" +
                "Holder Name: Christine, creditCardNumber: 35717, Status: valid\n" +
                "Holder Name: Christopher, creditCardNumber: 28376, Status: valid\n" +
                "Holder Name: Christopher, creditCardNumber: 87286, Status: valid\n" +
                "Holder Name: Debbie, creditCardNumber: 92090, Status: valid\n" +
                "Holder Name: Deena, creditCardNumber: 95953, Status: valid\n" +
                "Holder Name: Donald, creditCardNumber: 23858, Status: valid\n" +
                "Holder Name: Edwin, creditCardNumber: 23842, Status: valid\n" +
                "Holder Name: Elaine, creditCardNumber: 48685, Status: valid\n" +
                "Holder Name: Elizabeth, creditCardNumber: 96667, Status: valid\n" +
                "Holder Name: Evelyn, creditCardNumber: 64820, Status: valid\n" +
                "Holder Name: Felix, creditCardNumber: 31093, Status: valid\n" +
                "Holder Name: Francisco, creditCardNumber: 27402, Status: valid\n" +
                "Holder Name: Helene, creditCardNumber: 72500, Status: valid\n" +
                "Holder Name: James, creditCardNumber: 20565, Status: valid\n" +
                "Holder Name: James, creditCardNumber: 33527, Status: valid\n" +
                "Holder Name: Janet, creditCardNumber: 69655, Status: valid\n" +
                "Holder Name: Jeffrey, creditCardNumber: 98708, Status: valid\n" +
                "Holder Name: Jeremy, creditCardNumber: 74061, Status: valid\n" +
                "Holder Name: Joan, creditCardNumber: 77852, Status: valid\n" +
                "Holder Name: John, creditCardNumber: 90669, Status: valid\n" +
                "Holder Name: Julie, creditCardNumber: 56907, Status: valid\n" +
                "Holder Name: Kasey, creditCardNumber: 60146, Status: valid\n" +
                "Holder Name: Kenneth, creditCardNumber: 60632, Status: valid\n" +
                "Holder Name: Leonard, creditCardNumber: 72238, Status: valid\n" +
                "Holder Name: Liana, creditCardNumber: 75183, Status: valid\n" +
                "Holder Name: Linda, creditCardNumber: 38409, Status: valid\n" +
                "Holder Name: Manuel, creditCardNumber: 53477, Status: valid\n" +
                "Holder Name: Marguerite, creditCardNumber: 30831, Status: valid\n" +
                "Holder Name: Mark, creditCardNumber: 66192, Status: valid\n" +
                "Holder Name: Maxine, creditCardNumber: 34402, Status: valid\n" +
                "Holder Name: Michael, creditCardNumber: 24531, Status: valid\n" +
                "Holder Name: Naomi, creditCardNumber: 43114, Status: valid\n" +
                "Holder Name: Patricia, creditCardNumber: 30690, Status: valid\n" +
                "Holder Name: Rebecca, creditCardNumber: 54981, Status: valid\n" +
                "Holder Name: Robert, creditCardNumber: 85202, Status: valid\n" +
                "Holder Name: Ruby, creditCardNumber: 78073, Status: valid\n" +
                "Holder Name: Ruth, creditCardNumber: 55134, Status: valid\n" +
                "Holder Name: Sergio, creditCardNumber: 42689, Status: valid\n" +
                "Holder Name: Simone, creditCardNumber: 89037, Status: valid\n" +
                "Holder Name: Stacey, creditCardNumber: 26436, Status: valid\n" +
                "Holder Name: Vincent, creditCardNumber: 59141, Status: valid\n" +
                "Holder Name: Wanda, creditCardNumber: 97523, Status: valid\n" +
                "Holder Name: William, creditCardNumber: 67707, Status: valid\n", SqlController.showAllCreditCards());
    }

    /**
     *
     * Method: addMovie(String, String, String, String, String, List)
     *
     */
    @Test
    public void testAddMovie() {
        List<String> cast = new ArrayList<>();
        SqlController.addMovie("hhh", "PG", "test", "2001-01-01", "joker", cast);
    }

    /**
     *
     * Method: showAllGiftCards()
     *
     */
    @Test
    public void testShowAllGiftCards() {
        assertEquals("card Num: 11111111111111GC, PIN: 5432, balance: 1000.0, lost: false\n" +
                "card Num: 22222222222222GC, PIN: 8765, balance: 500.0, lost: true\n", SqlController.ShowAllGiftCards());
    }

    /**
     *
     * Method: addShow(int filmId, int cinemaId, String time, String screen, double price, int row, int column)
     *
     */
    @Test
    public void testAddShow() {
        SqlController.addShow(67, 1, "20010101", "gold", 30, 4, 5);
    }

    /**
     *
     * Method: addStaff(String username, String password)
     *
     */
    @Test
    public void testAddStaff_0() {
        Register register = SqlController.addStaff("", "123456abcd");
        assertEquals(Register.RegisterStatus.EMPTY_USERNAME, register.getRegisterStatus());
    }

    @Test
    public void testAddStaff_1() {
        Register register = SqlController.addStaff("Bob", "");
        assertEquals(Register.RegisterStatus.EMPTY_PASSWORD, register.getRegisterStatus());
    }

    @Test
    public void testAddStaff_2() {
        Register register = SqlController.addStaff("Conor", "123456abcd");
        assertEquals(Register.RegisterStatus.DUPLICATE_USERNAME, register.getRegisterStatus());
    }

    @Test
    public void testAddStaff_3() {
        Register register = SqlController.addStaff("Bob", "123456");
        assertEquals(Register.RegisterStatus.INVALID_PASSWORD, register.getRegisterStatus());
    }
}
