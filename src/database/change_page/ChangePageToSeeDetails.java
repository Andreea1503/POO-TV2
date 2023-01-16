package database.change_page;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.action.ActionsInput;
import input.movie.MoviesInput;
import database.Page;
import write.Write;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that executes the change page to see details command
 */
public class ChangePageToSeeDetails implements ChangePageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayList<MoviesInput> movies;
    private ArrayNode output;

    private Stack<Page> pages;

    public ChangePageToSeeDetails(final ActionsInput action, final Page currentPage,
                                  final ArrayList<MoviesInput> movies, final ArrayNode output, final Stack<Page> pages) {
        this.action = action;
        this.currentPage = currentPage;
        this.movies = movies;
        this.output = output;
        this.pages = pages;
    }

    /**
     * Methos that returns the action
     * @return action
     */
    public ActionsInput getAction() {
        return action;
    }

    /**
     * Method that returns the current page
     * @return currentPage
     */
    public Page getCurrentPage() {
        return currentPage;
    }

    /**
     * Method that returns the movies
     * @return movies
     */
    public ArrayList<MoviesInput> getMovies() {
        return movies;
    }

    /**
     * Method that returns the output
     * @return output
     */
    public ArrayNode getOutput() {
        return output;
    }

    /**
     * Method that changes the page to see details if the current page is "movies" or
     * "see details", otherwise it returns an error message
     * If the change page to see details command is executed successfully, the current page is
     * changed to "see details", then the movie given in the input is searched in the list of the
     * allowed movies for the user.
     * If the movie is found, then the details of the movie are written in the output file and the
     * current movie list for the user is updated, containing the movie given in the input.
     * Otherwise, an error message is written in the output file and the current movie list for the
     * user contains the allowed movies for the user.
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentPageName().equals("movies")
                || currentPage.getCurrentPageName().equals("see details")) {
            MoviesInput allowedMovie = MoviesInput.seeDetails(
                    action, currentPage.getCurrentUser().getCurrentMoviesList());

            if (allowedMovie != null) {
                pages.push(new Page(currentPage.getCurrentPageName(), currentPage.getCurrentUser(), currentPage.getAction()));
                currentPage.setCurrentPageName("see details");
                currentPage.getCurrentUser().setCurrentMoviesList(new ArrayList<>());
                currentPage.setAction(action);
                currentPage.getCurrentUser().getCurrentMoviesList().add(allowedMovie);
                Write.writePageError(currentPage.getCurrentUser(), action, output);
            } else {
                action.setError("Error");
                currentPage.setAction(action);
                MoviesInput movie = new MoviesInput();
                currentPage.getCurrentUser().setCurrentMoviesList(
                        movie.allowedMoviesForUser(movies, currentPage.getCurrentUser()));
                Write.writePageError(null, action, output);
            }
        } else {
            action.setError("Error");
            currentPage.setAction(action);
            MoviesInput movie = new MoviesInput();
            currentPage.getCurrentUser().setCurrentMoviesList(
                    movie.allowedMoviesForUser(movies, currentPage.getCurrentUser()));
            Write.writePageError(null, action, output);
        }
    }
}
