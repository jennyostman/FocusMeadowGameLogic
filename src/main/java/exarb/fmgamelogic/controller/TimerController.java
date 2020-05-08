package exarb.fmgamelogic.controller;

import exarb.fmgamelogic.adapter.TimerAdapter;
import exarb.fmgamelogic.model.Timer;
import exarb.fmgamelogic.model.TimerSession;
import exarb.fmgamelogic.model.UserGameData;
import exarb.fmgamelogic.service.TimerService;
import exarb.fmgamelogic.service.UserGameDataService;
import exarb.fmgamelogic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/timers", produces = APPLICATION_JSON_VALUE)
public class TimerController {

    private final TimerAdapter timerAdapter;
    private final TimerService timerService;
    private final UserService userService;


    /**
     * - - - -
     * @param timer, a Timer object containing the result from a timer session
     * @return ResponseEntity<UserGameData> A users game data
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/timer/save")
    public ResponseEntity<UserGameData> updatesUserGameDataByAddingNewTimerSession(@RequestHeader String userId,
                                                                                   @Valid @NotNull @RequestBody Timer timer) {

        // userService.saveUser("bbb", "hej");

        return ResponseEntity.ok().body(timerAdapter.updatesUserGameData(timer, userId));
    }

    @GetMapping(value = "/game/{userId}")
    public ResponseEntity<UserGameData> getUserGameDataByUserId(@PathVariable String userId){  //
        System.out.println("i getUserGameDataByUserId");
        System.out.println("userId" + userId);
        return ResponseEntity.ok().body(timerAdapter.getUserGameData(userId));
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
