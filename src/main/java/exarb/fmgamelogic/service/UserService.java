package exarb.fmgamelogic.service;

import exarb.fmgamelogic.client.UserClient;
import exarb.fmgamelogic.client.UserClientImpl;
import exarb.fmgamelogic.client.dto.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private UserClient userClient;

    public void getUserById(String userId) {
        User user = userClient.retrieveUserById(userId);
        log.info("User {} received from User service", user.getUserName());
    }
}
