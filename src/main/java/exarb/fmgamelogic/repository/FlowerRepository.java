package exarb.fmgamelogic.repository;

import exarb.fmgamelogic.model.AllAvailableFlowers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepository extends MongoRepository<AllAvailableFlowers, String> {

}
