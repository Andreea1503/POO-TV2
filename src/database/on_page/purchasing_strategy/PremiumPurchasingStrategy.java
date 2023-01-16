package database.on_page.purchasing_strategy;

import database.Page;
import input.movie.MoviesInput;
import input.user.UsersInput;

/**
 * Concrete implementation of the PurchasingStrategy interface for purchasing
 * movies for a premium user.
 */
public class PremiumPurchasingStrategy implements PurchasingStrategy {
    /**
     * Method that purchases a movie for a premium user
     *
     * @param currentPage the current page
     * @param purchasedMovie the movie to be purchased
     * @return the purchased movie or null if the purchase is not successful
     */
    @Override
    public MoviesInput purchaseMovie(final Page currentPage, final MoviesInput purchasedMovie) {
        UsersInput currentUser = currentPage.getCurrentUser();
        if (purchasedMovie != null && currentUser.getNumFreePremiumVideos() > 0) {
            currentUser.setNumFreePremiumVideos(currentUser.getNumFreePremiumVideos() - 1);
            currentUser.getPurchasedMovies().add(purchasedMovie);
            return purchasedMovie;
        } else if (purchasedMovie != null) {
            currentUser.setTokensCount(currentUser.getTokensCount() - 2);
            currentUser.getPurchasedMovies().add(purchasedMovie);
            return purchasedMovie;
        }
        return null;
    }
}
