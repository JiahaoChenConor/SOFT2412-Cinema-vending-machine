package R08_C8_Group2_Assignment2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.*;

public class UserInterface {
    List<CreditCard> cards;
    private User currentUser;
    Scanner sc;
    private static final ExecutorService l = Executors.newFixedThreadPool(1);

    public UserInterface(){
        sc = new Scanner(System.in);
        currentUser = null;
    }

    /*

    main menu -> available movies -> location and times, details, (book if not guest)
     */
    public void mainMenu(){
        flush();
        // initialize credit cards
        cards = initializeCreditCards();
        // Choose login method
        System.out.println("Cinema System");
        System.out.println("Choose 1 for Guest User");
        System.out.println("Choose 2 for Login");
        System.out.println("Choose 3 for Register");
        System.out.println("Choose 4 for Exit");
        System.out.print("Choose the operation you want to perform:");

        int choice;
        while (true){
            try{
                choice = Integer.parseInt(sc.nextLine());
            }catch (Exception e){
                System.out.print("Invalid input try again");
                continue;
            }
            if (choice < 0 || choice > 4){
                System.out.print("Invalid input try again");
                continue;
            }

            break;
        }
        flush();
        switch(choice){
            case 1:
                guestUserProcess();
            case 2:
                customerProcess();
            case 3:
                register();
                if (currentUser != null)
                    customerProcess();
            case 4:
                break;
        }

        sc.close();
    }

    public void login(){
        flush();
        System.out.println("Cinema System Login");

        boolean success = false;
        while(!success){
            System.out.println("Please Enter Your username: ");
            String lUsername = sc.nextLine().strip();
            System.out.println("Please Enter Your password: ");
            String lPassword;
            if (System.console() == null)
                lPassword = sc.nextLine().strip();
            else
                lPassword = new String(System.console().readPassword());

            Login currentLogin = Login.currentLogin(lUsername, lPassword);

            switch (currentLogin.getLoginStatus()) {
                case SUCCESSFUL:
                    System.out.println("Login successful\n\n");
                    currentUser = SqlController.login(lUsername, lPassword);
                    success = true;
                    break;
                case EMPTY_ERROR:
                    System.out.println("Empty username or password\n\n");
                    System.out.println("Press Enter to continue");
                    sc.nextLine();
                    flush();
                    System.out.println("Cinema System Login");
                    break;
                case INPUT_ERROR:
                    System.out.println("Wrong password or username\n\n");
                    System.out.println("Press Enter to continue");
                    sc.nextLine();
                    flush();
                    System.out.println("Cinema System Login");
                    break;
            }
        }
    }


