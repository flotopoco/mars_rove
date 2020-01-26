package com.marsrover.marsrover.controller;

import com.marsrover.marsrover.model.Rover;
import com.marsrover.marsrover.model.MarsMap;
import com.marsrover.marsrover.service.ReceiveInstructions;
import com.marsrover.marsrover.utils.CardinalDirections;
import com.marsrover.marsrover.utils.Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarsRoverController {

    private static final String template = "Hello, %s!";

    @GetMapping("/execute")
    public String execute(@RequestParam(value = "command", defaultValue = "Command Null") String command,
                          @RequestParam(value = "worldsize", defaultValue = "2") int sizeWorld,
                          @RequestParam(value = "roadBlockCount", defaultValue = "0") int roadBlockCount,
                          @RequestParam(value = "initialPosX", defaultValue = "0") int initialPosX,
                          @RequestParam(value = "initialPosY", defaultValue = "0") int initialPosY,
                          @RequestParam(value = "facing", defaultValue = "NORTH") String facing) {

        //create the map with size and roadblocks
        if (sizeWorld < 2) {
            sizeWorld = 2;
        }
        MarsMap map = new MarsMap(sizeWorld);
        map.buildRoadBlocks(roadBlockCount, sizeWorld);

        if (initialPosX < 0 || initialPosX >= sizeWorld) {
            initialPosX = 0;
        }
        if (initialPosY < 0 || initialPosY >= sizeWorld) {
            initialPosY = 0;
        }
        facing = (CardinalDirections.isCardinalDirection(facing)) ? facing : CardinalDirections.getNORTH();
        Rover rover = new Rover(initialPosX, initialPosY, facing);

        ReceiveInstructions receiveInstructions = new ReceiveInstructions();

        receiveInstructions.startProcess(rover, map, command);

        return receiveInstructions.getResult();

    }
}

