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

public class DeleteAction implements Action {
    private final ArrayList<MoviesInput> movies;
    private final ArrayNode output;
    private final ActionsInput action;
    private final ArrayList<UsersInput> users;

    public DeleteAction(final ArrayList<MoviesInput> movies, final ArrayNode output, final ActionsInput action, final ArrayList<UsersInput> users) {
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

    private static void deleteMovieForUser(MoviesInput movieToBeDeleted, ArrayList<UsersInput> users) {
        for (UsersInput user : users) {
            if (user.getPurchasedMovies() != null && user.getPurchasedMovies().contains(movieToBeDeleted)) {
                user.getPurchasedMovies().remove(movieToBeDeleted);
                returnProperties(user);
            }
        }
    }

    private static void returnProperties(UsersInput user) {
        ReturnPropertiesStrategy strategy;
        if (user.getCredentials().getAccountType().equals("premium")) {
            strategy = new PremiumReturnPropertiesStrategy();
        } else {
            strategy = new StandardReturnPropertiesStrategy();
        }
        strategy.returnProperties(user);
    }
}