    public void guestUserProcess(){
        flush();
        boolean isExit = false;
        while (!isExit) {
            System.out.println("You are logged in as Guest");
            System.out.println("Choose 0 to Exit");
            System.out.println("Choose 1 to Search Film");
            System.out.println("Choose 2 to Search Cinema");
            System.out.print("Choose the operation you want to perform:");
            int choice = -1;
            try{
                choice = getInputInt(0, 2);
            }catch (Exception e){
                System.out.print("Invalid input try again");
                continue;
            }

            if (choice < 0 || choice > 2){
                System.out.print("Invalid input try again");
                continue;
            }
            flush();
            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    isExit = true;
                    System.exit(0);
                    break;
                case 1:
                    selectMovie();
                    System.out.println();
                    break;
                case 2:
                    queryAndFilter();
                    System.out.println();
                    break;
            }
            flush();
        }
    }

    public void customerProcess(){
        // Login first
        if (currentUser == null)
            login();
        switch(currentUser.getUserType()) {
            case STAFF:
                staffProcess();
                return;
            case MANAGER:
                managerProcess();
                return;
            case CUSTOMER:
        }
        flush();
        boolean isExit = false;
        while (!isExit) {
            System.out.println("You are logged in as " + currentUser.getUsername());
            System.out.println("Customer View");
            System.out.println("Choose 0 to Exit");
            System.out.println("Choose 1 to Search Film");
            System.out.println("Choose 2 to Search Cinema");
            System.out.println("Choose 3 to View Tickets");
            System.out.print("Choose the operation you want to perform:");
            int choice = -1;
            try{
                choice = getInputInt(0, 3);
            }catch (Exception e){
                System.out.print("Invalid input try again");
                continue;
            }

            if (choice < 0 || choice > 4){
                System.out.print("Invalid input try again");
                continue;
            }
            flush();
            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    isExit = true;
                    System.exit(0);
                    break;
                case 1:
                    selectMovie();
                    System.out.println();
                    break;
                case 2:
                    queryAndFilter();
                    System.out.println();
                    break;
                case 3:
                    displayPurchases();
                    System.out.println("Press Enter to continue");
                    sc.nextLine();
                    break;
            }
            flush();
        }
    }

    /*
     insert movie data, delete movie data,
      modify movie data, add new shows for the upcoming week
      and choose the selected screen sizes.
      maintaining the gift card database/file
      and ensuring all new gift cards are entered accordingly.
     */
    public void staffProcess() {
        flush();
        boolean isExit = false;
        while (!isExit) {
            System.out.println("You are logged in as " + currentUser.getUsername());
            System.out.println("Staff View");
            System.out.println("Choose 0 to Exit");
            System.out.println("Choose 1 to Manage Movie");
            System.out.println("Choose 2 to Manage Gift Card");
            System.out.println("Choose 3 to Get Movie Report");
            System.out.println("Choose 4 to Get Booking Report");
            System.out.print("Choose the operation you want to perform:");
            int choice = -1;
            try{
                choice = getInputInt(0, 5);
            }catch (Exception e){
                System.out.print("Invalid input try again");
                continue;
            }

            if (choice < 0 || choice > 4){
                System.out.print("Invalid input try again");
                continue;
            }
            flush();
            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    isExit = true;
                    System.exit(0);
                    break;
                case 1:
                    manageMovie();
                    System.out.println();
                    break;
                case 2:
                    manageGiftCard();
                    System.out.println();
                    break;
                case 3:
                    dumpMovieReport();
                    System.out.println("Press enter to continue...");
                    sc.nextLine();
                    break;
                case 4:
                    dumpBookingReport();
                    System.out.println("Press enter to continue...");
                    sc.nextLine();
                    break;
            }
            flush();
        }
    }

    public void managerProcess() {
        flush();
        boolean isExit = false;
        while (!isExit) {
            System.out.println("You are logged in as " + currentUser.getUsername());
            System.out.println("Manager View");
            System.out.println("Choose 0 to Exit");
            System.out.println("Choose 1 to Manage Movie");
            System.out.println("Choose 2 to Manage Gift Card");
            System.out.println("Choose 3 to Get Movie Report");
            System.out.println("Choose 4 to Get Booking Report");
            System.out.println("Choose 5 to Manage Staff Members");
            System.out.println("Choose 6 to Manage Credit Card");
            System.out.println("Choose 7 to Get Transaction Report");
            System.out.print("Choose the operation you want to perform:");
            int choice = -1;
            try{
                choice = getInputInt(0, 8);
            }catch (Exception e){
                System.out.print("Invalid input try again");
                continue;
            }

            if (choice < 0 || choice > 7){
                System.out.print("Invalid input try again");
                continue;
            }
            flush();
            switch (choice) {
                case 0:
                    System.out.println("Exiting...");
                    isExit = true;
                    System.exit(0);
                    break;
                case 1:
                    manageMovie();
                    System.out.println();
                    break;
                case 2:
                    manageGiftCard();
                    System.out.println();
                    break;
                case 3:
                    dumpMovieReport();
                    System.out.println("Press enter to continue...");
                    sc.nextLine();
                    break;
                case 4:
                    dumpBookingReport();
                    System.out.println("Press enter to continue...");
                    sc.nextLine();
                    break;
                case 5:
                    manageStaff();
                    break;
                case 6:
                    manageCreditCard();
                    break;
                case 7:
                    dumpTransactionReport();
                    System.out.println("Press enter to continue...");
                    sc.nextLine();
                    break;
            }
            flush();
        }
    }

    public void manageStaff() {
        System.out.println("Manage Staff");
        System.out.println("Choose 0 to return");
        System.out.println("Choose 1 to add staff");
        System.out.println("Choose 2 to remove staff");
        int choice = getInputInt(0, 2);
        switch (choice) {
            case 0:
                return;
            case 1:
                addStaff();
                System.out.println("Press enter to continue...");
                sc.nextLine();
                break;
            case 2:
                deleteStaff();
                System.out.println("Press enter to continue...");
                sc.nextLine();
                break;
        }
    }

    public void addStaff() {
        flush();
        System.out.println("Add Staff");
        System.out.println("Staff username");
        String username = sc.nextLine().strip();
        System.out.println("Staff password");
        String pw;
        if (System.console() == null)
            pw = sc.nextLine().strip();
        else
            pw = new String(System.console().readPassword());
        Register staff = SqlController.addStaff(username, pw);
        switch (staff.getRegisterStatus()){
            case SUCCESSFUL:
                System.out.println("Registration success.\n");
                break;
            case DUPLICATE_USERNAME:
                System.out.println("This username is occupied.\n");
                break;
            case EMPTY_USERNAME:
                System.out.println("Empty username is invalid.\n");
                break;
            case EMPTY_PASSWORD:
                System.out.println("Empty password is invalid.\n");
                break;
            case INVALID_PASSWORD:
                System.out.println("Invalid password. Please enter at least 8 characters. At least 1 letter and 1 number\n");
                break;
        }
    }

    public void deleteStaff() {
        flush();
        System.out.println("Delete Staff");
        System.out.println(SqlController.getAllStaff());
        System.out.println("Input username to delete, or empty to cancel");
        String username = sc.nextLine().strip();
        if (username.length() == 0)
            return;
        int ret = SqlController.deleteStaff(username);
        if (ret != 0)
            System.out.println("Wrong Username!");
        else
            System.out.println("Successful");
    }

    public void dumpMovieReport() {
        System.out.println("Writing upcoming movies & shows to ./movie_report.csv");
        String movieReport = SqlController.dumpMovieReport();
        try (PrintWriter writer = new PrintWriter("movie_report.csv")) {
            writer.write(movieReport);
            System.out.println("Done!");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dumpBookingReport() {
        System.out.println("Writing booking report to ./booking_report.csv");
        String movieReport = SqlController.dumpBookingReport();
        try (PrintWriter writer = new PrintWriter("booking_report.csv")) {
            writer.write(movieReport);
            System.out.println("Done!");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dumpTransactionReport() {
        System.out.println("Writing transaction report to ./transaction_report.txt");
        String report = SqlController.reportOfCancelledTransaction();
        try (PrintWriter writer = new PrintWriter("transaction_report.txt")) {
            writer.write(report);
            System.out.println("Done!");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void manageCreditCard() {
        flush();
        System.out.println("Manage Credit Cards");
        System.out.println("Choose 0 to Return");
        System.out.println("Choose 1 to Add Credit Card");
        System.out.println("Choose 2 to Edit Credit Card");
        int choice = getInputInt(0, 2);
        switch (choice) {
            case 0:
                return;
            case 1:
                addCreditCard();
                System.out.println("Press Enter to continue");
                sc.nextLine();
                break;
            case 2:
                editCreditCard();
                System.out.println("Press Enter to continue");
                sc.nextLine();
                break;
        }
        flush();
    }

    public void addCreditCard() {
        flush();
        System.out.println("Add Credit Card");
        System.out.println("Holder Name");
        String holderName = sc.nextLine().strip();
        System.out.println("Card Number");
        String PIN;
        if (System.console() == null)
            PIN = sc.nextLine().strip();
        else
            PIN = new String(System.console().readPassword());
        boolean status = SqlController.addCreditCard(holderName, PIN);
        if (status) {
            System.out.println("Success! ");
        } else {
            System.out.println("Repeated Card Found");
        }
    }

    public void editCreditCard() {
        flush();
        System.out.println("Edit Credit Cards");
        System.out.println(SqlController.showAllCreditCards());
        System.out.println("Holder Name:");
        String num = sc.nextLine();
        System.out.println("Card Number");
        String PIN;
        if (System.console() == null)
            PIN = sc.nextLine().strip();
        else
            PIN = new String(System.console().readPassword());
        System.out.println("Type \"INVALID\" to set it to invalid, \"VALID\" to restore, empty to return");
        String lost = sc.nextLine().strip();
        CreditCard card = null;
        if (lost.equals("INVALID")){
            card = SqlController.deleteCreditCard(num, PIN);
        } else if (lost.equals("VALID"))
            card = SqlController.restoreCreditCard(num, PIN);
        else if (lost.equals(""))
            return;
        switch (Objects.requireNonNull(card).getCreditStatus()) {
            case NOT_EXISTS:
                System.out.println("Card does not exist");
                break;
            case DELETED:
                System.out.println("Card deleted");
                break;
        }
    }

    public void manageGiftCard() {
        flush();
        System.out.println("Manage Gift Cards");
        System.out.println("Choose 0 to Return");
        System.out.println("Choose 1 to Add Gift Card");
        System.out.println("Choose 2 to Edit Gift Card");
        int choice = getInputInt(0, 2);
        switch (choice) {
            case 0:
                return;
            case 1:
                purchaseGiftCard();
                System.out.println("Press Enter to continue");
                sc.nextLine();
                break;
            case 2:
                editGiftCard();
                break;
        }
        flush();
    }

    public void editGiftCard() {
        flush();
        System.out.println("Edit Gift Cards");
        System.out.println(SqlController.ShowAllGiftCards());
        System.out.println("Card Number:");
        String num = sc.nextLine();
        System.out.println("Type \"LOST\" to set it to lost, \"NOTLOST\" to restore, empty to return");
        String lost = sc.nextLine().strip();
        if (lost.equals("LOST")){
            SqlController.setGiftCardAsLost(num);
        } else if (lost.equals("NOTLOST"))
            SqlController.setGiftCardAsNotLost(num);
    }

    public void manageMovie() {
        flush();
        System.out.println("Manage Movies");
        System.out.println("Choose 0 to Return");
        System.out.println("Choose 1 to Add Movie");
        System.out.println("Choose 2 to Edit Move/Add Show");
        int choice = getInputInt(0, 2);
        switch (choice) {
            case 0:
                return;
            case 1:
                addMovie();
                break;
            case 2:
                editMovie();
                break;
        }
        flush();
    }

    public void addMovie() {
        flush();
        System.out.println("Add Movie");
        System.out.println("Movie Name");
        String movieName = sc.nextLine().strip();
        System.out.println("Movie Rating(Classification)");
        String classification = sc.nextLine().strip();
        System.out.println("Movie Synopsis");
        String synopsis = sc.nextLine().strip();
        System.out.println("Movie Release Date(YYYY-MM-DD)");
        String releaseDate = sc.nextLine().strip();
        System.out.println("Movie Director");
        String director = sc.nextLine().strip();
        System.out.println("Movie Casts, type empty string to go next");
        List<String> casts = new ArrayList<>();
        while(true) {
            String cast = sc.nextLine().strip();
            if (cast.length() == 0)
                break;
            casts.add(cast);
        }
        SqlController.addMovie(movieName, classification, synopsis, releaseDate, director, casts);
    }

    public void editMovie() {
        System.out.println("Please choose from available movies, or type 0 to return");
        List<Film> films = SqlController.getFilms();
        for (int i = 0; i < films.size(); i++) {
            Film film = films.get(i);
            System.out.println((i+1) + ": " + film.getName());
        }

        int filmNo;
        filmNo = getInputInt(0, films.size() + 1);
        if (filmNo == 0)
            return;
        flush();
        // show film detail and let user choose next move
        Film chosen = SqlController.getFilmDetails(filmNo);
        System.out.println("You have chosen: " + chosen.getName());
        System.out.println(chosen.showDetails());
        System.out.println("0 to return");
        System.out.println("1 to edit Film");
        System.out.println("2 to delete Film");
        System.out.println("3 to add Shows");
        int choice = getInputInt(0, 4);
        switch (choice) {
            case 0:
                return;
            case 1:
                editMovieContent(chosen);
                break;
            case 2:
                System.out.println("Are you sure? this will also remove shows and tickets. Type 'DELETE' to proceed");
                if (sc.nextLine().strip().equals("DELETE"))
                    SqlController.deleteMovie(chosen);
                break;
            case 3:
                addShow(chosen);
                break;
        }
    }

    public void editMovieContent(Film chosen) {
        flush();
        System.out.println("Edit Movie, press enter to not make changes");
        System.out.println("Movie Name, Current: " + chosen.getName());
        String movieName = sc.nextLine().strip();
        if (movieName.length() == 0)
            movieName = chosen.getName();
        System.out.println("Movie Rating(Classification), Current: " + chosen.getClassification());
        String classification = sc.nextLine().strip();
        if (classification.length() == 0)
            classification = chosen.getClassification();
        System.out.println("Movie Synopsis, Current: " + chosen.getSynopsis());
        String synopsis = sc.nextLine().strip();
        if (synopsis.length() == 0)
            synopsis = chosen.getSynopsis();
        System.out.println("Movie Release Date(YYYY-MM-DD), Current: " + chosen.getReleaseDate());
        String releaseDate = sc.nextLine().strip();
        if (releaseDate.length() == 0)
            releaseDate = chosen.getReleaseDate();
        System.out.println("Movie Director");
        String director = sc.nextLine().strip();
        if (director.length() == 0)
            director = chosen.getDirector();
        System.out.println("Movie Casts, type empty string to go next, Current: " + chosen.getCasts().toString());
        List<String> casts = new ArrayList<>();
        while(true) {
            String cast = sc.nextLine().strip();
            if (cast.length() == 0)
                break;
            casts.add(cast);
        }
        SqlController.editMovie(chosen.getFilmId(), movieName, classification, synopsis, releaseDate, director, casts);
    }

    public void addShow(Film chosen) {
        flush();
        System.out.println("Add Show");
        System.out.println("Choose Cinema");
        System.out.println("1: TownHall");
        System.out.println("2: Burwood");
        int cinemaId = getInputInt(1, 2);
        System.out.println("Time in YYYY-MM-DD hh:mm:ss");
        String time = sc.nextLine().strip();
        System.out.println("Screen type, Gold, Silver or Bronze");
        String screen = sc.nextLine().strip();
        System.out.println("Price");
        double price = Double.parseDouble(sc.nextLine().strip());
        System.out.println("Number of seat, Rows");
        int row = Integer.parseInt(sc.nextLine());
        System.out.println("Number of seat, Columns");
        int column = Integer.parseInt(sc.nextLine());
        SqlController.addShow(chosen.getFilmId(), cinemaId, time, screen, price, row, column);
    }

    public void register(){
        System.out.println("Create a new account");

        boolean success = false;
        while (!success){
            System.out.println("Please Enter Your username: ");
            String rUsername = sc.nextLine().strip();
            System.out.println("Please Enter Your password(length > 8, at least 1 letter and 1 number): ");
            String rPassword;
            if (System.console() == null)
                rPassword = sc.nextLine().strip();
            else
                rPassword = new String(System.console().readPassword());
            Register currentRegister = SqlController.registerUser(rUsername,rPassword);
            switch (currentRegister.getRegisterStatus()){
                case SUCCESSFUL:
                    System.out.println("Registration success.\n");
                    currentUser = SqlController.login(rUsername, rPassword);
                    success = true;
                    break;
                case DUPLICATE_USERNAME:
                    System.out.println("This username is occupied.\n");
                    System.out.println("Press Enter to continue");
                    sc.nextLine();
                    flush();
                    System.out.println("Create a new account");
                    break;
                case EMPTY_USERNAME:
                    System.out.println("Empty username is invalid.\n");
                    System.out.println("Press Enter to continue");
                    sc.nextLine();
                    flush();
                    System.out.println("Create a new account");
                    break;
                case EMPTY_PASSWORD:
                    System.out.println("Empty password is invalid.\n");
                    System.out.println("Press Enter to continue");
                    sc.nextLine();
                    flush();
                    System.out.println("Create a new account");
                    break;
                case INVALID_PASSWORD:
                    System.out.println("Invalid password. Please enter at least 8 characters. At least 1 letter and 1 number\n");
                    System.out.println("Press Enter to continue");
                    sc.nextLine();
                    flush();
                    System.out.println("Create a new account");
                    break;
            }

        }


    }

    public void selectMovie() {
        System.out.println("Please choose from available movies, or type 0 to exit");
        List<Film> films = SqlController.getFilms();
        for (int i = 0; i < films.size(); i++) {
            Film film = films.get(i);
            System.out.println((i+1) + ": " + film.getName());
        }

        int filmNo;
        filmNo = getInputInt(0, films.size() + 1);
        if (filmNo == 0) {
            return;
        }
        flush();
        // show film detail and let user choose next move
        Film chosen = SqlController.getFilmDetails(filmNo);
        System.out.println("You have chosen: " + chosen.getName());
        System.out.println(chosen.showDetails());
        if (currentUser == null){
            System.out.println("Press Enter to continue");
            sc.nextLine();
            return;
        }
        System.out.println("Select 0 to exit");
        System.out.println("Select 1 to book a ticket");
        int input = getInputInt(0, 1);
        if (input == 0)
            return;
        flush();
        List<FilmShow> filmShows = SqlController.getFilmShowsByFilm(filmNo);
        filmShows.sort(Comparator.comparingInt(FilmShow::getShowId));
        System.out.println("You have chosen: " + chosen.getName());
        System.out.println("Please select an available time, or -1 to exit");
        for (int i = 0; i < filmShows.size(); i++) {
            FilmShow f = filmShows.get(i);
            System.out.println(i + ": " + f.filterString());
        }
        input = getInputInt(-1, filmShows.size() - 1);
        if (input == -1)
            return;
        // show id start from 1, index should --
        FilmShow chosenShow = filmShows.get(input);

        flush();
        Ticket purchased = chooseSeatForShow(chosenShow);
        while (purchased.getState() == Ticket.State.OCCUPIED) {
            flush();
            purchased = chooseSeatForShow(chosenShow);
        }
    }

    public Ticket chooseSeatForShow(FilmShow chosenShow) {
        System.out.println("You have chosen: " + chosenShow.filterString());
        System.out.println("Please select an available seat");
        System.out.println(SqlController.getBookingInformationOfShow(chosenShow.getShowId()));
        System.out.print("Row: ");
        int row = getInputInt(0, chosenShow.getNumSeatRows());
        System.out.print("Column: ");
        int column = getInputInt(0, chosenShow.getNumSeatColumns());
        flush();
        System.out.println("You have chosen: " + chosenShow.filterString());
        System.out.println("Column" + column + ", Row " + row);
        System.out.println("Please choose ticket type");
        System.out.println("Select 0 for Adult");
        System.out.println("Select 1 for Child");
        System.out.println("Select 2 for Student");
        int input = getInputInt(0, 2);
        Ticket.Person ticketType = Ticket.Person.ADULT;
        switch (input) {
            case 1:
                ticketType = Ticket.Person.CHILD;
                break;
            case 2:
                ticketType = Ticket.Person.STUDENT;
            case 0:
        }
        flush();
        System.out.println("You have chosen: " + chosenShow.filterString());
        System.out.println("Column" + column + ", Row " + row);
        Ticket purchased = SqlController.buyTicket(chosenShow.getShowId(), ticketType, row, column, currentUser.getUsername());
        switch (purchased.getState()) {
            case ERROR:
                System.out.println("Internal Error");
                System.out.println("Press Enter to continue");
                sc.nextLine();
                break;
            case OUT_OF_RANGE:
                System.out.println("Seat info out of range");
                System.out.println("Press Enter to continue");
                sc.nextLine();
                break;
            case OCCUPIED:
                System.out.println("The seat you choose has been occupied");
                System.out.println("Please choose again!");
                System.out.println("Press Enter to continue");
                sc.nextLine();
                break;
            case SUCCESS:
                purchaseTicket(purchased);
                break;
        }
        return purchased;
    }

    public void purchaseTicket(Ticket purchased) {
        flush();
        System.out.printf("You have chosen %s ticket. %n", purchased.getPerson().toString());
        double price = purchased.getPrice();
        System.out.printf("The price is: $%.2f. %n", purchased.getPrice());
        System.out.println("Please choose purchase method");
        System.out.println("-1 for cancel");
        System.out.println("0 for credit card");
        System.out.println("1 for gift card");
        int input = getInputInt(-1, 1);
        int purchaseResult = 0;
        switch(input) {
            case -1:
                purchaseResult = input;
                break;
            case 0:
                purchaseResult = purchaseByCreditCard(price);
                break;
            case 1:
                purchaseResult = purchaseByGiftCard(price);
        }
        if (purchaseResult == -1) {
            SqlController.cancelTicketTransaction(purchased.getTicket_id());
            System.out.println("Purchase failed");
            return;
        }
        System.out.println("You have successfully purchased the ticket, check it on 'View Tickets'");
        System.out.println("Press Enter to continue");
        sc.nextLine();

    }

    public int purchaseByCreditCard(double price) {
        while (true){
            flush();
            if (price > 0)
                System.out.printf("You are paying $%.2f\n", price);
            else
                System.out.println("You are refunding" + Math.abs(price));

            if (currentUser.creditCards.size() != 0) {
                System.out.println("Please choose from stored cards, or -1 to pay/refund via new card");
                for (int i = 0; i < currentUser.creditCards.size(); i++) {
                    CreditCard currentCard = currentUser.creditCards.get(i);
                    System.out.printf("%d Name: %s, Number: ****%s\n", i, currentCard.name, currentCard.number.substring(currentCard.number.length() - 4));
                }
                int input = getInputInt(-1, currentUser.creditCards.size() - 1);
                if (input != -1) {
                    System.out.println("Success!");
                    return 0;
                }
            }
            System.out.println("Please enter cardholder name in 2 minutes, or type 'exit' to cancel purchase");
            String name = getUserInputWithTimeout();
            if (name == null)
                return -1;
            if (name.equals("exit")) {
                SqlController.cancelTransaction(CancelledTransaction.Reason.USER_CANCELLED, currentUser.getUsername());
                return -1;
            }
            System.out.println("Please enter credit card number");
            String number;
            if (System.console() == null)
                number = sc.nextLine().strip();
            else
                number = new String(System.console().readPassword());
            if (!SqlController.checkCreditCardExists(name, number)) {
                System.out.println("Invalid card");
                System.out.println("Type \"RETRY\" to try again, or enter to return");
                String input = sc.nextLine();
                if (input.equals("RETRY")) {
                    continue;
                }
                SqlController.cancelTransaction(CancelledTransaction.Reason.CREDIT_CARD_PAYMENT_FAILED, currentUser.getUsername());
                return -1;
            }
            System.out.println("Success!");
            CreditCard newCard = new CreditCard(name, number);
            if (currentUser.creditCards.size() == 0 || currentUser.creditCards.stream().noneMatch(c -> c.equals(newCard))) {
                System.out.println("Do you want to save this card ends with " + number.substring(number.length() - 4));
                System.out.println("0 to save");
                System.out.println("1 to ignore");
                int input = getInputInt(0, 1);
                if (input == 0) {
                    currentUser.creditCards.add(newCard);
                    SqlController.saveCreditCard(currentUser, newCard);
                }
            }
            return 0;
        }
    }

    public int purchaseByGiftCard(double price) {
        while (true) {
            flush();
            System.out.printf("You are paying $%.2f\n", price);
            System.out.println("Please enter gift card number in 2 minutes, or type 'exit' to cancel purchase");
            String giftCardNumber = getUserInputWithTimeout();
            if (giftCardNumber == null)
                return -1;
            if (giftCardNumber.equals("exit")) {
                SqlController.cancelTransaction(CancelledTransaction.Reason.USER_CANCELLED, currentUser.getUsername());
                return -1;
            }
            System.out.println("Please enter PIN");
            String PIN;
            if (System.console() == null)
                PIN = sc.nextLine().strip();
            else
                PIN = new String(System.console().readPassword());
            GiftCard currentCard = SqlController.useGiftCard(giftCardNumber, PIN, price);
            String input;
            switch (currentCard.getLoginStatus()) {
                case LOST:
                    System.out.println("Card Lost!");
                    System.out.println("Type \"RETRY\" to try again, or enter to return");
                    input = sc.nextLine();
                    if (input.equals("RETRY")) {
                        continue;
                    }
                    SqlController.cancelTransaction(CancelledTransaction.Reason.GIFT_CARD_PAYMENT_FAILED, currentUser.getUsername());
                    return -1;
                case INSUFFICIENT_BALANCE:
                    System.out.println("Insufficient Balance!");
                    System.out.println("Type \"RETRY\" to try again, or enter to return");
                    input = sc.nextLine();
                    if (input.equals("RETRY")) {
                        continue;
                    }
                    SqlController.cancelTransaction(CancelledTransaction.Reason.GIFT_CARD_PAYMENT_FAILED, currentUser.getUsername());
                    return -1;
                case INCORRECT_PIN:
                    System.out.println("Wrong PIN!");
                    System.out.println("Type \"RETRY\" to try again, or enter to return");
                    input = sc.nextLine();
                    if (input.equals("RETRY")) {
                        continue;
                    }
                    SqlController.cancelTransaction(CancelledTransaction.Reason.GIFT_CARD_PAYMENT_FAILED, currentUser.getUsername());
                    return -1;
                case NONEXISTENT_CARD_NUMBER:
                    System.out.println("Card does not exist!");
                    System.out.println("Type \"RETRY\" to try again, or enter to return");
                    input = sc.nextLine();
                    if (input.equals("RETRY")) {
                        continue;
                    }
                    SqlController.cancelTransaction(CancelledTransaction.Reason.GIFT_CARD_PAYMENT_FAILED, currentUser.getUsername());
                    return -1;
            }
            System.out.println("Success!");
            return 0;
        }
    }

    public void purchaseGiftCard() {
        while (true) {
            flush();
            System.out.println("Add a new gift card");
            System.out.println("Please enter a 16 digit gift card number with the suffix GC, " +
                    "or type 'exit' to cancel purchase");
            String giftCardNumber = sc.nextLine().strip();
            if (giftCardNumber.equals("exit")) {
                return;
            }
            System.out.println("Please enter PIN");
            String PIN;
            if (System.console() == null)
                PIN = sc.nextLine().strip();
            else
                PIN = new String(System.console().readPassword());
            System.out.println("Please enter gift card amount");
            double amount = Double.parseDouble(sc.nextLine());
            GiftCard currentCard = SqlController.buyGiftCard(giftCardNumber, PIN, amount);
            switch (currentCard.getBuyStatus()) {
                case INCORRECT_FORMAT_OF_CARD_NUMBER:
                    System.out.println("The card number does not match the format, please try again");
                    System.out.println("Press Enter to continue");
                    sc.nextLine();
                    continue;
                case DUPLICATE_CARD:
                    System.out.println("The card number is used by another card, please try again");
                    System.out.println("Press Enter to continue");
                    sc.nextLine();
                    continue;
                case INCORRECT_FORMAT_OF_PIN:
                    System.out.println("The PIN is too short, please try again");
                    System.out.println("Press Enter to continue");
                    sc.nextLine();
                    continue;
                case LARGE_AMOUNT:
                case INAPPROPRIATE_AMOUNT:
                    System.out.println("The amount is not of multiple of 5 and less than $1000, please try again");
                    System.out.println("Press Enter to continue");
                    sc.nextLine();
                    continue;
            }
            System.out.println("Success!");
        }
    }

    public void queryAndFilter() {
        System.out.println("Please choose from available locations, or type -1 to exit");
        List<String> locations = SqlController.getLocations();
        for (int i = 0; i < locations.size(); i++) {
            System.out.println(i + ": " + locations.get(i));
        }
        int locationNo;
        while (true){
            try{
                locationNo = getInputInt(-1, locations.size() - 1);
            }catch (Exception e){
                System.out.print("Invalid input try again");
                continue;
            }

            if (locationNo < 0 || locationNo >= locations.size()){
                System.out.print("Invalid input try again");
                continue;
            }
            break;
        }
        flush();

        // query using provided location
        List<FilmShow> filmShows = SqlController.filter(locations.get(locationNo));
        if (currentUser == null){
            for (FilmShow f: filmShows){
                System.out.println(f.filterString());
            }
            System.out.println("Press Enter to continue");
            sc.nextLine();
            return;
        }
        for (FilmShow f: filmShows){
            System.out.println(f.getShowId() + ": " + f.filterString());
        }
        System.out.println("Select -1 to exit");
        System.out.println("Select a show to book a ticket");
        int input = getInputInt(-1, filmShows.get(0).getShowId() + filmShows.size() - 1);
        if (input == -1)
            return;
        FilmShow chosenShow = filmShows.get(input - filmShows.get(0).getShowId());
        Ticket purchased = chooseSeatForShow(chosenShow);
        while (purchased.getState() == Ticket.State.OCCUPIED) {
            flush();
            purchased = chooseSeatForShow(chosenShow);
        }
    }

    public void displayPurchases() {
        flush();
        System.out.println("Your tickets");
        List<Ticket> tickets = SqlController.getPurchasedTickets(currentUser.getUsername());
        for (int i = 0; i < tickets.size(); i++) {
            Ticket t = tickets.get(i);
            System.out.printf("Ticket: %d Film: %s Cinema: %s Time: %s Status: %s Transaction ID: %s\n", i, t.getFilmName(), t.getCinemaName(), t.getTime(), t.getState().toString(), t.getTicket_id());
        }
        System.out.println("Select a ticket to refund, or -1 to exit");
        int input = getInputInt(-1, tickets.size() - 1);
        if (input == -1)
            return;
        Ticket chosen = tickets.get(input);
        if (chosen.getState() == Ticket.State.CANCELLED) {
            System.out.println("You cannot refund a cancelled ticket");
            return;
        }
        flush();
        System.out.printf("Ticket: %d Film: %s Cinema: %s Time: %s Status: %s", input, chosen.getFilmName(), chosen.getCinemaName(), chosen.getTime(), chosen.getState().toString());
        System.out.println("\nType 0 to refund to a Card, or -1 to exit");
        input = getInputInt(-1, 0);
        if (input == -1)
            return;
        purchaseByCreditCard(-chosen.getPrice());
        SqlController.refundTicketTransaction(chosen.getTicket_id());
    }

    public List<CreditCard> initializeCreditCards() {
        List<CreditCard> cards = new ArrayList<>();
        JSONParser parser = new JSONParser();
        JSONArray objs = null;
        InputStream configStream = this.getClass().getResourceAsStream("/credit_cards.json");
        try {
            assert configStream != null;
            objs = (JSONArray) parser.parse(new InputStreamReader(configStream, "UTF-8"));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        assert (objs != null);
        for (Object obj : objs) {
            JSONObject cardEntry = (JSONObject) obj;
            CreditCard card = new CreditCard(cardEntry.get("name").toString(), cardEntry.get("number").toString());
            cards.add(card);
        }
        return cards;
    }

    public int getInputInt(int rangeS, int rangeE){
        int integer;
        while(true){
            try{
                integer = Integer.parseInt(sc.nextLine());

            } catch (Exception e){
                System.out.println("Invalid input, try again");
                continue;
            }

            if ( integer < rangeS || integer > rangeE ){
                System.out.println("Invalid input, try again");
                continue;
            }

            break;
        }
        return integer;
    }

    private String getUserInputWithTimeout() {
        Callable<String> k = () -> new Scanner(System.in).nextLine();
        LocalDateTime start = LocalDateTime.now();
        Future<String> g = l.submit(k);
        while (ChronoUnit.SECONDS.between(start, LocalDateTime.now()) < 120) {
            if (g.isDone()) {
                try {
                    return g.get();
                } catch (InterruptedException | ExecutionException | IllegalArgumentException e) {
                    g = l.submit(k);
                }
            }
        }
        g.cancel(true);
        SqlController.cancelTransaction(CancelledTransaction.Reason.TIME_OUT, currentUser.getUsername());
        return null;
    }

    public static void flush() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
