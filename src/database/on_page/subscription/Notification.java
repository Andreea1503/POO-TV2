package database.on_page.subscription;

/**
 * The class represents a notification that is sent to an observer.
 * It contains information about the movie name and the message.
 */
public class Notification {
    private String movieName;
    private String message;

    public Notification() {
    }
    public Notification(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    /**
     * Method that returns the movie name
     * @return movie name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Method that sets the movie name
     * @param movieName the movie name
     */
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    /**
     * Method that returns the message
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Method that sets the message
     * @param message the message
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
