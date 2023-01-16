package database.database_action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import database.on_page.subscription.MovieGenre;
import input.action.ActionsInput;
import input.movie.MoviesInput;
import input.user.UsersInput;
import write.Write;

import java.util.ArrayList;

public class DatabaseAction {
    private static volatile DatabaseAction instance;
    private DatabaseAction() {

    }

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

    public static void databaseAction(ArrayList<MoviesInput> movies, ActionsInput action, ArrayNode output, ArrayList<UsersInput> users) {
        Action databaseAction = DatabaseActionFactory.getAction(action, movies, output, users);
        databaseAction.execute();
    }
}
