package database.on_page.purchasing_strategy;

import database.Page;
import input.movie.MoviesInput;

/**
 * Interface for purchasing movies for a user.
 */
public interface PurchasingStrategy {
    /**
     * Method for purchasing a movie for a user.
     *
     * @param currentPage the current page
     * @param purchasedMovie the movie to be purchased
     * @return the purchased movie or null if the purchase is not successful
     */
    MoviesInput purchaseMovie(Page currentPage, MoviesInput purchasedMovie);
}
