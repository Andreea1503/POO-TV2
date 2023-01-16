package database.on_page;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import database.Page;
import input.action.ActionsInput;
import input.movie.MoviesInput;
import input.user.UsersInput;

import write.Write;
import java.util.ArrayList;

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

    @Override
    public void execute() {
        if (currentPage.getCurrentPageName().equals("see details") && !currentPage.getCurrentUser().getSubscribedGenres().contains(action.getSubscribedGenre())) {
            for (MoviesInput movie : currentPage.getCurrentUser().getCurrentMoviesList()) {
                if (!movie.getGenres().contains(action.getSubscribedGenre())) {
                    action.setError("Error");
                    Write.writePageError(null, action, output);
                    return;
                }
            }
            currentPage.getCurrentUser().getSubscribedGenres().add(action.getSubscribedGenre());
            Database database = Database.getInstance();
            database.getGenre().subscribe(currentPage.getCurrentUser(), action.getSubscribedGenre());
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
