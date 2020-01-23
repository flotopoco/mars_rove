package com.marsrover.marsrover.controller;

import com.marsrover.marsrover.model.Rover;
import com.marsrover.marsrover.model.marsMap;
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
        if (sizeWorld <2){
            sizeWorld = 2;
        }
        marsMap map = new marsMap(sizeWorld);
        map.buildRoadBlocks(roadBlockCount, sizeWorld);

        if (initialPosX<0 || initialPosX>=sizeWorld){
            initialPosX=0;
        }
        if (initialPosY<0 || initialPosY>=sizeWorld){
            initialPosY=0;
        }
        facing = (CardinalDirections.isCardinalDirection(facing))?facing:CardinalDirections.getNORTH();
        Rover rover = new Rover(initialPosX, initialPosY, facing);
        StringBuilder result = new StringBuilder("<html><head><style>"+
                "body {background-color: lightblue;}h1 {color: black;text-align: center;}tit{color: #622569;} error{color: red;}"+
                "p {font-family: verdana;font-size: 20px;}</style></head><body> <h1><b>Map of Mars</b> </br>");
        if ("Command Null".equals(command)) {
            return result.append("<tip>"+command+"</tip>").toString() ;
        } else if (Utils.isValidCommand(command)) {
            rover.executeCommand(command, map);
            result.append(map.toString()+"</h1>");
        } else {
            return result.append("<tip>Invalid Command</tip>").toString();
        }
        result.append("</br> <tit><b>Command sent to rover:</b></tit></br> "+command.toUpperCase());
        result.append("</br> <tit><b>Rover start on position</b></tit></br> X:"+initialPosX+" Y:"+initialPosY+" D:"+facing);
        result.append("</br>"+rover.getPosition());
        //        return new String(String.format(template, aux));
//        return "\n" + rover.getPosition();
        return result.toString();

    }
}

