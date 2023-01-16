package database.database_action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.database_action.return_properties_strategy.PremiumReturnPropertiesStrategy;
import database.database_action.return_properties_strategy.ReturnPropertiesStrategy;
import database.database_action.return_properties_strategy.StandardReturnPropertiesStrategy;
import database.on_page.subscription.MovieGenre;
import input.action.ActionsInput;
import input.movie.MoviesInput;
import input.user.UsersInput;
import write.Write;

import java.util.ArrayList;

/**
 * Class representing the delete action from the database.
 */
public class DeleteAction implements Action {
    private final ArrayList<MoviesInput> movies;
    private final ArrayNode output;
    private final ActionsInput action;
    private final ArrayList<UsersInput> users;

    public DeleteAction(final ArrayList<MoviesInput> movies, final ArrayNode output,
                        final ActionsInput action, final ArrayList<UsersInput> users) {
        this.movies = movies;
        this.output = output;
        this.action = action;
        this.users = users;
    }

    /**
     * Method that executes the delete action
     */
    @Override
    public void execute() {
        MoviesInput movie = new MoviesInput();
        MoviesInput movieToDelete = movie.isInList(movies, action.getDeletedMovie());

        if (movieToDelete == null) {
            action.setError("Error");
            Write.writePageError(null, action, output);
            return;
        }

        movies.remove(movieToDelete);
        MovieGenre genre = new MovieGenre(action);
        genre.notifyObservers(movieToDelete);
        deleteMovieForUser(movieToDelete, users);
    }

    /**
     * Helper method that deletes a movie from a user's purchased movie list and returns user's
     * properties
     *
     * @param movieToBeDeleted the movie to be deleted
     * @param users list of users
     */
    private static void deleteMovieForUser(final MoviesInput movieToBeDeleted,
                                           final ArrayList<UsersInput> users) {
        for (UsersInput user : users) {
            if (user.getPurchasedMovies() != null
                    && user.getPurchasedMovies().contains(movieToBeDeleted)) {
                user.getPurchasedMovies().remove(movieToBeDeleted);
                returnProperties(user);
            }
        }
    }

    /**
     * Helper method that returns properties based on the user's account type
     *
     * @param user the user for which to return the properties
     */
    private static void returnProperties(final UsersInput user) {
        ReturnPropertiesStrategy strategy;
        if (user.getCredentials().getAccountType().equals("premium")) {
            strategy = new PremiumReturnPropertiesStrategy();
        } else {
            strategy = new StandardReturnPropertiesStrategy();
        }
        strategy.returnProperties(user);
    }
}
