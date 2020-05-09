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

    public User fromLoginUserToUser(LoginUser loginUser){
        User user = new User();
        user.setUserId(loginUser.getId());
        user.setUserName(loginUser.getUserName());
        System.out.println("user: " + user);
        return user;
    }

}
