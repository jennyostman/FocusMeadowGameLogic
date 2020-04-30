package exarb.fmgamelogic.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model for a timer session
 */
@Data
@Document("timer")
public class Timer {

    private String id;
    private String userId;
    private int time;
    private boolean workType;
    private boolean interrupted;

    public Timer() {
    }

    public Timer(int time, boolean workType, boolean interrupted) {
        this.time = time;
        this.workType = workType;
        this.interrupted = interrupted;
    }
}
