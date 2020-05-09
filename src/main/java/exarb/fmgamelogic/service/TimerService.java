package exarb.fmgamelogic.service;

import exarb.fmgamelogic.enums.FlowerType;
import exarb.fmgamelogic.event.EventDispatcher;
import exarb.fmgamelogic.event.TimerCountWorkEvent;
import exarb.fmgamelogic.model.Timer;
import exarb.fmgamelogic.model.TimerSession;
import exarb.fmgamelogic.repository.TimerRepository;
import exarb.fmgamelogic.utility.UserGameDataUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class TimerService {

    private EventDispatcher eventDispatcher;
    private TimerRepository timerRepository;


    public TimerSession getTimerResultForUser(String timerCountSessionId){
        Optional<TimerSession> timerSession = timerRepository.findById(timerCountSessionId);
        if (timerSession.isPresent()){
            return timerSession.get();
        } else {
            // Throw exception
            return null;
        }
    }


    /**
     * Saves a timer session and sends out a TimerCountWorkEvent
     * @param timer timer session data
     * @return TimerSession
     */
    @Transactional
    public TimerSession saveTimerSession(final Timer timer, String userId) {
        System.out.println("TimerService saveTimerSession");
        // TODO: Väljer blomma att plantera här just nu
        timer.setFlowerToPlant(FlowerType.SUNFLOWER);

        TimerSession timerSession = convertToTimerSession(timer, userId);
        TimerSession savedTimerSession = timerRepository.save(timerSession);
        eventDispatcher.send(new TimerCountWorkEvent(savedTimerSession.getId(), savedTimerSession.getUserId()));
        return savedTimerSession;
    }

    /**
     * Converts a Timer object to a TimerSession object, and adds a date
     * @param timer timer session data
     * @param userId a users id
     * @return TimerSession
     */
    private TimerSession convertToTimerSession(final Timer timer, String userId){
        return new TimerSession(userId, timer.getTime(),
                timer.getSessionType(), timer.isInterrupted(),
                new Date(), timer.getFlowerToPlant());
    }

}
