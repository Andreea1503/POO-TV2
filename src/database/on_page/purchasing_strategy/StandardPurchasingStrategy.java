package database.on_page.purchasing_strategy;

import database.Page;
import input.movie.MoviesInput;
import input.user.UsersInput;

/**
 * Concrete implementation of the PurchasingStrategy interface for purchasing
 * movies for a standard user.
 */
public class StandardPurchasingStrategy implements PurchasingStrategy {
    /**
     * Method that purchases a movie for a standard user
     *
     * @param currentPage the current page
     * @param purchasedMovie the movie to be purchased
     * @return the purchased movie or null if the purchase is not successful
     */
    @Override
    public MoviesInput purchaseMovie(final Page currentPage, final MoviesInput purchasedMovie) {
        if (purchasedMovie != null) {
            UsersInput currentUser = currentPage.getCurrentUser();
            currentUser.setTokensCount(currentUser.getTokensCount() - 2);
            currentUser.getPurchasedMovies().add(purchasedMovie);
            return purchasedMovie;
        }
        return null;
    }
}
