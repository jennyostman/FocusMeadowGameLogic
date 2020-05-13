package exarb.fmgamelogic.model;

import exarb.fmgamelogic.enums.FlowerType;
import exarb.fmgamelogic.enums.SessionType;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model for timer result
 */
@Data
@Document("timer")
public class Timer {

    private String id;
    private String userId;
    private int time;
    private SessionType sessionType;
    private boolean interrupted;
    private FlowerType flowerToPlant;

    public Timer() {
    }

    public Timer(int time, SessionType sessionType, boolean interrupted) {
        this.time = time;
        this.sessionType = sessionType;
        this.interrupted = interrupted;
    }

    public Timer(String userId, int time, SessionType sessionType, boolean interrupted, FlowerType flowerToPlant) {
        this.userId = userId;
        this.time = time;
        this.sessionType = sessionType;
        this.interrupted = interrupted;
        this.flowerToPlant = flowerToPlant;
    }
}
