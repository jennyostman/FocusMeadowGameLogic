package exarb.fmgamelogic.adapter;

import exarb.fmgamelogic.enums.FlowerType;
import exarb.fmgamelogic.model.Flower;
import exarb.fmgamelogic.model.UserGameData;
import exarb.fmgamelogic.service.FlowerService;
import exarb.fmgamelogic.service.UserGameDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;


@AllArgsConstructor
@Slf4j
@Service
public class FlowerAdapter {

    private final FlowerService flowerService;
    private final UserGameDataService userGameDataService;

    /**
     * Buys a flower and adds it to the users list of flowers
     * that is possible to choose from when running the timer.
     * @param flowerType
     * @param userId
     * @return
     */
    public UserGameData buyFlower(FlowerType flowerType, String userId){
        Map<FlowerType, Flower> allFlowers = flowerService.getAllFlowers();
        return userGameDataService.buyFlower(allFlowers, userId, flowerType);
    }


}
