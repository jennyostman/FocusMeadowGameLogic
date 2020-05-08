package exarb.fmgamelogic.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import exarb.fmgamelogic.client.LoginUserDeserializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@JsonDeserialize(using = LoginUserDeserializer.class)
public class LoginUser {
    private final String id;
    private final String userName;

    public LoginUser() {
        id = null;
        userName = null;
    }
}