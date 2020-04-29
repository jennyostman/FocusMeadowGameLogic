package exarb.fmgamelogic.service;

import exarb.fmgamelogic.model.TimerResult;
import exarb.fmgamelogic.repository.TimerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimerService {

    private final TimerRepository timerRepository;

    public TimerService(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }

    public TimerResult getTimerResultForUser(String timerCountSessionId){

        // *** För test *** :
        List<TimerResult> timerResult = timerRepository.getTimerResultByUserId("abc");
        System.out.println("från db: " + timerResult);

        TimerResult fetchedTr;
        if (timerResult.size() > 0){
            fetchedTr = timerResult.get(0);
        }
        else {
            fetchedTr = null;
        }
        return fetchedTr;
    }

}
