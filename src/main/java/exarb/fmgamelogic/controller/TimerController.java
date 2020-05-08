package exarb.fmgamelogic.controller;

import exarb.fmgamelogic.adapter.TimerAdapter;
import exarb.fmgamelogic.model.Timer;
import exarb.fmgamelogic.model.TimerSession;
import exarb.fmgamelogic.model.UserGameData;
import exarb.fmgamelogic.service.TimerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/timers", produces = APPLICATION_JSON_VALUE)
public class TimerController {

    private final TimerAdapter timerAdapter;
    private final TimerService timerService;

    public TimerController(TimerAdapter timerAdapter, TimerService timerService) {
        this.timerAdapter = timerAdapter;
        this.timerService = timerService;
    }


    /**
     * Saves the received timer and sends out a TimerCountWorkEvent
     * @param timer, a Timer object containing the result from a timer session
     * @return ResponseEntity<UserGameData> A users game data
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/timer/save")
    public ResponseEntity<UserGameData> updatesUserGameDataByAddingNewTimerSession(@RequestHeader String userId,
                                                                                   @Valid @NotNull @RequestBody Timer timer) {
        return ResponseEntity.ok().body(timerAdapter.updatesUserGameData(timer, userId));
    }

    /**
     * Gets a TimerSession object from an id
     * @param timerCountSessionId, a TimerSession object id
     * @return ResponseEntity<TimerSession>
     */
    @GetMapping(value = "/result/{timerCountSessionId}")
    public ResponseEntity<TimerSession> getTimerResultForTimerCountSessionId(@PathVariable String timerCountSessionId) {
        return ResponseEntity.ok().body(timerService.getTimerResultForUser(timerCountSessionId));
    }

}
