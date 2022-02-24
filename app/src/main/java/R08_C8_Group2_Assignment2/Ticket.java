package R08_C8_Group2_Assignment2;

import static R08_C8_Group2_Assignment2.Ticket.Person.ADULT;

public class Ticket {
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    enum State{
        ERROR,
        OUT_OF_RANGE,
        OCCUPIED,
        CANCELLED,
        REFUNDED,
        SUCCESS
    }

    enum Person{
        CHILD,
        STUDENT,
        ADULT
    }

    private int show_id;
    private int ticket_id;
    private Person person;
    private int seat_row;
    private int seat_column;
    private State state;
    private String filmName;
    private String cinemaName;
    private String time;

    public Ticket(){

    }

    public Ticket(int show_id, int ticket_id, Person person, int seat_row, int seat_column, State state){
        this.show_id = show_id;
        this.ticket_id = ticket_id;
        this.person = person;

        this.seat_row = seat_row;
        this.seat_column = seat_column;
        this.state = state;
    }

    public int getShow_id() {
        return show_id;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getSeat_row() {
        return seat_row;
    }

    public void setSeat_row(int seat_row) {
        this.seat_row = seat_row;
    }

    public int getSeat_column() {
        return seat_column;
    }

    public void setSeat_column(int seat_column) {
        this.seat_column = seat_column;
    }

    public void setCinemaName(String cinemaName){
        this.cinemaName = cinemaName;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setFilmName(String filmName){
        this.filmName = filmName;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public double getPrice() {
        switch (person) {
            case ADULT:
                return 3;
            case STUDENT:
                return 2;
            case CHILD:
                return 1;
        }
        return 0;
    }
}
