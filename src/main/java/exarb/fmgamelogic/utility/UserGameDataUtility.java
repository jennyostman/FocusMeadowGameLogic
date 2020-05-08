package exarb.fmgamelogic.utility;


import exarb.fmgamelogic.enums.FlowerType;
import exarb.fmgamelogic.model.TimerSession;
import exarb.fmgamelogic.model.UserGameData;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class UserGameDataUtility {


    /**
     * Creates a new user game data object
     * @param userId a users id
     * @return UserGameData
     */
    public UserGameData createNewUserGameData(String userId){

        String userName = "abc";

        List<FlowerType> choosableFlower = new ArrayList<>();
        choosableFlower.add(FlowerType.SUNFLOWER);
        UserGameData userGameData = new UserGameData(
                userId,
                userName,
                0,
                new ArrayList<>(),
                0,
                choosableFlower,
                LocalDate.now());


        // BÖR GÖRAS VID INLOGG?
        // Måste göras även under tiden man är inloggad.
        // En koll varje gång men hämtar en UserGameData?
        // Måste skapas/uppdateras när man loggar in + när man är klar med en timerSession

        // När det är en ny dag: Skapa en ny UserGameData, där
        // int minutesThisDay,
        // List<FlowerType> flowersOnMeadow,
        // FlowerType[][] meadow
        // är tomma.
        // Ta bort den gamla UserGameData, och ersätt med den nya.


        return userGameData;
    }

    /**
     * Updates a users game data
     * @param userGameData a users old game data
     * @param savedTimerSession a users timer session data
     * @return UserGameData
     */
    public UserGameData updateUserGameData(UserGameData userGameData, TimerSession savedTimerSession){
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
        return userGameData;
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
        // TODO: ta bort hårdkodning
        int x = 8;
        int sizeOfMeadow = x * x;
        List<FlowerType> meadow = new ArrayList<>();



        if (newFocusTimeFlowers.size() <= sizeOfMeadow){
            int amountOfGrassToFillUpWith = sizeOfMeadow - newFocusTimeFlowers.size();

            System.out.println("antal gräs: " + amountOfGrassToFillUpWith);
            for (int i = 0; i < amountOfGrassToFillUpWith; i++) {
                meadow.add(FlowerType.GRASS);
            }

            System.out.println("antal blommor: " + newFocusTimeFlowers.size());
            meadow.addAll(newFocusTimeFlowers);
            System.out.println("meadow.size(): " + meadow.size());

            Collections.shuffle(meadow);
        }
        else {
            // TODO: throw MeadowToSmallException
        }

        return meadow;
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

    private Map<String, Integer> divideTotalMinutesIntoHoursAndMinutes(int newTotalOfMinutes){
        Map<String, Integer> hoursAndMinutes = new HashMap<>();
        int hours = newTotalOfMinutes / 60;
        int minutes = newTotalOfMinutes % 60;
        hoursAndMinutes.put("hours", hours);
        hoursAndMinutes.put("minutes", minutes);
        return hoursAndMinutes;
    }

    /**
     * calculates how many coins the user has earned based on new focus time
     * @param focusTime amount of minutes a user focused
     * @return integer
     */
    private int calculateAmountOfCoins(int focusTime){
        return focusTime / 5;
    }

}
