package exarb.fmgamelogic.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import exarb.fmgamelogic.client.UserDeserializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Gamelogics version of a User
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@JsonDeserialize(using = UserDeserializer.class)
public class User {
    private final String id;
    private final String userName;

    public User() {
        id = null;
        userName = null;
    }
}
