package exarb.fmgamelogic.adapter;

import exarb.fmgamelogic.client.LoginUserClient;
import exarb.fmgamelogic.client.dto.LoginUser;
import exarb.fmgamelogic.model.User;
import exarb.fmgamelogic.service.UserGameDataService;
import exarb.fmgamelogic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserAdapter {

    private final LoginUserClient loginUserClient;
    private final UserGameDataService userGameDataService;
    private final UserService userService;

    /**
     * Rest call to get a user by userId. Saves a user.
     * @param userId
     */
    public void createUserGameDataForNewUser(String userId) {
        try {
            LoginUser loginUser = getLoginUserFromUserService(userId);
            User user = userService.fromLoginUserToUser(loginUser);
            userGameDataService.createNewUserGameData(user);
            log.info("created new UserGameData for {}", userId);
        } catch (Exception e){
            log.info("Error when createUserGameDataForNewUser");
            // TODO: throw exception
        }
    }

    private LoginUser getLoginUserFromUserService(String userId){
        LoginUser loginUser = loginUserClient.retrieveUserById(userId);
        log.info("loginUser {} received from User service", loginUser.getUserName());
        return loginUser;
    }
}
