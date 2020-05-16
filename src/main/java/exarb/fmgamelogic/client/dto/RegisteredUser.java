package exarb.fmgamelogic.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import exarb.fmgamelogic.client.RegisteredUserDeserializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Dto containing user data
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@JsonDeserialize(using = RegisteredUserDeserializer.class)
public class RegisteredUser {
    private final String id;
    private final String userName;

    public RegisteredUser() {
        id = null;
        userName = null;
    }
}