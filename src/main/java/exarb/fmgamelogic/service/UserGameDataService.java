package exarb.fmgamelogic.service;

import exarb.fmgamelogic.model.TimerSession;
import exarb.fmgamelogic.model.User;
import exarb.fmgamelogic.model.UserGameData;
import exarb.fmgamelogic.repository.UserGameDataRepository;
import exarb.fmgamelogic.utility.UserGameDataUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class UserGameDataService {

    private final UserGameDataRepository userGameDataRepository;
    private final UserGameDataUtility userGameDataUtility;


    /**
     * Creates a new user game data object
     * @param user user object
     * @return UserGameData
     */
    public void createNewUserGameData(User user){
        UserGameData userGameData = userGameDataUtility.createNewUserGameData(user);
        UserGameData savedUserGameData = userGameDataRepository.save(userGameData);
        log.info("Saved new UserGameData {}", savedUserGameData);
    }

    /**
     * Updates a users game data by adding data from a new TimerSession.
     * @param savedTimerSession timer session data with date
     * @return UserGameData
     */
    public UserGameData updateUserGameData(TimerSession savedTimerSession, String userId){
        UserGameData oldUserGameData = getUserGameDataByUserId(userId);
        UserGameData updatedUserGameData = userGameDataUtility.updateUserGameData(oldUserGameData, savedTimerSession);
        UserGameData savedUserGameData = userGameDataRepository.save(updatedUserGameData);
        System.out.println(savedUserGameData);
        log.info("Updated UserGameData for {}", userId);
        return savedUserGameData;
    }

    /**
     * Get a users game data
     * @param userId a users id
     * @return UserGameData
     */
    public UserGameData getUserGameDataByUserId(String userId) {
        Optional<UserGameData> userGameData = userGameDataRepository.findUserGameDataByUserId(userId);
        if (userGameData.isPresent()){
            return userGameData.get();
        }
        else {
            log.info("No UserGameData was found for id {}", userId);
            // TODO: skicka exception
            return null;
        }
    }

}
