package database.on_page.purchasing_strategy;

import database.Page;
import input.movie.MoviesInput;

public interface PurchasingStrategy {
    MoviesInput purchaseMovie(Page currentPage, MoviesInput purchasedMovie);
}
