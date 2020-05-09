package exarb.fmgamelogic.controller;

import exarb.fmgamelogic.adapter.TimerAdapter;
import exarb.fmgamelogic.model.Timer;
import exarb.fmgamelogic.model.TimerSession;
import exarb.fmgamelogic.model.UserGameData;
import exarb.fmgamelogic.service.TimerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/timers", produces = APPLICATION_JSON_VALUE)
public class TimerController {

    private final TimerAdapter timerAdapter;
    private final TimerService timerService;


    /**
     * Updates user game data when a new timer session is added
     * @param timer, a Timer object containing the result from a timer session
     * @return ResponseEntity<UserGameData> A users game data
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/timer/save")
    public ResponseEntity<UserGameData> addingNewTimerSessionToUserGameData(@RequestHeader String userId,
                                                                            @Valid @NotNull @RequestBody Timer timer) {
        return ResponseEntity.ok().body(timerAdapter.updatesUserGameData(timer, userId));
    }

    /**
     * Get a users game data
     * @param userId a users id
     * @return ResponseEntity<UserGameData>
     */
    @GetMapping(value = "/game/{userId}")
    public ResponseEntity<UserGameData> getUserGameDataByUserId(@PathVariable String userId){
        return ResponseEntity.ok().body(timerAdapter.getUserGameDataByUserId(userId));
    }

    /**
     * Gets a TimerSession object from an id
     * @param timerCountSessionId, a TimerSession object id
     * @return ResponseEntity<TimerSession>
     */
    @GetMapping(value = "/result/{timerCountSessionId}")
    public ResponseEntity<TimerSession> getTimerResultForTimerCountSessionId(@PathVariable String timerCountSessionId) {
        return ResponseEntity.ok().body(timerService.getTimerResultById(timerCountSessionId));
    }

}
