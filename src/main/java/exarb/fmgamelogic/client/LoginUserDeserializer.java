package exarb.fmgamelogic.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import exarb.fmgamelogic.client.dto.LoginUser;

import java.io.IOException;

/**
 * Deserializes a User coming from the User service into Gamelogics version of User
 */
public class LoginUserDeserializer extends JsonDeserializer<LoginUser> {
    @Override
    public LoginUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        return new LoginUser(node.get("id").asText(), node.get("userName").asText());
    }
}
