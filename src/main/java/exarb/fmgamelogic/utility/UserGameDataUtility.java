package exarb.fmgamelogic.utility;


import exarb.fmgamelogic.enums.FlowerType;
import exarb.fmgamelogic.model.TimerSession;
import exarb.fmgamelogic.model.User;
import exarb.fmgamelogic.model.UserGameData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Slf4j
@Component
public class UserGameDataUtility {


    /**
     * Creates a new user game data object
     * @param user a users data
     * @return UserGameData
     */
    public UserGameData createNewUserGameData(User user){
        List<FlowerType> choosableFlower = new ArrayList<>();
        choosableFlower.add(FlowerType.SUNFLOWER);
        UserGameData userGameData = new UserGameData(
                user.getUserId(),
                user.getUserName(),
                0,
                new ArrayList<>(),
                0,
                choosableFlower,
                new Date());
        return userGameData;
    }

    /**
     * Updates a users game data
     * @param oldUserGameData a users old game data
     * @param savedTimerSession a users timer session data
     * @return UserGameData
     */
    public UserGameData updateUserGameData(UserGameData oldUserGameData, TimerSession savedTimerSession){

        UserGameData userGameData = prepareUserGameDataForNewDay(oldUserGameData);

        List<FlowerType> newFocusTimeFlowers = addItemToBePlacedOnMeadow(savedTimerSession, userGameData.getFocusTimeFlowers());
        userGameData.setFocusTimeFlowers(newFocusTimeFlowers);

        List<FlowerType> meadow = addItemsToMeadow(newFocusTimeFlowers);
        userGameData.setMeadow(meadow);

        if (!savedTimerSession.isInterrupted()){
            int newTotalOfMinutes = calculateTotalOfMinutes(userGameData.getMinutesThisDay(), savedTimerSession.getTime());
            Map<String, Integer> hoursAndMinutes = divideTotalMinutesIntoHoursAndMinutes(newTotalOfMinutes);
            userGameData.setMinutesThisDay(newTotalOfMinutes);
            userGameData.setEarnedHours(hoursAndMinutes.get("hours"));
            userGameData.setEarnedMinutes(hoursAndMinutes.get("minutes"));

            int earnedCoins = calculateAmountOfCoins(savedTimerSession.getTime());
            userGameData.setCoins(userGameData.getCoins() + earnedCoins);
        }

        userGameData.setUpdated(new Date());

        return userGameData;
    }

    /**
     * prepares a game user data object for a new gaming day
     * @param userGameData a users game data
     * @return UserGameData
     */
    public UserGameData prepareUserGameDataForNewDay(UserGameData userGameData){
        if (!isUpdatedToday(userGameData)){
            userGameData.setMinutesThisDay(0);
            userGameData.setFocusTimeFlowers(new ArrayList<>());
            userGameData.setMeadow(new ArrayList<>());
            userGameData.setEarnedHours(0);
            userGameData.setEarnedMinutes(0);
        }
        return userGameData;
    }

    /**
     * checks if the user game data is updated today
     * @param userGameData a users game data
     * @return boolean
     */
    public boolean isUpdatedToday(UserGameData userGameData){
        LocalDate lastUpdatedDate = convertToLocalDateViaMilisecond(userGameData.getUpdated());
        LocalDate todaysDate = LocalDate.now();
        return lastUpdatedDate.equals(todaysDate);
    }

    /**
     * converts java.util.date to LocalDate
     * @param dateToConvert java.util.date to convert
     * @return LocalDate
     */
    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * Adds new items to the list of earned items
     * @param savedTimerSession a users timer session data
     * @param focusTimeFlowers a list with earned items
     * @return List<FlowerType>
     */
    private List<FlowerType> addItemToBePlacedOnMeadow(TimerSession savedTimerSession, List<FlowerType> focusTimeFlowers){
        if (savedTimerSession.isInterrupted()){
            focusTimeFlowers.add(FlowerType.PILEOFDIRT);
        } else {
            int amountOfFlowers = calculateAmountOfFlowers(savedTimerSession.getTime());
            for (int i = 0; i < amountOfFlowers; i++ ){
                focusTimeFlowers.add(savedTimerSession.getFlowerToPlant());
            }
        }
        return focusTimeFlowers;
    }

    /**
     * Calculates amount of new items a user earned
     * @param minutes amount of minutes a user focused
     * @return integer
     */
    private int calculateAmountOfFlowers(int minutes){
        return minutes/15;
    }

    /**
     * creates a meadow with earned items and grass
     * @param newFocusTimeFlowers list of earned items
     * @return List<FlowerType>
     */
    private List<FlowerType> addItemsToMeadow(List<FlowerType> newFocusTimeFlowers){
        // TODO: ta bort hårdkodning när ängens storlek kan ändras
        int x = 8;
        int sizeOfMeadow = x * x;
        List<FlowerType> meadow = new ArrayList<>();

        List<FlowerType> focusTimeFlowers = adjustIfFlowersTooManyForMeadow(newFocusTimeFlowers, sizeOfMeadow);

        int amountOfGrassToFillUpWith = sizeOfMeadow - focusTimeFlowers.size();
        for (int i = 0; i < amountOfGrassToFillUpWith; i++) {
            meadow.add(FlowerType.GRASS);
        }
        meadow.addAll(focusTimeFlowers);
        Collections.shuffle(meadow);

        return meadow;
    }

    /**
     * Checks if amount of earned items are to many for the meadow. If so, removes items until they fit.
     * @param newFocusTimeFlowers list of earned items
     * @param sizeOfMeadow size of meadow
     * @return List<FlowerType>
     */
    private List<FlowerType> adjustIfFlowersTooManyForMeadow(List<FlowerType> newFocusTimeFlowers, int sizeOfMeadow){
        if (newFocusTimeFlowers.size() > sizeOfMeadow){
            boolean toLarge = true;
            while (toLarge){
                newFocusTimeFlowers.remove(0);
                if (newFocusTimeFlowers.size() == sizeOfMeadow){
                    toLarge = false;
                }
            }
        }
        return newFocusTimeFlowers;
    }

    /**
     * calculates the total amount of focused minutes
     * @param oldTotal old amount of total minutes
     * @param minutesToAdd amount of minutes a user focused
     * @return integer
     */
    private int calculateTotalOfMinutes(int oldTotal, int minutesToAdd){
        return oldTotal + minutesToAdd;
    }

    /**
     * Divides the total of focused minutes into hours and minutes
     * @param newTotalOfMinutes total minutes of focus
     * @return Map<String, Integer>
     */
    private Map<String, Integer> divideTotalMinutesIntoHoursAndMinutes(int newTotalOfMinutes){
        Map<String, Integer> hoursAndMinutes = new HashMap<>();
        int hours = newTotalOfMinutes / 60;
        int minutes = newTotalOfMinutes % 60;
        hoursAndMinutes.put("hours", hours);
        hoursAndMinutes.put("minutes", minutes);
        return hoursAndMinutes;
    }

    /**
     * calculates how many coins the user has earned based on focus time
     * @param focusTime amount of minutes a user focused
     * @return integer
     */
    private int calculateAmountOfCoins(int focusTime){
        return focusTime / 5;
    }

}
