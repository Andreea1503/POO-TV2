package database.database_action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import input.action.ActionsInput;
import input.movie.MoviesInput;
import write.Write;

import java.util.ArrayList;

public class AddAction implements Action {
    private ArrayList<MoviesInput> movies;
    private ArrayNode output;

    private ActionsInput action;

    public AddAction(final ArrayList<MoviesInput> movies, final ArrayNode output, final ActionsInput action) {
        this.movies = movies;
        this.output = output;
        this.action = action;
    }

    /**
     * Method that executes the add action
     */
    @Override
    public void execute() {
        for (MoviesInput movie : movies) {
            if (action.getAddedMovie().getName().equals(movie.getName())) {
                action.setError("Error");
                Write.writePageError(null, action, output);
                return;
            }
        }
        movies.add(action.getAddedMovie());
        Database database = Database.getInstance();
        database.getGenre().notifyObservers(action.getAddedMovie());
    }
}
