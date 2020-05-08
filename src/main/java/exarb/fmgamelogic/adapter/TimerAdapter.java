package exarb.fmgamelogic.adapter;

import exarb.fmgamelogic.model.Timer;
import exarb.fmgamelogic.model.TimerSession;
import exarb.fmgamelogic.model.User;
import exarb.fmgamelogic.model.UserGameData;
import exarb.fmgamelogic.service.TimerService;
import exarb.fmgamelogic.service.UserGameDataService;
import exarb.fmgamelogic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class TimerAdapter {

    private final TimerService timerService;
    private final UserGameDataService userGameDataService;
    private final UserService userService;



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

    public UserGameData getUserGameData(String userId){
        return userGameDataService.getUserGameDataUsingUserId(userId);
    }


}
