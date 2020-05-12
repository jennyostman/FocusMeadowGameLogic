package exarb.fmgamelogic.service;

import exarb.fmgamelogic.enums.FlowerType;
import exarb.fmgamelogic.exceptions.FlowerException;
import exarb.fmgamelogic.exceptions.UserGameDataException;
import exarb.fmgamelogic.model.Flower;
import exarb.fmgamelogic.model.TimerSession;
import exarb.fmgamelogic.model.User;
import exarb.fmgamelogic.model.UserGameData;
import exarb.fmgamelogic.repository.UserGameDataRepository;
import exarb.fmgamelogic.utility.UserGameDataUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class UserGameDataService {

    private final UserGameDataRepository userGameDataRepository;
    private final UserGameDataUtility userGameDataUtility;


    /**
     * Creates a new user game data object and saves it
     * @param user user object
     */
    public void createNewUserGameData(User user){
        UserGameData userGameData = userGameDataUtility.createNewUserGameData(user);
        UserGameData savedUserGameData = userGameDataRepository.save(userGameData);
        log.info("Saved new UserGameData {}", savedUserGameData);
    }

    /**
     * Updates a users game data by adding data from a new TimerSession.
     * @param savedTimerSession timer session data
     * @return UserGameData
     */
    public UserGameData updateUserGameData(TimerSession savedTimerSession){
        UserGameData oldUserGameData = getUserGameDataByUserId(savedTimerSession.getUserId());
        UserGameData updatedUserGameData = userGameDataUtility.updateUserGameData(oldUserGameData, savedTimerSession);
        UserGameData savedUserGameData = userGameDataRepository.save(updatedUserGameData);
        log.info("Updated UserGameData for {}", savedTimerSession.getUserId());
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
            throw new UserGameDataException("Game data was not found");
        }
    }

    /**
     * Buys a flower and adds it to the users list of flowers
     * that is possible to choose from when running the timer.
     * @param allFlowers all flowers that is possible to buy
     * @param userId a users id
     * @param flowerType a flowers enum type
     * @return UserGameData
     */
    public UserGameData buyFlower(Map<FlowerType, Flower> allFlowers, String userId, FlowerType flowerType){
        UserGameData userGameData = getUserGameDataByUserId(userId);
        int costForFlower = allFlowers.get(flowerType).getPrice();
        int usersAmountOfCoins = userGameData.getCoins();

        if (costForFlower <= usersAmountOfCoins) {
            List<FlowerType> usersTimerFlowers = userGameData.getChoosableFlowers();
            usersTimerFlowers.add(flowerType);
            userGameData.setChoosableFlowers(usersTimerFlowers);
            userGameData.setCoins(usersAmountOfCoins-costForFlower);
            log.info("Game data updated when buying a new flower for user {}", userId);
            return userGameDataRepository.save(userGameData);
        }
        else {
            log.info("User did not have enough money to buy a new flower");
            throw new FlowerException("Price was too high");
        }
    }

}
