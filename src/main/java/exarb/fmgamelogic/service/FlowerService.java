package exarb.fmgamelogic.service;

import exarb.fmgamelogic.enums.FlowerType;
import exarb.fmgamelogic.exceptions.FlowerException;
import exarb.fmgamelogic.model.AllAvailableFlowers;
import exarb.fmgamelogic.model.Flower;
import exarb.fmgamelogic.repository.FlowerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@AllArgsConstructor
@Slf4j
@Service
public class FlowerService {

    private final FlowerRepository flowerRepository;

    /**
     * Adds a flower to the flowers the user can buy
     * @param flower a flower object
     * @return Map<FlowerType, Flower>
     */
    public Map<FlowerType, Flower> addNewFlowerToBuy(Flower flower){
        List<AllAvailableFlowers> allFlowers = flowerRepository.findAll();
        if (allFlowers.size() > 0){
            AllAvailableFlowers flowersUsersCanBuy = allFlowers.get(0);
            flowersUsersCanBuy.getFlowersToBuy().put(flower.getFlowerType(), flower);
            return flowerRepository.save(flowersUsersCanBuy).getFlowersToBuy();
        }
        else {
            log.info("Error when getting all the flowers from the database");
            throw new FlowerException("Could not find a place to save the new flower");
        }
    }

    /**
     * Gets all flowers a user kan buy and then use when a timer is set
     * @return Map<FlowerType, Flower>
     */
    public Map<FlowerType, Flower> getAllFlowers(){
        Optional<AllAvailableFlowers> flowerMapObj = flowerRepository.findAll().stream().findFirst();
        if (flowerMapObj.isPresent()){
            return flowerMapObj.get().getFlowersToBuy();
        }
        else {
            log.info("Error when getting all the flowers from the database");
            throw new FlowerException("Could not find any flowers");
        }
    }

}
