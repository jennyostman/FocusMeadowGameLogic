package exarb.fmgamelogic.client;

import exarb.fmgamelogic.client.dto.User;

/**
 * Interface to connect to User service
 */
public interface UserClient {

    User retrieveUserById(final String userId);
}
