package exarb.fmgamelogic.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("flowers")
public class Flower {

    private String id;
    private String name;
    private String imageUrl;

    public Flower(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
