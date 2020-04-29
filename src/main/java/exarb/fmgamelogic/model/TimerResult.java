package exarb.fmgamelogic.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "timerResult")
public class TimerResult {

    private String id;
    private String userId;
    private Long timeInMillis;
    private boolean isWork;

    public TimerResult(String userId, Long timeInMillis, boolean isWork) {
        this.userId = userId;
        this.timeInMillis = timeInMillis;
        this.isWork = isWork;
    }

    public TimerResult() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(Long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }

    @Override
    public String toString() {
        return "TimerResult{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", timeInMillis=" + timeInMillis +
                ", isWork=" + isWork +
                '}';
    }
}
