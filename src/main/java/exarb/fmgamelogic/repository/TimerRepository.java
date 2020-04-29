package exarb.fmgamelogic.repository;

import exarb.fmgamelogic.model.TimerResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TimerRepository extends MongoRepository<TimerResult, String> {

    List<TimerResult> getTimerResultByUserId(String userId);
}
