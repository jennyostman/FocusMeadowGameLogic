package exarb.fmgamelogic.controller;


import exarb.fmgamelogic.adapter.FlowerAdapter;
import exarb.fmgamelogic.enums.FlowerType;
import exarb.fmgamelogic.model.Flower;
import exarb.fmgamelogic.model.UserGameData;
import exarb.fmgamelogic.service.FlowerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/flowers", produces = APPLICATION_JSON_VALUE)
public class FlowerController {

    private final FlowerService flowerService;
    private final FlowerAdapter flowerAdapter;

    /**
     * Gets all flower that are possible to buy
     * @return ResponseEntity<Map<FlowerType, Flower>>
     */
    @GetMapping(value = "/all")
    public ResponseEntity<Map<FlowerType, Flower>> getAllFlowers(){
        return ResponseEntity.ok().body(flowerService.getAllFlowers());
    }

    /**
     * Adds a flower to the flowers the user can buy
     * @param flower a flower object
     * @return ResponseEntity<Map<FlowerType, Flower>>
     */
    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<FlowerType, Flower>> addNewFlowerToBuy(@Valid @NotNull @RequestBody Flower flower) {
        return ResponseEntity.ok().body(flowerService.addNewFlowerToBuy(flower));
    }

    /**
     * Buys a flower and adds it to the users list of flowers
     * that is possible to choose from when running the timer.
     * @param flowerType a flowers enum type
     * @param userId a users id
     * @return ResponseEntity<UserGameData>
     */
    @PostMapping(value = "/buy/{flowerType}/{userId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserGameData> buyFlower(@PathVariable FlowerType flowerType,
                                                  @PathVariable String userId) {
        return ResponseEntity.ok().body(flowerAdapter.buyFlower(flowerType, userId));
    }
}
