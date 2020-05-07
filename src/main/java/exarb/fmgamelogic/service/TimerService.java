package exarb.fmgamelogic.service;

import exarb.fmgamelogic.event.EventDispatcher;
import exarb.fmgamelogic.event.TimerCountWorkEvent;
import exarb.fmgamelogic.model.Timer;
import exarb.fmgamelogic.repository.TimerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TimerService {

    private EventDispatcher eventDispatcher;
    private TimerRepository timerRepository;

    @Autowired
    public TimerService(EventDispatcher eventDispatcher, TimerRepository timerRepository) {
        this.eventDispatcher = eventDispatcher;
        this.timerRepository = timerRepository;
    }

    /**
     * Saves a timer session and sends out a TimerCountWorkEvent
     * @param timer
     * @return
     */
    @Transactional
    public boolean saveTimerSession(final Timer timer) {
        Timer savedTimerSession = timerRepository.save(timer);
        eventDispatcher.send(new TimerCountWorkEvent(savedTimerSession.getId(), savedTimerSession.getUserId()));
        return timer.isInterrupted();
    }


    public Timer getTimerResultForUser(String timerCountSessionId){

        // *** För test *** :
        List<Timer> timerResult = timerRepository.getTimerResultById("5eb3c9209df1090e7ca83b0f");
        System.out.println("från db: " + timerResult);

        Timer fetchedTimer;
        if (timerResult.size() > 0){
            fetchedTimer = timerResult.get(0);
        }
        else {
            fetchedTimer = null;
        }
        return fetchedTimer;
    }

}
