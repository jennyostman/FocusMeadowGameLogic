package exarb.fmgamelogic.model;

import exarb.fmgamelogic.enums.FlowerType;
import lombok.Data;


@Data
public class Flower {

    private String image;
    private String name;
    private FlowerType flowerType;
    private int price;

    public Flower(String image, String name, FlowerType flowerType, int price) {
        this.image = image;
        this.name = name;
        this.flowerType = flowerType;
        this.price = price;
    }
}
