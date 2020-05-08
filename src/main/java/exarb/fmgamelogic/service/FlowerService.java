package exarb.fmgamelogic.service;

import exarb.fmgamelogic.model.Flower;
import exarb.fmgamelogic.repository.FlowerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FlowerService {

    private FlowerRepository flowerRepository;

    public Flower createFlower(Flower flower) {
        Flower savedFlower = flowerRepository.save(flower);
        return savedFlower;
    }
}
