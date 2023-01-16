package database.on_page;

import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.action.ActionsInput;
import input.movie.MoviesInput;

import java.util.ArrayList;

/**
 * Class that implements the like action
 */
public class LikeAction implements OnPageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;
    public LikeAction(final ActionsInput action, final Page currentPage, final ArrayNode output) {
        this.action = action;
        this.currentPage = currentPage;
        this.output = output;
    }

    /**
     * Method that adds a like to a movie
     * First it checks if the movie exists for the user. If it doesn't an error message is added
     * in the output file.
     * If the movie exists, it checks if the movie is in the user's watched list. If it is, the
     * movie is added to the user's liked movies list and the number of likes is incremented and a
     * message is added in the output file.
     * If the movie is not in the user's watched list, an error message is added in the output file.
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentUser() != null) {
            MoviesInput watchedMovie = MoviesInput.seeDetails(currentPage.getAction(),
                    currentPage.getCurrentUser().getCurrentMoviesList());

            if (watchedMovie == null) {
                action.setError("Error");
                Write.writePageError(null, action, output);
                return;
            }

            MoviesInput movie = new MoviesInput();
            MoviesInput likedMovie = movie.isInList(
                    currentPage.getCurrentUser().getWatchedMovies(), watchedMovie.getName());

                if (likedMovie != null
                        && currentPage.getCurrentPageName().equals("see details")) {
                    ArrayList<MoviesInput> likedMovies = currentPage.getCurrentUser()
                            .getLikedMovies();
                    if (!likedMovies.contains(likedMovie)) {
                        likedMovies.add(likedMovie);
                        likedMovies.get(likedMovies.size() - 1).setNumLikes(
                                likedMovies.get(likedMovies.size() - 1).getNumLikes() + 1);
                    }
                    Write.writePageError(currentPage.getCurrentUser(), action, output);
                } else {
                    action.setError("Error");
                    Write.writePageError(null, action, output);
                }
            }

    }
}
