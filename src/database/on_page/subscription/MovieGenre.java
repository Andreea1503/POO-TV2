package database.on_page.subscription;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import input.action.ActionsInput;
import input.movie.MoviesInput;

import java.util.ArrayList;

/**
 * Class that represents a movie genre and implements the Subject interface
 */
public class MovieGenre implements Subject {
    private Notification notification;
    private Map<Observer, List<String>> observers = new HashMap<>();
    private ActionsInput action;
    public MovieGenre() {
        this.notification = new Notification();
    }

    public MovieGenre(final ActionsInput action) {
        this.notification = new Notification();
    }

    /**
     * Method that creates the notification
     * @param movieName name of the movie
     */
    public void createNotification(final String movieName) {
        notification.setMovieName(movieName);
        notification.setMessage("ADD");
    }

    /**
     * Subscribe method that adds the observer to the list of observers and the genre to the list of
     * genres that the observer is subscribed to.
     *
     * @param observer the observer
     * @param movieGenre the movie genre
     */
    @Override
    public void subscribe(final Observer observer, final String movieGenre) {
        if (observers.containsKey(observer)) {
            observers.get(observer).add(movieGenre);
        } else {
            List<String> movieGenres = new ArrayList<>();
            movieGenres.add(movieGenre);
            observers.put(observer, movieGenres);
        }
    }

    /**
     * notifyObservers method that notify all the observers that are subscribed to the genre of the
     * movie
     *
     * @param movie the movie
     */
    @Override
    public void notifyObservers(final MoviesInput movie) {
        createNotification(movie.getName());

        for (Map.Entry<Observer, List<String>> entry : observers.entrySet()) {
            boolean isSubscribed = false;
            for (String genre : entry.getValue()) {
                if (movie.getGenres().contains(genre) && !isSubscribed) {
                    Notification notification = new Notification();
                    notification.setMessage(this.notification.getMessage());
                    notification.setMovieName(this.notification.getMovieName());
                    entry.getKey().update(notification);
                    isSubscribed = true;
                }
            }
        }
    }
}
