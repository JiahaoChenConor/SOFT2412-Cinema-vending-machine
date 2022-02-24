package R08_C8_Group2_Assignment2;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class Film {


    private int filmId;
    private String name;
    private String classification;
    private String synopsis;
    private String release_date;
    private String director;
    private List<String> casts = new ArrayList<>();

    private List<FilmShow> filmShows = new ArrayList<>();

    public  Film(){};
    public Film(int filmId, String name,String classification, String synopsis, String release_date, String director, List<String> casts) {
        this.filmId = filmId;
        this.name = name;
        this.classification = classification;
        this.synopsis = synopsis;
        this.release_date = release_date;
        this.director = director;
        this.casts = casts;
    }

    public String getLocations(){
        StringBuilder upcomingTimes = new StringBuilder();

        java.sql.Timestamp curTime = new Timestamp(System.currentTimeMillis());

        for (FilmShow filmShow: filmShows){
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date parsedDate = dateFormat.parse(filmShow.getTime());
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

                if (timestamp.after(curTime)){
                    upcomingTimes.append(filmShow.getTime()).append(" Screen Size: ");
                    upcomingTimes.append(filmShow.getScreen()).append(" Location: ").append(filmShow.getLocation()).append('\n');
                }
            } catch(Exception e) { //this generic but you can control another types of exception
                e.printStackTrace();
            }

        }

        return upcomingTimes.toString();
    }
    public String showDetails(){
        StringBuilder casters = new StringBuilder();
        for (String cast: this.casts){
            casters.append(cast).append("   ");
        }

        return String.format("######<< %s >>######\n\n" +
                "Classification: %s\n\n" +
                "Synopsis: %s \n\n" +
                "Release Date: %s\n\n" +
                "Director: %s\n\n" +
                "Casts: %s \n\n" +
                "Upcoming times and screen sizes: \n%s\n" , name, classification, synopsis, release_date, director, casters, getLocations());
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String  classification) {
        this.classification = classification;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<FilmShow> getFilmShows() {
        return filmShows;
    }

    public void setFilmShows(List<FilmShow> filmShows) {
        this.filmShows = filmShows;
    }

    public void addFilmShow(FilmShow filmShow){
        this.filmShows.add(filmShow);
    }

    public void deleteFilmShow(FilmShow filmShow){
        filmShows.removeIf(x -> x.equals(filmShow));
    }


    public List<String> getCasts() {
        return casts;
    }

    public void setCasts(List<String> casts) {
        this.casts = casts;
    }

    public void addCast(String cast){
        this.casts.add(cast);
    }

    public void deleteCast(String cast){
        casts.removeIf(x -> x.equals(cast));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
