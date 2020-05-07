package exarb.fmgamelogic.client;

import exarb.fmgamelogic.client.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This implementation of the UserClient interface connects to the User service via REST
 */
@Component
public class UserClientImpl implements UserClient {

    private final RestTemplate restTemplate;
    private final String userHost;

    @Autowired
    public UserClientImpl(RestTemplate restTemplate, @Value("${userHost}") String userHost) {
        this.restTemplate = restTemplate;
        this.userHost = userHost;
    }

    @Override
    public User retrieveUserById(String userId) {
        return restTemplate.getForObject(userHost + "/users/user/" + userId, User.class);
    }
}
