package exarb.fmgamelogic.client;

import exarb.fmgamelogic.client.dto.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This implementation of the UserClient interface connects to the User service via REST
 */
@Component
public class LoginUserClientImpl implements LoginUserClient {

    private final RestTemplate restTemplate;
    private final String userHost;

    @Autowired
    public LoginUserClientImpl(RestTemplate restTemplate, @Value("${userHost}") String userHost) {
        this.restTemplate = restTemplate;
        this.userHost = userHost;
    }

    @Override
    public LoginUser retrieveUserById(String userId) {
        return restTemplate.getForObject(userHost + "/users/user/" + userId, LoginUser.class);
    }
}
