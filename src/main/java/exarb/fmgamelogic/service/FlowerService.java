package exarb.fmgamelogic.service;

import exarb.fmgamelogic.model.Flower;
import exarb.fmgamelogic.repository.FlowerRepository;
import org.springframework.stereotype.Service;

@Service
public class FlowerService {

    private FlowerRepository flowerRepository;

    public FlowerService(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    public Flower createFlower(Flower flower) {
        Flower savedFlower = flowerRepository.save(flower);
        return savedFlower;
    }
}
