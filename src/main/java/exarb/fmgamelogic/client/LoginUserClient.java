package exarb.fmgamelogic.client;


import exarb.fmgamelogic.client.dto.LoginUser;

/**
 * Interface to connect to User service
 */
public interface LoginUserClient {

    LoginUser retrieveUserById(final String userId);
}
