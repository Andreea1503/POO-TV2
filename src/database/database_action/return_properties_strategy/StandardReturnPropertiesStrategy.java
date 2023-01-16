package database.database_action.return_properties_strategy;

import input.user.UsersInput;

/**
 * Concrete implementation of the ReturnPropertiesStrategy interface for returning properties
 * for a standard user.
 */
public class StandardReturnPropertiesStrategy implements ReturnPropertiesStrategy {
    /**
     * Method that returns the properties for a standard user.
     *
     * @param user the user for which to return the properties
     */
    @Override
    public void returnProperties(final UsersInput user) {
        user.setTokensCount(user.getTokensCount() + 2);
    }
}
