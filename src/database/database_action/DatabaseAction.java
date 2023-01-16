package database.database_action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.action.ActionsInput;
import input.movie.MoviesInput;
import input.user.UsersInput;

import java.util.ArrayList;

/**
 * Singleton class that encapsulates database actions.
 */
public final class DatabaseAction {
    private static volatile DatabaseAction instance;
    private DatabaseAction() {

    }

    /**
     * Returns a singleton instance of the DatabaseAction class.
     *
     * @return the singleton instance of the DatabaseAction class
     */
    public static DatabaseAction getInstance() {
        DatabaseAction result = instance;
        if (result == null) {
            synchronized (DatabaseAction.class) {
                result = instance;
                if (result == null) {
                    instance = result = new DatabaseAction();
                }
            }
        }
        return result;
    }

    /**
     * Executes a database action based on the provided action input.
     *
     * @param movies   list of movies
     * @param action   input action
     * @param output   output JSON object
     * @param users    list of users
     */
    public static void databaseAction(final ArrayList<MoviesInput> movies,
                                      final ActionsInput action,
                                      final ArrayNode output, final ArrayList<UsersInput> users) {
        Action databaseAction = DatabaseActionFactory.getAction(action, movies, output, users);
        databaseAction.execute();
    }
}
