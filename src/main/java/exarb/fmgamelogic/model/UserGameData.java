package exarb.fmgamelogic.model;

import exarb.fmgamelogic.enums.FlowerType;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;


@Data
@Document("userGameData")
public class UserGameData {

    private String id;
    private String userId;
    private String userName;
    private int minutesThisDay;
    private List<FlowerType> focusTimeFlowers;
    private List<FlowerType> meadow;
    private int coins;
    // private List<Achievement> achievements;  // Lista med de man tilldelats
    private List<FlowerType> choosableFlowers;
    // Så att man uppdaterar den som är för dagen, och kan radera den som är för igår.
    private LocalDate updated;
    private int earnedHours;
    private int earnedMinutes;


    public UserGameData() {
    }

    public UserGameData(String userId, String userName, int minutesThisDay, List<FlowerType> focusTimeFlowers,
                        int coins, List<FlowerType> choosableFlowers, LocalDate updated) {
        this.userId = userId;
        this.userName = userName;
        this.minutesThisDay = minutesThisDay;
        this.focusTimeFlowers = focusTimeFlowers;
        this.coins = coins;
        this.choosableFlowers = choosableFlowers;
        this.updated = updated;
    }

}
