package exarb.fmgamelogic.repository;

import exarb.fmgamelogic.model.Flower;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepository extends MongoRepository<Flower, String> {
}
