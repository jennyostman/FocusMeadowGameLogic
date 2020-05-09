package exarb.fmgamelogic.service;

import exarb.fmgamelogic.client.dto.LoginUser;
import exarb.fmgamelogic.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    /**
     * converts LoginUser object to User object
     * @param loginUser a loginUser object
     * @return User
     */
    public User fromLoginUserToUser(LoginUser loginUser){
        return new User(loginUser.getId(), loginUser.getUserName());
    }

}
