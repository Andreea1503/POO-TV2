package database.database_action.return_properties_strategy;

import input.user.UsersInput;

/**
 * Interface for returning properties for a user.
 */
public interface ReturnPropertiesStrategy {
    /**
     * Method for returning properties for a user.
     *
     * @param user the user for which to return the properties
     */
    void returnProperties(UsersInput user);
}
