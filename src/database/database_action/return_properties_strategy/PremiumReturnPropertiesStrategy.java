package database.database_action.return_properties_strategy;

import input.user.UsersInput;

public class PremiumReturnPropertiesStrategy implements ReturnPropertiesStrategy {
    @Override
    public void returnProperties(UsersInput user) {
        user.setNumFreePremiumVideos(user.getNumFreePremiumVideos() + 1);
    }
}
