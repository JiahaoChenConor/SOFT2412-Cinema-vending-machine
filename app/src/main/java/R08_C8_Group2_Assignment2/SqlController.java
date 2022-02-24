package R08_C8_Group2_Assignment2;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.beans.Customizer;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SqlController {

    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://localhost:3306";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "admin";


    // Connections
    static Connection conn = null;
    static Statement stmt = null;


    public static void connectServer() {
        //Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

    public static void prepareForStatement() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException e){
            e.printStackTrace();
        }

        String database = "USE CINEMA";
        try {
            stmt.executeUpdate(database);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void disconnectDataBase() {
        try {
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // used for test
    public static void initDataBase() {
        String initDataBase = "CREATE DATABASE IF NOT EXISTS CINEMA";
        try {
            stmt.executeUpdate(initDataBase);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // used for test
    public static void dropDatabase() {
        String dropCasts = "DROP TABLE IF EXISTS Casts";
        String dropFilm = "DROP TABLE IF EXISTS Film";
        String dropCinemaFilm = "DROP TABLE IF EXISTS Cinema_Film";
        String dropCinema = "DROP TABLE IF EXISTS Cinema";
        String dropUser = "DROP TABLE IF EXISTS User";
        String dropTicket = "DROP TABLE IF EXISTS Ticket";
        String dropCancelledTransaction = "DROP TABLE IF EXISTS CancelledTransaction";
        String dropGiftCard = "DROP TABLE IF EXISTS GiftCard";
        try {
            stmt.executeUpdate(dropCasts);
            stmt.executeUpdate(dropFilm);
            stmt.executeUpdate(dropCinemaFilm);
            stmt.executeUpdate(dropCinema);
            stmt.executeUpdate(dropUser);
            stmt.executeUpdate(dropTicket);
            stmt.executeUpdate(dropCancelledTransaction);
            stmt.executeUpdate(dropGiftCard);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //used for test
    // TODO: edit
    public static void initTable() {
        String initCasts = "CREATE TABLE IF NOT EXISTS Casts(\n" +
                "    cast_id INT PRIMARY KEY ,\n" +
                "    cast_name VARCHAR(32) NOT NULL,\n" +
                "    film_id INT\n" +
                ")";
        String initFilm = "CREATE TABLE IF NOT EXISTS Film(\n" +
                "    film_id INT PRIMARY KEY,\n" +
                "    film_name VARCHAR(38) NOT NULL ,\n" +
                "    classification VARCHAR(16) NOT NULL,\n" +
                "    synopsis VARCHAR(500) NOT NULL ,\n" +
                "    release_date DATE NOT NULL,\n" +
                "    director VARCHAR(32) NOT NULL\n" +
                ")";
        String initCinemaFilm = "CREATE TABLE IF NOT EXISTS Cinema_Film(\n" +
                "    show_id INT PRIMARY KEY,\n" +
                "    film_id INT,\n" +
                "    cinema_id INT ,\n" +
                "    timestamp_film TIMESTAMP ,\n" +
                "    screen VARCHAR(10),\n" +
                "    price DECIMAL NOT NULL,\n" +
                "    num_seat_rows INT,\n" +
                "    num_seat_columns INT\n" +
                ")";
        String initCinema = "CREATE TABLE IF NOT EXISTS Cinema(\n" +
                "    cinema_id INT PRIMARY KEY,\n" +
                "    location VARCHAR(30) NOT NULL\n" +
                ")";
        String initUser = "CREATE TABLE IF NOT EXISTS User(\n" +
                "    username VARCHAR(255) NOT NULL,\n" +
                "    password VARCHAR(255) NOT NULL,\n" +
                "    role VARCHAR(255) NOT NULL DEFAULT 'CUSTOMER'\n" +
                ");";
        String initTicket = "CREATE TABLE IF NOT EXISTS Ticket(\n" +
                "    ticket_id INT PRIMARY KEY ,\n" +
                "    show_id INT,\n" +
                "    type_person VARCHAR(30),\n" +
                "    seat_row INT,\n" +
                "    seat_column INT,\n" +
                "    status VARCHAR(10) DEFAULT 'purchased',\n" +
                "    username VARCHAR(255) NOT NULL\n" +
                ")";
        String initCancelledTransaction = "CREATE TABLE IF NOT EXISTS CancelledTransaction(\n" +
                "    transaction_id INT PRIMARY KEY ,\n" +
                "    user VARCHAR(255) DEFAULT 'ANONYMOUS',\n" +
                "    reason VARCHAR(255),\n" +
                "    timestamp_cancelled TIMESTAMP\n" +
                ")";
        String initGiftCard = "CREATE TABLE IF NOT EXISTS GiftCard(\n" +
                "    card_number CHAR(16) NOT NULL,\n" +
                "    PIN         CHAR(4) NOT NULL,\n" +
                "    balance     DECIMAL(6,2)  NOT NULL,\n" +
                "    is_lost     BOOLEAN DEFAULT FALSE\n" +
                ");";
        String initCreditCard = "CREATE TABLE IF NOT EXISTS CreditCard (\n" +
                "    cardholder_name VARCHAR(255) NOT NULL,\n" +
                "    credit_card_number VARCHAR(255) NOT NULL,\n" +
                "    username VARCHAR(255),\n" +
                "    PRIMARY KEY (cardholder_name, credit_card_number, username)\n" +
                ")";

        try {
            stmt.executeUpdate(initCasts);
            stmt.executeUpdate(initFilm);
            stmt.executeUpdate(initCinemaFilm);
            stmt.executeUpdate(initCinema);
            stmt.executeUpdate(initUser);
            stmt.executeUpdate(initCancelledTransaction);
            stmt.executeUpdate(initTicket);
            stmt.executeUpdate(initGiftCard);
            stmt.executeUpdate(initCreditCard);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //used for test
    // TODO: edit
    public static void resetData() {
        String resetCasts = "INSERT INTO Casts VALUES\n" +
                "(1, 'Jack', 1),\n" +
                "(2, 'Julia', 1),\n" +
                "(3, 'Brad Pit', 1),\n" +
                "(4, 'George', 2),\n" +
                "(5, 'Robert', 3)";
        String resetFilm = "INSERT INTO Film VALUES\n" +
                "(1, 'ACADEMY DINOSAUR', 'PG', 'A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies', '2021-1-1', 'Alan'),\n" +
                "(2, 'ACE GOLDFINGER', 'PG', 'A Astounding Epistle of a Database Administrator And a Explorer who must Find a Car in Ancient China', '2021-1-1', 'Daniel'),\n" +
                "(3, 'ADAPTATION HOLES', 'PG+', 'A Astounding Reflection of a Lumberjack And a Car who must Sink a Lumberjack in A Baloon Factory', '2021-1-1', 'Tom')";
        String resetCinemaFilm = "INSERT INTO Cinema_Film VALUES\n" +
                "(1, 1, 1, '2021-12-1 18:00:00', 'Gold', 29.9, 15, 12),\n" +
                "(2, 1, 1, '2021-12-2 18:00:00', 'Silver', 29.9, 12, 15),\n" +
                "(3, 2, 2, '2021-12-2 18:00:00', 'Silver', 29.9, 12, 10)";
        String resetCinema = "INSERT INTO Cinema VALUES\n" +
                "(1, 'TownHall'),\n" +
                "(2, 'Burwood')";
        String resetUser = "INSERT INTO User VALUES\n" +
                "('Conor', '123456abc', 'CUSTOMER'),\n" +
                "('ZhiQian', 'abcdefg123', 'CUSTOMER'),\n" +
                "('Qihan', 'caiqihan123', 'MANAGER');";
        String resetTicket = "INSERT INTO Ticket VALUES\n" +
                "(1, 1, 'child', 1, 1, 'purchased', 'Conor'),\n" +
                "(2, 2, 'adult', 2, 3, 'purchased', 'Conor'),\n" +
                "(3, 3, 'student', 4, 4, 'purchased', 'Conor');";
        String resetGiftCard = "INSERT INTO GiftCard VALUES\n" +
                "('11111111111111GC', '5432', 1000.00, false),\n" +
                "('22222222222222GC', '8765', 500.00, true);";

        try {
            stmt.executeUpdate(resetCasts);
            stmt.executeUpdate(resetFilm);
            stmt.executeUpdate(resetCinemaFilm);
            stmt.executeUpdate(resetCinema);
            stmt.executeUpdate(resetUser);
            stmt.executeUpdate(resetTicket);
            stmt.executeUpdate(resetGiftCard);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<String> getLocations() {
        String sql = "SELECT location from Cinema";
        List<String> cinemas = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cinemas.add(rs.getString("location"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cinemas;
    }

    public static List<Film> getFilms(){
        String sql = "SELECT * FROM Film";

        List<Film> films = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Film film = new Film();
                film.setFilmId(rs.getInt("film_id"));
                film.setName(rs.getString("film_name"));
                film.setClassification(rs.getString("classification"));
                film.setSynopsis(rs.getString("synopsis"));
                film.setReleaseDate(rs.getString("release_date"));
                film.setDirector(rs.getString("director"));

                String getCasts = String.format("SELECT cast_name FROM Casts WHERE film_id = %d", rs.getInt("film_id"));
                ResultSet rs2 = stmt.executeQuery(getCasts);
                while (rs2.next()){
                    film.addCast(rs2.getString("cast_name"));
                }
                films.add(film);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return films;
    }

    public static Film getFilmDetails(int filmId){
        List<Film> films = getFilms();
        Film curFilm = null;
        for (Film film : films){
            if (film.getFilmId() == filmId){
                curFilm = film;
            }
        }

        String details = String.format("SELECT * FROM Cinema_Film NATURAL JOIN Cinema WHERE film_id = %d", filmId);
        try {
            ResultSet rs = stmt.executeQuery(details);
            while (rs.next()){
                FilmShow filmShow = new FilmShow();
                filmShow.setFilmId(rs.getInt("film_id"));
                filmShow.setCinemaId(rs.getInt("cinema_id"));
                filmShow.setTime(rs.getString("timestamp_film"));
                filmShow.setScreen(rs.getString("screen"));
                filmShow.setPrice(rs.getDouble("price"));
                filmShow.setLocation(rs.getString("location"));

                // ######################## Sprint2
                filmShow.setShowId(rs.getInt("show_id"));
                filmShow.setNumSeatRows(rs.getInt("num_seat_rows"));
                filmShow.setNumSeatColumns(rs.getInt("num_seat_columns"));
                // ######################## Sprint2
                assert curFilm != null;
                curFilm.addFilmShow(filmShow);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return curFilm;
    }

    public static List<FilmShow> filter(String location){
        List<FilmShow> filmShows = new ArrayList<>();
        String details = String.format("SELECT * FROM Cinema_Film NATURAL JOIN Cinema NATURAL JOIN Film WHERE location = '%s' ORDER BY timestamp_film", location);
        try {
            ResultSet rs = stmt.executeQuery(details);
            while (rs.next()){
                FilmShow filmShow = new FilmShow();
                filmShow.setFilmId(rs.getInt("film_id"));
                filmShow.setFilmName(rs.getString("film_name"));
                filmShow.setCinemaId(rs.getInt("cinema_id"));
                filmShow.setTime(rs.getString("timestamp_film"));
                filmShow.setScreen(rs.getString("screen"));
                filmShow.setPrice(rs.getDouble("price"));
                filmShow.setLocation(rs.getString("location"));
                filmShows.add(filmShow);


                // ######################## Sprint2
                filmShow.setShowId(rs.getInt("show_id"));
                filmShow.setNumSeatRows(rs.getInt("num_seat_rows"));
                filmShow.setNumSeatColumns(rs.getInt("num_seat_columns"));
                // ######################## Sprint2
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return filmShows;
    }

    public static boolean checkUserExists(String username) {
        String usernames = String.format("SELECT * FROM User WHERE username = '%s'", username);
        try {
            ResultSet resultSet = stmt.executeQuery(usernames);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //check if password is valid in register
    public static boolean checkValidPassword(String password){
        if (password != null && password.length() >= 8){
            boolean letter = false;
            boolean digit = false;
            for (int i = 0; i < password.length(); i++){
                char c = password.charAt(i);
                if (Character.isAlphabetic(c)){
                    letter = true;
                }
                if (Character.isDigit(c)){
                    digit = true;
                }
            }

            return letter && digit;
        }

        return false;
    }

    public static Register registerUser(String username, String password) {
        String newUser = String.format("INSERT INTO User VALUES ('%s', '%s', '%s')", username, password, User.UserType.CUSTOMER.toString());
        try {
            if (username == null || username.strip().equals("")){
                return new Register(Register.RegisterStatus.EMPTY_USERNAME);
            }else if(password == null || password.strip().equals("")){
                return new Register(Register.RegisterStatus.EMPTY_PASSWORD);
            }else{
                if (checkUserExists(username)) {
                    return new Register(Register.RegisterStatus.DUPLICATE_USERNAME);
                }else if(!checkValidPassword(password)){
                    return new Register(Register.RegisterStatus.INVALID_PASSWORD);
                }else {
                    // Successful
                    stmt.execute(newUser);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Register(Register.RegisterStatus.SUCCESSFUL);
    }

    public static Register addStaff(String username, String password){
        String newUser = String.format("INSERT INTO User VALUES ('%s', '%s', '%s')", username, password, User.UserType.STAFF.toString());
        try {
            if (username == null || username.strip().equals("")){
                return new Register(Register.RegisterStatus.EMPTY_USERNAME);
            }else if(password == null || password.strip().equals("")){
                return new Register(Register.RegisterStatus.EMPTY_PASSWORD);
            }else{
                if (checkUserExists(username)) {
                    return new Register(Register.RegisterStatus.DUPLICATE_USERNAME);
                }else if(!checkValidPassword(password)){
                    return new Register(Register.RegisterStatus.INVALID_PASSWORD);
                }else {
                    // Successful
                    stmt.execute(newUser);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Register(Register.RegisterStatus.SUCCESSFUL);
    }

    public static int deleteStaff(String username){
        if (!checkUserExists(username)){
            return -1;
        }

        String deleteUser = String.format("DELETE FROM User WHERE username = '%s' AND role = '%s'", username, "STAFF");
        try{
            stmt.execute(deleteUser);
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }

        return 0;

    }



    public static User login(String username, String password) {
        User newUser = new User();
        if (checkUserExists(username)) {
            String usernames = String.format("SELECT * FROM User WHERE username = '%s'", username);
            try {
                ResultSet rs = stmt.executeQuery(usernames);
                while (rs.next()) {
                    newUser.setUsername(rs.getString("username"));
                    newUser.setPassword(rs.getString("password"));
                    String userType = rs.getString("role");
                    if (userType.equalsIgnoreCase("manager")){
                        newUser.setUserType(User.UserType.MANAGER);
                    }else if (userType.equalsIgnoreCase("staff")){
                        newUser.setUserType(User.UserType.STAFF);
                    }else if (userType.equalsIgnoreCase("customer")){
                        newUser.setUserType(User.UserType.CUSTOMER);
                    }
                }

                if (password.equals(newUser.getPassword())) {
                    newUser.creditCards = getCreditCards(username);
                    newUser.setLoggedIn(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return newUser;
    }


    public static List<FilmShow> getFilmShowsByFilm(int filmId){
        List<FilmShow> filmShows = new ArrayList<>();
        String details = String.format("SELECT * FROM Cinema_Film NATURAL JOIN Cinema NATURAL JOIN Film WHERE film_id = '%s' ORDER BY timestamp_film", filmId);
        try {
            ResultSet rs = stmt.executeQuery(details);
            while (rs.next()){
                FilmShow filmShow = new FilmShow();
                filmShow.setFilmId(rs.getInt("film_id"));
                filmShow.setFilmName(rs.getString("film_name"));
                filmShow.setCinemaId(rs.getInt("cinema_id"));
                filmShow.setTime(rs.getString("timestamp_film"));
                filmShow.setScreen(rs.getString("screen"));
                filmShow.setPrice(rs.getDouble("price"));
                filmShow.setLocation(rs.getString("location"));
                filmShows.add(filmShow);


                // ######################## Sprint2
                filmShow.setShowId(rs.getInt("show_id"));
                filmShow.setNumSeatRows(rs.getInt("num_seat_rows"));
                filmShow.setNumSeatColumns(rs.getInt("num_seat_columns"));
                // ######################## Sprint2
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return filmShows;

    }

    public static int getMaxTicketId(){
        int maxTicketNum = -1;
        // the new column name is max(transaction_number)
        String get = "SELECT MAX(ticket_id) AS ticket_id_max " +
                "FROM Ticket ";
        try {
            ResultSet rs = stmt.executeQuery(get);
            while (rs.next()){
                maxTicketNum = rs.getInt("ticket_id_max");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return maxTicketNum;
    }


    public static Ticket buyTicket(int showId, Ticket.Person personType, int seat_row, int seat_column, String username){
        // check the show exists
        Ticket ticket = new Ticket();
        String show = String.format("SELECT * FROM Cinema_Film WHERE show_id = %s", showId);
        int total_rows = -1;
        int total_columns = -1;
        try {
            ResultSet rs = stmt.executeQuery(show);
            while (rs.next()){
                total_rows = rs.getInt("num_seat_rows");
                total_columns = rs.getInt("num_seat_columns");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (total_rows == -1 || total_columns == -1){
            ticket.setState(Ticket.State.ERROR);
            return ticket;
        }

        // check the seat exists
        if (seat_row <= 0 || seat_row > total_rows || seat_column <= 0 || seat_column > total_columns){
            ticket.setState(Ticket.State.OUT_OF_RANGE);
            return ticket;
        }

        // check the seat is occupied or not
        String checkTicket = String.format("SELECT * FROM Ticket WHERE seat_row = %s AND seat_column = %s AND show_id = %s AND status = 'purchased'", seat_row, seat_column, showId);
        boolean exists = false;
        try {
            ResultSet rs = stmt.executeQuery(checkTicket);
            if(rs.next()){
               exists = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (exists){
            ticket.setState(Ticket.State.OCCUPIED);
            return ticket;
        }

        // buy the ticket
        int newId = getMaxTicketId() + 1;
        String addTicket = String.format("INSERT INTO Ticket VALUES (%s, %s, '%s', %s, %s, 'purchased', '%s')", newId,showId, personType.toString().toLowerCase(), seat_row, seat_column, username);
        try {
            stmt.executeQuery(addTicket);
        }catch (SQLException e){
            e.printStackTrace();
        }

        ticket.setTicket_id(newId);
        ticket.setState(Ticket.State.SUCCESS);
        ticket.setPerson(personType);
        ticket.setSeat_row(seat_row);
        ticket.setSeat_column(seat_column);

        return ticket;
    }

    public static void cancelTicketTransaction(int ticketId) {
        if (ticketId >= 0) {
            String updateFilmName = String.format("UPDATE Ticket SET status = 'cancelled' WHERE ticket_id = %s", ticketId);
            try {
                stmt.executeUpdate(updateFilmName);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void refundTicketTransaction(int ticketId) {
        if (ticketId >= 0) {
            String updateFilmName = String.format("UPDATE Ticket SET status = 'refunded' WHERE ticket_id = %s", ticketId);
            try {
                stmt.executeUpdate(updateFilmName);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static String getBookingInformationOfShow(int showId){
        String show = String.format("SELECT * FROM Cinema_Film WHERE show_id = %s", showId);
        int total_rows = -1;
        int total_columns = -1;
        try {
            ResultSet rs = stmt.executeQuery(show);
            while (rs.next()){
                total_rows = rs.getInt("num_seat_rows");
                total_columns = rs.getInt("num_seat_columns");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (total_rows == -1 || total_columns == -1){
            return "Not exists such show";
        }

        int[][] seats = new int[total_rows][total_columns];
        String bookings = String.format("SELECT * FROM Ticket WHERE show_id = %s AND status = 'purchased'", showId);

        try {
            ResultSet rs = stmt.executeQuery(bookings);
            while(rs.next()){
                int rowIndex = rs.getInt("seat_row") - 1;
                int columnIndex = rs.getInt("seat_column") - 1;
                seats[rowIndex][columnIndex] = 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        StringBuilder sbColumnNum = new StringBuilder();
        sbColumnNum.append("\n");
        sbColumnNum.append("     ".repeat(Math.max(0, total_columns / 2 - 3)));
        sbColumnNum.append("S c r e e n\n\n");
        sbColumnNum.append("    ");
        StringBuilder sb = new StringBuilder();

        int divider_row = total_rows / 3;
        for (int i = 0; i < total_rows; i ++){
            if (i == divider_row || i == divider_row * 2){
                sb.append("\n");
            }

            sb.append(i + 1);
            if (i >= 9){
                sb.append(" ");
            }else {
                sb.append("  ");
            }


            for (int j = 0; j < total_columns; j++){
                if (seats[i][j] == 0){
                    sb.append("✅ ");
                }else{
                    sb.append("❌ ");
                }
                if (j == 2 || j == total_columns - 4){
                    sb.append(" ");
                }

                if (i == 0){
                    if (j + 2 < 10){
                        sbColumnNum.append(j + 1).append("  ");
                    }else{
                        sbColumnNum.append(j + 1).append(" ");
                    }

                    if (j == 2 || j == total_columns - 4){
                        sbColumnNum.append(" ");
                    }
                }
            }
            sb.append("\n");
        }

        return sbColumnNum.append("\n").append(sb).toString();
    }

    public static List<Ticket> getPurchasedTickets(String username) {
        String show = String.format("SELECT * " +
                "FROM Ticket t " +
                "LEFT JOIN Cinema_Film cf " +
                "USING (show_id) " +
                "LEFT JOIN Film f " +
                "USING (film_id) WHERE t.username = '%s'", username);
        List<Ticket> tickets = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery(show);
            while (rs.next()){
                Ticket ticket = new Ticket();
                ticket.setTicket_id(rs.getInt("ticket_id"));
                ticket.setSeat_row(rs.getInt("seat_row"));
                ticket.setSeat_column(rs.getInt("seat_column"));
                ticket.setShow_id(rs.getInt("show_id"));
                switch (rs.getString("status")) {
                    case "purchased":
                        ticket.setState(Ticket.State.SUCCESS);
                        break;
                    case "cancelled":
                        ticket.setState(Ticket.State.CANCELLED);
                        break;
                    case "refunded":
                        ticket.setState(Ticket.State.REFUNDED);
                        break;
                }
                switch (rs.getString("type_person")) {
                    case "adult":
                        ticket.setPerson(Ticket.Person.ADULT);
                        break;
                    case "child":
                        ticket.setPerson(Ticket.Person.CHILD);
                        break;
                    case "student":
                        ticket.setPerson(Ticket.Person.STUDENT);
                        break;
                }
                ticket.setCinemaName(rs.getString("cinema_id"));
                ticket.setFilmName(rs.getString("film_name"));
                ticket.setTime(rs.getString("timestamp_film"));
                tickets.add(ticket);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tickets;
    }

    public static int getMaxTranId(){
        int maxTranNum = -1;
        // the new column name is max(transaction_number)
        String get = "SELECT MAX(transaction_id) AS tran_id_max " +
                "FROM CancelledTransaction ";
        try {
            ResultSet rs = stmt.executeQuery(get);
            while (rs.next()){
                maxTranNum = rs.getInt("tran_id_max");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return maxTranNum;
    }

    public static CancelledTransaction cancelTransaction(CancelledTransaction.Reason reason, String username){
        int new_id = getMaxTranId() + 1;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String newTran = String.format("INSERT INTO CancelledTransaction VALUES ('%s', '%s', '%s', '%s')", new_id, username, reason.toString(), timestamp);
        try {
          stmt.executeUpdate(newTran);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new CancelledTransaction(new_id, reason, username, timestamp.toString());
    }

    public static String reportOfCancelledTransaction(){
        StringBuilder sbDetails = new StringBuilder();
        String allCancelledTransactions = "SELECT * FROM CancelledTransaction";
        int numFeedback = 0;
        HashMap<String, Integer> cancelTran = new HashMap<>();
        try {
            ResultSet rs = stmt.executeQuery(allCancelledTransactions);
            while (rs.next()){
                String id = rs.getString("transaction_id");
                String reason = rs.getString("reason");
                String username = rs.getString("user");
                String time = rs.getString("timestamp_cancelled");
                // TODO:
                sbDetails.append("Transaction ID: ").append(id)
                        .append(", Reason: ").append(reason)
                        .append(", Username: ").append(username).
                        append(", Time: ").append(time).append("\n");
                if (cancelTran.containsKey(reason)){
                    cancelTran.put(reason, cancelTran.get(reason) + 1);
                }else{
                    cancelTran.put(reason, 1);
                }
                numFeedback++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("########## Summary ############\n");
        for (String res : cancelTran.keySet()){
            double percentage = cancelTran.get(res) / (numFeedback * 1.0) * 100;
            String percent = String.format("%.2f", percentage);
            sb.append(res).append(" : ").append(percent).append("%").append("\n");
        }

        sb.append("\n########## Details ############\n").append(sbDetails);


        return sb.toString();
    }

    // update the film details by film_id
    public static void updateFilmDetails(int filmId, String newFilmName, String newClassification, String newSynopsis, String newReleaseDate, String newDirector) {
        updateFilmName(filmId, newFilmName);
        updateClassification(filmId, newClassification);
        updateSynopsis(filmId, newSynopsis);
        updateReleaseDate(filmId, newReleaseDate);
        updateDirector(filmId, newDirector);

    }

    public static void updateFilmName(int filmId, String newFilmName) {
        if (newFilmName != null) {
            String updateFilmName = String.format("UPDATE film SET film_name = '%s' WHERE film_id = %d", newFilmName, filmId);
            try {
                stmt.executeUpdate(updateFilmName);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void updateClassification(int filmId, String newClassification) {
        if (newClassification != null) {
            String updateClassification = String.format("UPDATE film SET classification = '%s' WHERE film_id = %d", newClassification, filmId);
            try {
                stmt.executeUpdate(updateClassification);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void updateSynopsis(int filmId, String newSynopsis) {
        if (newSynopsis != null) {
            String updateSynopsis = String.format("UPDATE film SET synopsis = '%s' WHERE film_id = %d", newSynopsis, filmId);
            try {
                stmt.executeUpdate(updateSynopsis);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void updateReleaseDate(int filmId, String newReleaseDate) {
        if (newReleaseDate != null) {
            String updateReleaseDate = String.format("UPDATE film SET release_date = '%s' WHERE film_id = %d", newReleaseDate, filmId);
            try {
                stmt.executeUpdate(updateReleaseDate);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void updateDirector(int filmId, String newDirector) {
        if (newDirector != null) {
            String updateDirector = String.format("UPDATE film SET director = '%s' WHERE film_id = %d", newDirector, filmId);
            try {
                stmt.executeUpdate(updateDirector);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static boolean checkGiftCardExists(String cardNumber) {
        String usernames = String.format("SELECT * FROM GiftCard WHERE card_number = '%s'", cardNumber);
        try {
            ResultSet resultSet = stmt.executeQuery(usernames);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static GiftCard buyGiftCard(String cardNumber, String pin, double amount) {
        GiftCard newGiftCard = new GiftCard();

        if (cardNumber.length() != 16 || !cardNumber.endsWith("GC")) {
            newGiftCard.setBuyStatus(GiftCard.buyStatus.INCORRECT_FORMAT_OF_CARD_NUMBER);
            return newGiftCard;
        }

        if (checkGiftCardExists(cardNumber)) {
            newGiftCard.setBuyStatus(GiftCard.buyStatus.DUPLICATE_CARD);
            return newGiftCard;
        }

        if (pin.length() != 4) {
            newGiftCard.setBuyStatus(GiftCard.buyStatus.INCORRECT_FORMAT_OF_PIN);
            return newGiftCard;
        }

        if (amount > 1000 || amount <= 0) {
            newGiftCard.setBuyStatus(GiftCard.buyStatus.LARGE_AMOUNT);
            return newGiftCard;
        }

        if (amount % 5 != 0) {
            newGiftCard.setBuyStatus(GiftCard.buyStatus.INAPPROPRIATE_AMOUNT);
            return newGiftCard;
        }

        String newCard = String.format("INSERT INTO GiftCard VALUES ('%s', '%s', %f, false)", cardNumber, pin, amount);
        try {
            stmt.executeUpdate(newCard);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        newGiftCard.setBuyStatus(GiftCard.buyStatus.SUCCESS);
        return newGiftCard;
    }

    public static GiftCard useGiftCard(String cardNumber, String pin, double price) {
        GiftCard card = new GiftCard();
        if (checkGiftCardExists(cardNumber)) {
            String cardDetail = String.format("SELECT * FROM GiftCard WHERE card_number = '%s'", cardNumber);
            try {
                ResultSet rs = stmt.executeQuery(cardDetail);
                boolean isLost = false;
                while (rs.next()) {
                    card.setCardNumber(rs.getString("card_number"));
                    card.setPIN(rs.getString("PIN"));
                    card.setBalance(rs.getDouble("balance"));
                    isLost = rs.getBoolean("is_lost");
                }

                if (isLost) {
                    card.setLoginStatus(GiftCard.cardLoginStatus.LOST);
                    return card;
                }

                if (pin.equals(card.getPIN())) {
                    card.setLoginStatus(GiftCard.cardLoginStatus.LOGGED_IN);
                    if (price > card.getBalance()) {
                        card.setLoginStatus(GiftCard.cardLoginStatus.INSUFFICIENT_BALANCE);
                        return card;
                    } else {
                        double currentBalance = card.getBalance() - price;
                        String updateCardBalance = String.format("UPDATE GiftCard SET balance = %f WHERE card_number = '%s'", currentBalance, cardNumber);
                        stmt.executeUpdate(updateCardBalance);
                    }
                } else {
                    card.setLoginStatus(GiftCard.cardLoginStatus.INCORRECT_PIN);
                    return card;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            card.setLoginStatus(GiftCard.cardLoginStatus.NONEXISTENT_CARD_NUMBER);
            return card;
        }
        return card;
    }

    public static String ShowAllGiftCards(){
        String cardDetails = "SELECT * FROM GiftCard";
        StringBuilder sb = new StringBuilder();
        try {
            ResultSet rs = stmt.executeQuery(cardDetails);
            while (rs.next()){

                String cardNum = rs.getString("card_number");
                sb.append("card Num: ").append(cardNum).append(", ");
                String pin = rs.getString("PIN");
                sb.append("PIN: ").append(pin).append(", ");
                double balance = rs.getDouble("balance");
                sb.append("balance: ").append(balance).append(", ");
                boolean lost = rs.getBoolean("is_lost");
                sb.append("lost: ").append(lost).append("\n");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb.toString();
    }

    public static GiftCard setGiftCardAsLost(String giftCardNumber){
        String cardDetails = String.format("UPDATE GiftCard SET is_lost = true WHERE card_number = '%s'", giftCardNumber);
        if (!checkGiftCardExists(giftCardNumber)){
            return null;
        }

        try {
            stmt.executeQuery(cardDetails);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        GiftCard gc = new GiftCard();
        gc.setLoginStatus(GiftCard.cardLoginStatus.LOST);
        return gc;
    }

    public static GiftCard setGiftCardAsNotLost(String giftCardNumber){
        String cardDetails = String.format("UPDATE GiftCard SET is_lost = false WHERE card_number = '%s'", giftCardNumber);
        if (!checkGiftCardExists(giftCardNumber)){
            return null;
        }

        try {
            stmt.executeQuery(cardDetails);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        GiftCard gc = new GiftCard();
        gc.setLoginStatus(GiftCard.cardLoginStatus.LOST);
        return gc;
    }

    public static GiftCard restoreGiftCardAsNotLost(String giftCardNumber){
        String cardDetails = String.format("UPDATE GiftCard SET is_lost = false WHERE card_number = '%s'", giftCardNumber);
        if (!checkGiftCardExists(giftCardNumber)){
            return null;
        }

        try {
            stmt.executeQuery(cardDetails);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new GiftCard();
    }



    public static void saveCreditCard(User user, CreditCard card) {
        String newCard = String.format("INSERT INTO CreditCard VALUES ('%s', '%s', '%s')", card.name, card.number, user.getUsername());
        try {
            stmt.executeUpdate(newCard);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<CreditCard> getCreditCards(String username) {
        List<CreditCard> cards = new ArrayList<>();
        String show = String.format("SELECT * FROM CreditCard where username = '%s'", username);
        try {
            ResultSet rs = stmt.executeQuery(show);
            while (rs.next()){
                CreditCard card = new CreditCard(rs.getString("cardholder_name"), rs.getString("credit_card_number"));
                cards.add(card);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cards;
    }


    public static String getAllStaff(){
        String getAllStaff = "SELECT * FROM User WHERE role = 'STAFF'";

        StringBuilder sb = new StringBuilder();

        try {
            ResultSet rs = stmt.executeQuery(getAllStaff);
            while (rs.next()){
                String username = rs.getString("username");
                sb.append(username).append("\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static void importValidCard(){

        String find = "SELECT * FROM CreditCard WHERE username = 'root'";
        try{
            ResultSet rs = stmt.executeQuery(find);
            if (rs.next()){
                return;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        InputStream configStream = SqlController.class.getResourceAsStream("/credit_cards.json");
        JSONParser jsonParser = new JSONParser();
        try {
            assert configStream != null;
            JSONArray arr = (JSONArray) jsonParser.parse(new InputStreamReader(configStream, "UTF-8"));
            for (Object card: arr){
                JSONObject jo = (JSONObject) card;
                String name = (String) jo.get("name");
                String number = (String) jo.get("number");
                String newCard = String.format("INSERT INTO CreditCard VALUES ('%s', '%s', '%s')", name, number, "root");
                try {
                    stmt.executeUpdate(newCard);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String showAllCreditCards(){
        StringBuilder sb = new StringBuilder();
        String allCards = "SELECT * FROM CreditCard WHERE username = 'root' OR username = 'deleted'";
        try{
            ResultSet rs = stmt.executeQuery(allCards);
            while (rs.next()){
                String cardholderName = rs.getString("cardholder_name");
                String creditCardNumber = rs.getString("credit_card_number");
                String creditStatus = rs.getString("username");
                if (creditStatus.equals("root")){
                    creditStatus = "valid";
                }else{
                    creditStatus = "invalid";
                }
                sb.append("Holder Name: ").append(cardholderName).append(", ").append("creditCardNumber: ").append(creditCardNumber).append(", Status: ").append(creditStatus).append("\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static boolean checkValidCreditCard(String holderName, String cardNumber){
        String usernames = String.format("SELECT * FROM CreditCard WHERE cardholder_name = '%s' AND credit_card_number = '%s'", holderName, cardNumber);
        boolean valid = false;
        try {
            ResultSet resultSet = stmt.executeQuery(usernames);
            while (resultSet.next()){
                if (resultSet.getString("username").equals("root")){
                    valid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valid;
    }

    public static boolean checkCreditCardExists(String holderName, String cardNumber){
        String exist = String.format("SELECT * FROM CreditCard WHERE cardholder_name = '%s' AND credit_card_number = '%s'", holderName, cardNumber);
        try {
            ResultSet resultSet = stmt.executeQuery(exist);
            return (resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addCreditCard(String name, String number) {
        if (!checkCreditCardExists(name, number))
            return false;
        String add = String.format("INSERT INTO CreditCard VALUES ('%s', '%s', '%s')", name, number, "root");
        try {
            ResultSet resultSet = stmt.executeQuery(add);
            return (resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static CreditCard deleteCreditCard(String holderName, String cardNumber){
        String change = String.format("UPDATE CreditCard SET username = 'deleted' " +
                "WHERE cardholder_name = '%s' AND credit_card_number = '%s' AND username = 'root'", holderName, cardNumber);

        if (!checkCreditCardExists(holderName, cardNumber)){
            return new CreditCard(holderName, cardNumber, CreditCard.CreditStatus.NOT_EXISTS);
        }
        try {
            stmt.executeQuery(change);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new CreditCard(holderName, cardNumber, CreditCard.CreditStatus.DELETED);
    }

    public static CreditCard restoreCreditCard(String holderName, String cardNumber){
        String change = String.format("UPDATE CreditCard SET username = 'root' " +
                "WHERE cardholder_name = '%s' AND credit_card_number = '%s' AND username = 'deleted'", holderName, cardNumber);

        if (!checkCreditCardExists(holderName, cardNumber)){
            return new CreditCard(holderName, cardNumber, CreditCard.CreditStatus.NOT_EXISTS);
        }
        try {
            stmt.executeQuery(change);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new CreditCard(holderName, cardNumber, CreditCard.CreditStatus.VALID);
    }

    public static int getMaxFilmId(){
        int maxNum = -1;
        // the new column name is max(transaction_number)
        String get = "SELECT MAX(film_id) AS film_id_max " +
                "FROM Film ";
        try {
            ResultSet rs = stmt.executeQuery(get);
            while (rs.next()){
                maxNum = rs.getInt("film_id_max");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return maxNum;
    }

    public static int getMaxCastId(){
        int maxNum = -1;
        // the new column name is max(transaction_number)
        String get = "SELECT MAX(cast_id) AS cast_id " +
                "FROM Casts ";
        try {
            ResultSet rs = stmt.executeQuery(get);
            while (rs.next()){
                maxNum = rs.getInt("cast_id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return maxNum;
    }

    public static void addMovie(String name, String classification, String synopsis, String releaseDate, String director, List<String> casts) {
        int newId = getMaxFilmId() + 1;
        String addFilm = String.format("INSERT INTO Film VALUES (%s, '%s', '%s', '%s', '%s', '%s')", newId, name, classification, synopsis, releaseDate, director);
        try {
            stmt.executeQuery(addFilm);
            for (String cast: casts) {
                int newCastId = getMaxCastId() + 1;
                String addCast = String.format("INSERT INTO Casts VALUES (%s, '%s', %s)", newCastId, cast, newId);
                stmt.executeQuery(addCast);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void editMovie(int id, String name, String classification, String synopsis, String releaseDate, String director, List<String> casts) {
        String addFilm = String.format("UPDATE Film SET film_name='%s', classification='%s', synopsis='%s', release_date='%s', director='%s' where film_id=%s", name, classification, synopsis, releaseDate, director, id);
        try {
            stmt.executeQuery(addFilm);
            for (String cast: casts) {
                int newCastId = getMaxCastId() + 1;
                String addCast = String.format("INSERT INTO Casts VALUES (%s, '%s', %s)", newCastId, cast, id);
                stmt.executeQuery(addCast);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteMovie(Film chosen){
        int id = chosen.getFilmId();
        try {
            String removeFilm = String.format("DELETE f, cf, t\n" +
                    "    FROM Film f\n" +
                    "    LEFT JOIN Cinema_Film cf ON f.film_id = cf.film_id\n" +
                    "    LEFT JOIN Ticket t ON cf.show_id = t.show_id\n" +
                    "    WHERE f.film_id = %s", id);
            stmt.executeQuery(removeFilm);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int getMaxShowId(){
        int maxNum = -1;
        // the new column name is max(transaction_number)
        String get = "SELECT MAX(show_id) AS show_id " +
                "FROM Cinema_Film ";
        try {
            ResultSet rs = stmt.executeQuery(get);
            while (rs.next()){
                maxNum = rs.getInt("show_id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return maxNum;
    }

    public static void addShow(int filmId, int cinemaId, String time, String screen, double price, int row, int column) {
        int newId = getMaxShowId() + 1;
        String add = String.format("INSERT INTO Cinema_Film VALUES (%s, %s, %s, '%s', '%s', %s, %s, %s)", newId, filmId, cinemaId, time, screen, price, row, column);
        try {
            stmt.executeQuery(add);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static String dumpMovieReport() {
        String movieReportSql = "SELECT f.film_name, f.synopsis, cf.timestamp_film, c.location, cf.screen, cf.price FROM Film f RIGHT JOIN Cinema_Film cf ON f.film_id = cf.film_id JOIN Cinema c ON cf.cinema_id = c.cinema_id ORDER BY cf.timestamp_film";
        StringBuilder movieReport = new StringBuilder("Film,Synopsis,Time,Location,Screen Type,Price\n");
        try {
            ResultSet rs = stmt.executeQuery(movieReportSql);
            while (rs.next()){
                movieReport.append(rs.getString("film_name")).append(",");
                movieReport.append(rs.getString("synopsis")).append(",");
                movieReport.append(rs.getString("timestamp_film")).append(",");
                movieReport.append(rs.getString("location")).append(",");
                movieReport.append(rs.getString("screen")).append(",");
                movieReport.append(rs.getString("price")).append("\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return movieReport.toString();
    }

    public static String dumpBookingReport() {
        String movieReportSql = "SELECT f.film_name, cf.timestamp_film, c.location, count(t.ticket_id) as booked,\n" +
                "       cf.num_seat_columns * cf.num_seat_rows - count(t.ticket_id) as not_booked FROM\n" +
                "          Cinema_Film cf\n" +
                "          LEFT JOIN Film f ON f.film_id = cf.film_id\n" +
                "          LEFT JOIN Cinema c ON cf.cinema_id = c.cinema_id\n" +
                "          LEFT JOIN Ticket t ON cf.show_id = t.show_id\n" +
                "          GROUP BY cf.timestamp_film, f.film_name, c.location";
        StringBuilder movieReport = new StringBuilder("Film,Time,Location,Booked,Empty\n");
        try {
            ResultSet rs = stmt.executeQuery(movieReportSql);
            while (rs.next()){
                movieReport.append(rs.getString("film_name")).append(",");
                movieReport.append(rs.getString("timestamp_film")).append(",");
                movieReport.append(rs.getString("location")).append(",");
                movieReport.append(rs.getString("booked")).append(",");
                movieReport.append(rs.getString("not_booked")).append("\n");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return movieReport.toString();
    }
}

