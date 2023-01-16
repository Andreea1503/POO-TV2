package database.database_action.return_properties_strategy;

import input.user.UsersInput;

public class StandardReturnPropertiesStrategy implements ReturnPropertiesStrategy {
    @Override
    public void returnProperties(UsersInput user) {
        user.setTokensCount(user.getTokensCount() + 2);
    }
}
