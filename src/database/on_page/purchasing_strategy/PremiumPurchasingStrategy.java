package database.on_page.purchasing_strategy;

import database.Page;
import input.movie.MoviesInput;

public class PremiumPurchasingStrategy implements PurchasingStrategy {
    @Override
    public MoviesInput purchaseMovie(Page currentPage, MoviesInput purchasedMovie) {
        if (purchasedMovie != null && currentPage.getCurrentUser().getNumFreePremiumVideos() > 0) {
            currentPage.getCurrentUser().setNumFreePremiumVideos(currentPage.getCurrentUser().getNumFreePremiumVideos() - 1);
            currentPage.getCurrentUser().getPurchasedMovies().add(purchasedMovie);
            return purchasedMovie;
        } else if (purchasedMovie != null) {
            currentPage.getCurrentUser().setTokensCount(currentPage.getCurrentUser().getTokensCount() - 2);
            currentPage.getCurrentUser().getPurchasedMovies().add(purchasedMovie);
            return purchasedMovie;
        }
        return null;
    }
}
