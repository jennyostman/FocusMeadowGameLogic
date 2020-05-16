package exarb.fmgamelogic.client;

import exarb.fmgamelogic.client.dto.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This implementation of the UserClient interface connects to the User service via REST
 */
@Component
public class RegisteredUserClientImpl implements RegisteredUserClient {

    private final RestTemplate restTemplate;
    private final String userHost;

    @Autowired
    public RegisteredUserClientImpl(RestTemplate restTemplate, @Value("${userHost}") String userHost) {
        this.restTemplate = restTemplate;
        this.userHost = userHost;
    }


    /**
     * Makes a GET call to the user-service to retrieve information about the user
     * @param userId a users id
     * @return RegisteredUser
     */
    @Override
    public RegisteredUser retrieveUserById(String userId) {
        return restTemplate.getForObject(userHost + "/users/user/" + userId, RegisteredUser.class);
    }
}
