package database.database_action;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.action.ActionsInput;
import input.movie.MoviesInput;
import input.user.UsersInput;
import java.util.ArrayList;

public class DatabaseActionFactory {
    public static Action getAction(ActionsInput action, ArrayList<MoviesInput> movies, ArrayNode output, ArrayList<UsersInput> users) {
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
