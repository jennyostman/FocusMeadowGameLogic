package exarb.fmgamelogic.service;

import exarb.fmgamelogic.event.EventDispatcher;
import exarb.fmgamelogic.event.TimerCountWorkEvent;
import exarb.fmgamelogic.exceptions.TimerSessionException;
import exarb.fmgamelogic.model.Timer;
import exarb.fmgamelogic.model.TimerSession;
import exarb.fmgamelogic.repository.TimerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class TimerService {

    private EventDispatcher eventDispatcher;
    private TimerRepository timerRepository;


    /**
     * Gets a timer result
     * @param timerCountSessionId a timer session object id
     * @return TimerSession
     */
    public TimerSession getTimerResultById(String timerCountSessionId){
        Optional<TimerSession> timerSession = timerRepository.findById(timerCountSessionId);
        if (timerSession.isPresent()){
            return timerSession.get();
        } else {
            log.info("Error when fetching timerSession for {}", timerCountSessionId);
            throw new TimerSessionException("It was not possible to get timer data");
        }
    }


    /**
     * Saves a timer session and sends a TimerCountWorkEvent message
     * @param timer timer session data
     * @return TimerSession
     */
    @Transactional
    public TimerSession saveTimerSession(final Timer timer) {
        TimerSession timerSession = convertToTimerSession(timer);
        TimerSession savedTimerSession = timerRepository.save(timerSession);
        eventDispatcher.send(new TimerCountWorkEvent(savedTimerSession.getId(), savedTimerSession.getUserId()));
        log.info("TimerCountWorkEvent message was sent");
        return savedTimerSession;
    }

    /**
     * Converts a Timer object to a TimerSession object
     * @param timer timer session data
     * @return TimerSession
     */
    private TimerSession convertToTimerSession(final Timer timer){
        return new TimerSession(timer.getUserId(), timer.getTime(),
                timer.getSessionType(), timer.isInterrupted(),
                timer.getFlowerToPlant());
    }

}
