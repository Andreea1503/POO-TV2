package database.database_action.return_properties_strategy;

import input.user.UsersInput;

/**
 * Concrete implementation of the ReturnPropertiesStrategy interface for returning properties
 * for a premium user.
 */
public class PremiumReturnPropertiesStrategy implements ReturnPropertiesStrategy {
    /**
     * Method that returns the properties for a premium user.
     *
     * @param user the user for which to return the properties
     */
    @Override
    public void returnProperties(final UsersInput user) {
        user.setNumFreePremiumVideos(user.getNumFreePremiumVideos() + 1);
    }
}
