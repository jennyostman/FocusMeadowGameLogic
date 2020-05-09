package exarb.fmgamelogic.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Model for user
 */
@Data
@NoArgsConstructor
@Document("users")
public class User {

    private String id;
    private String userId;
    private String userName;


    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
