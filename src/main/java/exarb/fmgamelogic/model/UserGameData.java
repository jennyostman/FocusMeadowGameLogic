package exarb.fmgamelogic.model;

import exarb.fmgamelogic.enums.FlowerType;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
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
    private List<FlowerType> choosableFlowers;
    private Date updated;
    private int earnedHours;
    private int earnedMinutes;


    public UserGameData() {
    }

    public UserGameData(String userId, String userName, int minutesThisDay, List<FlowerType> focusTimeFlowers,
                        int coins, List<FlowerType> choosableFlowers, Date updated) {
        this.userId = userId;
        this.userName = userName;
        this.minutesThisDay = minutesThisDay;
        this.focusTimeFlowers = focusTimeFlowers;
        this.coins = coins;
        this.choosableFlowers = choosableFlowers;
        this.updated = updated;
    }

}
