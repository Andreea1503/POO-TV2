package database.on_page;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import database.Page;
import input.action.ActionsInput;
import input.movie.MoviesInput;
import input.user.UsersInput;

import write.Write;
import java.util.ArrayList;

/**
 * Class that implements the subscribe action
 */
public class SubscribeAction implements OnPageAction {
    private ArrayList<UsersInput> users;
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;

    public SubscribeAction(final ArrayList<UsersInput> users, final ActionsInput action,
                       final Page currentPage, final ArrayNode output) {
        this.users = users;
        this.action = action;
        this.currentPage = currentPage;
        this.output = output;
    }


    /**
     * Method that executes the action of subscribing to a genre.
     * It checks if the current page is 'see details' and if the user is not already
     * subscribed to the genre.
     * If the conditions are met, it adds the genre to the user's subscribed genres and
     * updates the database.
     * If the conditions are not met, it sets an error message in the output.
     * */
    @Override
    public void execute() {
        UsersInput currentUser = currentPage.getCurrentUser();
        if (currentPage.getCurrentPageName().equals("see details")
                && !currentUser.getSubscribedGenres()
                .contains(action.getSubscribedGenre())) {
            for (MoviesInput movie : currentUser.getCurrentMoviesList()) {
                if (!movie.getGenres().contains(action.getSubscribedGenre())) {
                    action.setError("Error");
                    Write.writePageError(null, action, output);
                    return;
                }
            }
            currentUser.getSubscribedGenres().add(action.getSubscribedGenre());
            Database database = Database.getInstance();
            database.getGenre().subscribe(currentUser, action.getSubscribedGenre());
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
