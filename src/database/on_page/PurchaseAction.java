package database.on_page;

import database.Page;
import database.on_page.purchasing_strategy.PremiumPurchasingStrategy;
import database.on_page.purchasing_strategy.PurchasingStrategy;
import database.on_page.purchasing_strategy.StandardPurchasingStrategy;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.action.ActionsInput;
import input.movie.MoviesInput;

/**
 * Class that implements the purchase action
 */
public class PurchaseAction implements OnPageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;
    public PurchaseAction(final ActionsInput action, final Page currentPage,
                          final ArrayNode output) {
        this.action = action;
        this.currentPage = currentPage;
        this.output = output;
    }

    /**
     * Method that implements the purchase action if the purchased movie exists or an error message
     * otherwise
     */
    @Override
    public void execute() {
        if (!currentPage.getCurrentUser().getPurchasedMovies()
                .contains(MoviesInput.seeDetails(currentPage.getAction(),
                currentPage.getCurrentUser().getCurrentMoviesList()))) {
            MoviesInput purchasedMovie = purchaseMovieOnAccount(currentPage);

            if (purchasedMovie != null && currentPage.getCurrentPageName().equals("see details")) {
                Write.writePageError(currentPage.getCurrentUser(), action, output);
            } else {
                action.setError("Error");
                Write.writePageError(null, action, output);
            }
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }

    /**
     * Method that checks if the movie exists in the database and if it does, checks what type of
     * user is trying to purchase it and if the user is a premium one and has enough free movies
     * left, it is purchased and the film is added to the user's list of purchased movies and the
     * number of free movies left is decreased by one.
     * Otherwise, if the user is a standard one, the movie is purchased with tokens and the number
     * of tokens is decreased by the price of the movie. The film is added to the user's list of
     * purchased movie.
     * @param currentPage
     * @return the purchased movie
     */
    private MoviesInput purchaseMovieOnAccount(final Page currentPage) {
        MoviesInput purchasedMovie = MoviesInput.seeDetails(currentPage.getAction(),
                currentPage.getCurrentUser().getCurrentMoviesList());
        PurchasingStrategy strategy;
        if (currentPage.getCurrentUser().getCredentials().getAccountType().equals("premium")) {
            strategy = new PremiumPurchasingStrategy();
        } else {
            strategy = new StandardPurchasingStrategy();
        }
        return strategy.purchaseMovie(currentPage, purchasedMovie);
    }
}
