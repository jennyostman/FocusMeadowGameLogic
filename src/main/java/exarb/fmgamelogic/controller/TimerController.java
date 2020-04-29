package exarb.fmgamelogic.controller;

import exarb.fmgamelogic.model.TimerResult;
import exarb.fmgamelogic.service.TimerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/timer", produces = APPLICATION_JSON_VALUE)
public class TimerController {

    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @GetMapping(value = "/result/{timerCountSessionId}")
    public ResponseEntity<TimerResult> getTimerResultForTimerCountSessionId(@PathVariable String timerCountSessionId) {
        return ResponseEntity.ok().body(timerService.getTimerResultForUser(timerCountSessionId));
    }

}
