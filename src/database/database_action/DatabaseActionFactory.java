package database.database_action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.action.ActionsInput;
import input.movie.MoviesInput;
import input.user.UsersInput;
import java.util.ArrayList;

/**
 * Factory class for creating database actions.
 */
public final class DatabaseActionFactory {
    private DatabaseActionFactory() {
    }

    /**
     * Returns a new action based on the provided action input.
     *
     * @param action  input action
     * @param movies  list of movies
     * @param output  output JSON object
     * @param users   list of users
     * @return the new action
     */
    public static Action getAction(final ActionsInput action, final ArrayList<MoviesInput> movies,
                                   final ArrayNode output, final ArrayList<UsersInput> users) {
        if (action.getFeature() == null) {
            return null;
        }
        switch (action.getFeature()) {
            case "add" -> {
                return new AddAction(movies, output, action);
            }
            case "delete" -> {
                return new DeleteAction(movies, output, action, users);
            }
            default -> {
                return null;
            }
        }
    }
}
