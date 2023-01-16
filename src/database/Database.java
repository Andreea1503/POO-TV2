package database;

import com.fasterxml.jackson.databind.node.ArrayNode;
import database.database_action.DatabaseAction;
import database.on_page.Recommendation;
import database.on_page.subscription.MovieGenre;
import input.action.ActionsInput;
import input.movie.MoviesInput;
import input.user.UsersInput;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that contains the database of the application. It is used to navigate through the pages
 * and to execute the actions. It contains the current page, the current user and the list of
 * current movies.
 * It is also a singleton, because we only need one instance of the database for the entire
 * application.
 */
public final class Database {
    private static volatile Database instance;
    private Page currentPage;
    private Stack<Page> pages;
    private MovieGenre genre = new MovieGenre();

    private Database() {
        currentPage = new Page();
        pages = new Stack<>();
    }

    /**
     * Singleton method that returns the instance of the database.
     * @return the instance of the database
     */
    public static Database getInstance() {
        Database result = instance;
        if (result == null) {
            synchronized (Database.class) {
                result = instance;
                if (result == null) {
                    instance = result = new Database();
                }
            }
        }
        return result;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    public Stack<Page> getPages() {
        return pages;
    }

    public void setPages(final Stack<Page> pages) {
        this.pages = pages;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * Method that navigates through the pages of the application and executes the type of action
     * that is given as parameter.
     * @param actions the list of actions from the input
     * @param users the list of users from the input
     * @param movies the list of movies from the input
     * @param output the output array node
     */
    public void databaseNavigation(final ArrayList<ActionsInput> actions,
                                   final ArrayList<UsersInput> users,
                                   final ArrayList<MoviesInput> movies,
                                   final ArrayNode output) {
        currentPage.setCurrentPageName("Homepage neautentificat");

        for (ActionsInput action : actions) {
            action.setError(null);
            switch (action.getType()) {
                case "on page" -> currentPage.onPageAction(users, action, currentPage, movies,
                        output);
                case "change page" -> currentPage.changePageAction(action, currentPage, movies,
                        output, pages);
                case "back" -> currentPage.backAction(action, currentPage, output, pages, movies);
                case "database" -> DatabaseAction.databaseAction(movies, action, output, users);
                default -> {
                }
            }
        }

        if (currentPage.getCurrentUser() == null) {
            return;
        }
        if (currentPage.getCurrentUser().getCredentials().getAccountType().equals("premium")) {
            MoviesInput movie = new MoviesInput();
            Recommendation recommendation = new Recommendation(currentPage.getCurrentUser()
                    .getSubscribedGenres(), movie.allowedMoviesForUser(movies,
                    currentPage.getCurrentUser()));
            recommendation.notify(currentPage.getCurrentUser(), output);
        }
    }

    /**
     * Method that resets the database.
     */
    public void destroy() {
        instance = null;
    }
}
