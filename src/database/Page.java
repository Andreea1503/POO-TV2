package database;

import database.change_page.ChangePageAction;
import database.change_page.ChangePageActionFactory;
import database.on_page.OnPageAction;
import database.on_page.OnPageActionFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.Stack;

import input.user.UsersInput;
import input.action.ActionsInput;
import input.movie.MoviesInput;
import write.Write;

/**
 * Class that contains a page of the application and executes the proper action. It contains the
 * current page name, the current user and the list of current movies.
 */
public class Page {
    private String currentPageName;
    private UsersInput currentUser;
    private ActionsInput action;

    public Page() {
    }

    public Page(final String currentPageName) {
        this.currentPageName = currentPageName;
    }

    public Page(final String currentPageName, final UsersInput currentUser,
                final ActionsInput action) {
        this.currentPageName = currentPageName;
        this.currentUser = currentUser;
        this.action = action;
    }

    /**
     * Method that returns the current page name.
     * @return the current page name
     */
    public String getCurrentPageName() {
        return currentPageName;
    }

    /**
     * Method that sets the current page name.
     * @param currentPageName the current page name
     */
    public void setCurrentPageName(final String currentPageName) {
        this.currentPageName = currentPageName;
    }

    /**
     * Method that returns the current user.
     * @return the current user
     */
    public UsersInput getCurrentUser() {
        return currentUser;
    }

    /**
     * Method that sets the current user.
     * @param currentUser the current user
     */
    public void setCurrentUser(final UsersInput currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * Method that returns the current action.
     * @return the current action
     */
    public ActionsInput getAction() {
        return action;
    }

    /**
     * Method that sets the current action.
     * @param action the current action
     */
    public void setAction(final ActionsInput action) {
        this.action = action;
    }

    /**
     * Method that executes the "on page" action using a factory design pattern.
     *
     * @param users       the list of users
     * @param action      the action to be executed
     * @param currentPage the current page
     * @param movies      the list of movies
     * @param output      the output array node
     */
    public void onPageAction(final ArrayList<UsersInput> users, final ActionsInput action,
                             final Page currentPage, final ArrayList<MoviesInput> movies,
                             final ArrayNode output) {
        OnPageAction onPageAction = OnPageActionFactory.
                getAction(action.getFeature(), users, action, currentPage, movies, output);
        onPageAction.execute();
    }

    /**
     * Method that executes the "change page" action using a factory design pattern.
     *
     * @param action      the action to be executed
     * @param currentPage the current page
     * @param movies      the list of movies
     * @param output      the output array node
     * @param pages
     */
    public void changePageAction(final ActionsInput action, final Page currentPage,
                                 final ArrayList<MoviesInput> movies,
                                 final ArrayNode output, final Stack<Page> pages) {
        ChangePageAction changePageAction = ChangePageActionFactory.
                getAction(action.getPage(), action, currentPage, movies, output, pages);
        changePageAction.execute();
    }

    /**
     * The backAction method allows the user to navigate to the previously visited page.
     * @param action an object representing an action performed by the user
     * @param currentPage the current page the user is on
     * @param output an object used to write JSON output
     * @param pages a stack of pages the user has visited
     * @param movies a list of movies
     */
    public void backAction(final ActionsInput action, final Page currentPage,
                           final ArrayNode output, final Stack<Page> pages,
                           final ArrayList<MoviesInput> movies) {
        if (currentPage.getCurrentUser() != null && !pages.isEmpty()) {
            Page newPage = null;
            if (!pages.isEmpty()) {
                newPage = pages.pop();
            } else {
                action.setError("Error");
                Write.writePageError(null, action, output);
                return;
            }

            if (newPage.getCurrentPageName().equals("Homepage autentificat")) {
                currentPage.setCurrentPageName("Homepage autentificat");
            }

            ChangePageAction changePageAction = ChangePageActionFactory.
                    getAction(newPage.getCurrentPageName(), action, currentPage, movies, output,
                            pages);
            if (changePageAction != null) {
                changePageAction.execute();
                pages.pop();
            }
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
