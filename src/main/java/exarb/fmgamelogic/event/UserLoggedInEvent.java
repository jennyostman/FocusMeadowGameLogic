package exarb.fmgamelogic.event;

import java.io.Serializable;

/**
 * This is a model for an event that is sent when a user has logged in successfully.
 */
public class UserLoggedInEvent implements Serializable {

    private String userId;

    public UserLoggedInEvent(String userId) {
        this.userId = userId;
    }

    public UserLoggedInEvent() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
