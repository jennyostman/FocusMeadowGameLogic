package exarb.fmgamelogic.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import exarb.fmgamelogic.client.dto.LoginUser;

import java.io.IOException;


public class LoginUserDeserializer extends JsonDeserializer<LoginUser> {

    /**
     * Deserializes a User object coming from the User service into Gamelogics version of User,
     * and allows to create a new object containing only the necessary variables.
     * @param jsonParser provides access to JSON data
     * @param deserializationContext context for deserialization
     * @return LoginUser
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public LoginUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        return new LoginUser(node.get("id").asText(), node.get("userName").asText());
    }
}
