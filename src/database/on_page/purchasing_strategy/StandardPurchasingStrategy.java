package database.on_page.purchasing_strategy;

import database.Page;
import input.movie.MoviesInput;

public class StandardPurchasingStrategy implements PurchasingStrategy {
    @Override
    public MoviesInput purchaseMovie(Page currentPage, MoviesInput purchasedMovie) {
        if (purchasedMovie != null) {
            currentPage.getCurrentUser().setTokensCount(currentPage.getCurrentUser().getTokensCount() - 2);
            currentPage.getCurrentUser().getPurchasedMovies().add(purchasedMovie);
            return purchasedMovie;
        }
        return null;
    }
}
