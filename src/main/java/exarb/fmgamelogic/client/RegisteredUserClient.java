package exarb.fmgamelogic.client;

import exarb.fmgamelogic.client.dto.RegisteredUser;

/**
 * Interface to connect to User service
 */
public interface RegisteredUserClient {

    RegisteredUser retrieveUserById(final String userId);
}
