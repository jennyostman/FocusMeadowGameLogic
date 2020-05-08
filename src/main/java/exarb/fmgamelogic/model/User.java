package exarb.fmgamelogic.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document("users")
public class User {

    private String userId;
    private String userName;

    public User(String id, String userName) {
    }
}
