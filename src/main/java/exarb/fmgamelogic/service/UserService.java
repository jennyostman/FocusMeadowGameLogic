package exarb.fmgamelogic.service;

import exarb.fmgamelogic.client.dto.RegisteredUser;
import exarb.fmgamelogic.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    /**
     * converts RegisteredUser object to User object
     * @param registeredUser a registeredUser object
     * @return User
     */
    public User fromRegisteredUserToUser(RegisteredUser registeredUser){
        return new User(registeredUser.getId(), registeredUser.getUserName());
    }

}
