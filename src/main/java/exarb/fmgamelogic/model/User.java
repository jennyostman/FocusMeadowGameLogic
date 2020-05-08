package exarb.fmgamelogic.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document("users")
public class User {

    private String id;
    private String userId;
    private String userName;

    public User() {
    }

    public User(String userId, String userName) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
