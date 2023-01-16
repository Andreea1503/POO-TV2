package database.change_page;


import database.Page;
import write.Write;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.action.ActionsInput;

import java.util.Stack;

/**
 * Class that executes the login command
 */
public class ChangePageToLogin implements ChangePageAction {
    private ActionsInput action;
    private Page currentPage;
    private ArrayNode output;
    private Stack<Page> pages;


    public ChangePageToLogin(final ActionsInput action, final Page currentPage,
                             final ArrayNode output, final Stack<Page> pages) {
        this.action = action;
        this.currentPage = currentPage;
        this.output = output;
        this.pages = pages;
    }

    /**
     * Method that returns the action
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
     * Method that returns the output array node
     * @return output
     */
    public ArrayNode getOutput() {
        return output;
    }

    /**
     * Method that executes the login command and changes the current page to login or outputs an
     * error message if the current page is not the start page
     */
    @Override
    public void execute() {
        if (currentPage.getCurrentPageName().equals("Homepage neautentificat")) {
            currentPage.setCurrentPageName("login");
        } else {
            action.setError("Error");
            Write.writePageError(null, action, output);
        }
    }
}
