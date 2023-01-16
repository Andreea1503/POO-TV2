package database.on_page.subscription;

public class Notification {
    private String movieName;
    private String message;

    public Notification() {
    }
    public Notification(String movieName, String message) {
        this.movieName = movieName;
        this.message = message;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
