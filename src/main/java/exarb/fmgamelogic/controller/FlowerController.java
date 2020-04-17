package exarb.fmgamelogic.controller;

import exarb.fmgamelogic.model.Flower;
import exarb.fmgamelogic.service.FlowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/flowers", produces = APPLICATION_JSON_VALUE)
public class FlowerController {

    private FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/create")
    public ResponseEntity<Flower> createFlower(@Valid @NotNull @RequestBody Flower flower) {
        return ResponseEntity.ok().body(flowerService.createFlower(flower));
    }
}
