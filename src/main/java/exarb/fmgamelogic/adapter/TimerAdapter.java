package exarb.fmgamelogic.adapter;

import exarb.fmgamelogic.model.Timer;
import exarb.fmgamelogic.model.TimerSession;
import exarb.fmgamelogic.model.UserGameData;
import exarb.fmgamelogic.service.TimerService;
import exarb.fmgamelogic.service.UserGameDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class TimerAdapter {

    private final TimerService timerService;
    private final UserGameDataService userGameDataService;


    /**
     * Updates a users game data object by adding a new timer session
     * @param timer, timer session data
     * @return UserGameData
     */
    public UserGameData updatesUserGameData(final Timer timer){
        TimerSession savedTimerSession = timerService.saveTimerSession(timer);
        UserGameData savedUserGameData = userGameDataService.updateUserGameData(savedTimerSession);
        return savedUserGameData;
    }

    /**
     * Gets user game data object
     * @param userId a users id
     * @return UserGameData
     */
    public UserGameData getUserGameDataByUserId(String userId){
        return userGameDataService.getUserGameDataByUserId(userId);
    }


}
