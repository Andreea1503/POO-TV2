package database.on_page.subscription;

import input.movie.MoviesInput;

/**
 * The Subject interface defines the methods that a class implementing this interface should have.
 * It allows observers to subscribe to the subject and receive notifications when a new movie of
 * a certain genre is added.
 */
public interface Subject {
    /**
     * Subscribes an observer to receive notifications when a new movie of the specified genre
     * is added.
     *
     * @param observer the observer to subscribe
     * @param movieGenre the genre of the movie to subscribe to
     */
    void subscribe(Observer observer, String movieGenre);

    /**
     * Notifies all subscribed observers when a new movie is added.
     *
     * @param movie the new movie that has been added
     */
    void notifyObservers(MoviesInput movie);
}
