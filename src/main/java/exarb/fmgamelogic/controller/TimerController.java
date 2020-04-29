package exarb.fmgamelogic.controller;

import exarb.fmgamelogic.model.Flower;
import exarb.fmgamelogic.model.Timer;
import exarb.fmgamelogic.service.FlowerService;
import exarb.fmgamelogic.service.TimerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/timer", produces = APPLICATION_JSON_VALUE)
public class TimerController {

    private TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/save")
    public ResponseEntity<Boolean> saveTimerSession(@Valid @NotNull @RequestBody Timer timer) {
        System.out.println("timer: " + timer);
        return ResponseEntity.ok().body(timerService.saveTimerSession(timer));
    }
}
