package exarb.fmgamelogic.repository;

import exarb.fmgamelogic.model.Timer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimerRepository extends MongoRepository<Timer, String> {
}
