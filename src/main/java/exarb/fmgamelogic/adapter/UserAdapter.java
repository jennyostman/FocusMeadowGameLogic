package exarb.fmgamelogic.adapter;

import exarb.fmgamelogic.client.RegisteredUserClient;
import exarb.fmgamelogic.client.dto.RegisteredUser;
import exarb.fmgamelogic.exceptions.UserGameDataException;
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

    private final RegisteredUserClient registeredUserClient;
    private final UserGameDataService userGameDataService;
    private final UserService userService;

    /**
     * Creates a user game data object for a registered user
     * @param userId a users id
     */
    public void createUserGameDataForNewUser(String userId) {
        try {
            RegisteredUser registeredUser = getRegisteredUserFromUserService(userId);
            User user = userService.fromRegisteredUserToUser(registeredUser);
            userGameDataService.createNewUserGameData(user);
            log.info("created new UserGameData for {}", userId);
        } catch (Exception e){
            log.info("Error when creating a UserGameData for registered user with id {}", userId);
            throw new UserGameDataException("It was not possible to save necessary user data");
        }
    }

    /**
     * Calls user service to get a user by userId
     * @param userId a users id
     * @return RegisteredUser
     */
    private RegisteredUser getRegisteredUserFromUserService(String userId){
        RegisteredUser registeredUser = registeredUserClient.retrieveUserById(userId);
        log.info("registeredUser {} received from User service", registeredUser.getUserName());
        return registeredUser;
    }
}
