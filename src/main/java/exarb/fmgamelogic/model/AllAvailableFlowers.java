package exarb.fmgamelogic.model;

import exarb.fmgamelogic.enums.FlowerType;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document("allFlowers")
public class AllAvailableFlowers {

    private String id;
    private Map<FlowerType, Flower> flowerToBuyMap;

}
