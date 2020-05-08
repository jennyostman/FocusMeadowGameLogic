package exarb.fmgamelogic.service;

import exarb.fmgamelogic.model.TimerSession;
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

    private UserGameDataRepository userGameDataRepository;
    private UserGameDataUtility userGameDataUtility;

    /**
     * Creates a new user game data object
     * @param userId a users id
     * @return UserGameData
     */
    public UserGameData createNewUserGameData(String userId){
        UserGameData userGameData = userGameDataUtility.createNewUserGameData(userId);
        return userGameDataRepository.save(userGameData);
    }

    /**
     * Updates a users game data by adding data from a new TimerSession.
     * @param savedTimerSession timer session data with date
     * @return UserGameData
     */
    public UserGameData updateUserGameData(TimerSession savedTimerSession){
        UserGameData oldUserGameData = getUserGameDataUsingUserId(savedTimerSession.getUserId());
        UserGameData updatedUserGameData = userGameDataUtility.updateUserGameData(oldUserGameData, savedTimerSession);
        UserGameData savedUserGameData = userGameDataRepository.save(updatedUserGameData);
        System.out.println(savedUserGameData);
        return savedUserGameData;
    }

    /**
     * Get a users game data
     * @param userId a users id
     * @return UserGameData
     */
    private UserGameData getUserGameDataUsingUserId(String userId) {
        Optional<UserGameData> userGameData = userGameDataRepository.findUserGameDataByUserId(userId);
        return userGameData.orElseGet(() -> createNewUserGameData(userId));
    }


}
