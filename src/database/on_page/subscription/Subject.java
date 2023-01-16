package database.on_page.subscription;

import input.movie.MoviesInput;

public interface Subject {
    void subscribe(Observer observer, String movieGenre);
    void notifyObservers(MoviesInput movie);
}
