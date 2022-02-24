package R08_C8_Group2_Assignment2;

public class FilmShow {

    private int cinemaId;
    private int filmId;
    private String filmName;
    private String location;
    private String time;
    private String screen;
    private double price;

    // Sprint 2
    // #############################
    // TODO: test getter and setter
    private int showId;
    private int numSeatRows;
    private int numSeatColumns;
    // #############################


    public FilmShow(){};

    public FilmShow(int cinemaId, int filmId, String filmName, String location, String time, String screen, double price) {
        this.cinemaId = cinemaId;
        this.filmId = filmId;
        this.filmName = filmName;
        this.location = location;
        this.time = time;
        this.screen = screen;
        this.price = price;
    }

    // Sprint 2
    // #############################
    // TODO: new constructor
    public FilmShow(int cinemaId, int filmId, String filmName, String location, String time, String screen, double price,
                    int showId, int numSeatRows, int numSeatColumns){
        this.cinemaId = cinemaId;
        this.filmId = filmId;
        this.filmName = filmName;
        this.location = location;
        this.time = time;
        this.screen = screen;
        this.price = price;
        this.showId = showId;
        this.numSeatRows = numSeatRows;
        this.numSeatColumns = numSeatColumns;
    }
    // #############################


    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof FilmShow)) {
            return false;
        }

        // typecast
        FilmShow c = (FilmShow) o;

        // Compare the data members and return accordingly
        return c.getCinemaId() == this.cinemaId
                && c.getFilmId() == this.filmId
                && c.getLocation().equalsIgnoreCase(this.location)
                && c.getTime().equalsIgnoreCase(this.time)
                && c.getScreen().equalsIgnoreCase(this.screen)
                && c.getPrice() == this.price;
    }

    public String filterString(){
        return "Location: " + location + ", Film: " + filmName + ", Time:" + time;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getNumSeatRows() {
        return numSeatRows;
    }

    public void setNumSeatRows(int numSeatRows) {
        this.numSeatRows = numSeatRows;
    }

    public int getNumSeatColumns() {
        return numSeatColumns;
    }

    public void setNumSeatColumns(int numSeatColumns) {
        this.numSeatColumns = numSeatColumns;
    }
}
