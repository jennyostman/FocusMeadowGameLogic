package exarb.fmgamelogic.adapter;

import exarb.fmgamelogic.model.Timer;
import exarb.fmgamelogic.model.TimerSession;
import exarb.fmgamelogic.model.UserGameData;
import exarb.fmgamelogic.service.TimerService;
import exarb.fmgamelogic.service.UserGameDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TimerAdapter {

    private final TimerService timerService;
    private final UserGameDataService userGameDataService;

    public TimerAdapter(TimerService timerService, UserGameDataService userGameDataService) {
        this.timerService = timerService;
        this.userGameDataService = userGameDataService;
    }

    /**
     * Updates a users game data by adding a new timer session
     * @param timer, timer session data
     * @param userId, a users id
     * @return UserGameData
     */
    public UserGameData updatesUserGameData(final Timer timer, String userId){
        TimerSession savedTimerSession = timerService.saveTimerSession(timer, userId);
        UserGameData savedUserGameData = userGameDataService.updateUserGameData(savedTimerSession);
        System.out.println(savedUserGameData);
        return savedUserGameData;
    }

}
