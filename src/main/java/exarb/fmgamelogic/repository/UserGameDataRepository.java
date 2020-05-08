package exarb.fmgamelogic.repository;

import exarb.fmgamelogic.model.UserGameData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserGameDataRepository extends MongoRepository<UserGameData, String> {

    Optional<UserGameData> findUserGameDataByUserId(String userId);
}
