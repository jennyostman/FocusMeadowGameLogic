package exarb.fmgamelogic.model;

import exarb.fmgamelogic.enums.FlowerType;
import exarb.fmgamelogic.enums.SessionType;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


/**
 * Model for a timer session
 */
@Data
@Document("timerSession")
public class TimerSession {

    private String id;
    private String userId;
    private int time;
    private SessionType sessionType;
    private boolean interrupted;
    private Date date;
    private FlowerType flowerToPlant;

    public TimerSession() {
    }

    public TimerSession(String userId, int time, SessionType sessionType, boolean interrupted, FlowerType flowerToPlant) {
        this.userId = userId;
        this.time = time;
        this.sessionType = sessionType;
        this.interrupted = interrupted;
        this.date = new Date();
        this.flowerToPlant = flowerToPlant;
    }
}
