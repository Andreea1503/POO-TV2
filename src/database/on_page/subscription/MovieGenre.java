package database.on_page.subscription;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import input.action.ActionsInput;
import input.movie.MoviesInput;

import java.util.ArrayList;

public class MovieGenre implements Subject {
    private Notification notification;
    private Map<Observer, List<String>> observers = new HashMap<>();
    private ActionsInput action;
    public MovieGenre() {
        this.notification = new Notification();
    }

    public MovieGenre(ActionsInput action) {
        this.notification = new Notification();
    }

    public void createNotification(String movieName) {
        notification.setMovieName(movieName);
        notification.setMessage("ADD");
    }
    @Override
    public void subscribe(Observer observer, String movieGenre) {
        if (observers.containsKey(observer)) {
            observers.get(observer).add(movieGenre);
        } else {
            List<String> movieGenres = new ArrayList<>();
            movieGenres.add(movieGenre);
            observers.put(observer, movieGenres);
        }
    }

    @Override
    public void notifyObservers(MoviesInput movie) {
        createNotification(movie.getName());

        for (Map.Entry<Observer, List<String>> entry : observers.entrySet()) {
            boolean isSubscribed = false;
            for (String genre : entry.getValue()) {
                if (movie.getGenres().contains(genre) && isSubscribed == false) {
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
