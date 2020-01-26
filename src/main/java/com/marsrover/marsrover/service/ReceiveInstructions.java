package com.marsrover.marsrover.service;

import com.marsrover.marsrover.model.MarsMap;
import com.marsrover.marsrover.model.Rover;
import com.marsrover.marsrover.utils.Utils;

public class ReceiveInstructions {

    StringBuilder result = new StringBuilder("<html><head><style>"+
            "body {background-color: lightblue;}h1 {color: black;text-align: center;}tit{color: #622569;} error{color: red;}"+
            "p {font-family: verdana;font-size: 20px;}</style></head><body> <h1><b>Map of Mars</b> </br>");
    public void startProcess(Rover rover, MarsMap map, String command) {
        if ("Command Null".equals(command)) {
            result.append("<tip>"+command+"</tip>").toString() ;
        } else if (Utils.isValidCommand(command)) {
            int initialPosX = rover.getPosX();
            int initialPosY = rover.getPosY();
            String facing = rover.getFacing();
            rover.executeCommand(command, map);
            result.append(map.toString()+"</h1>");result.append("</br> <tit><b>Command sent to rover:</b></tit></br> "+command.toUpperCase());
            result.append("</br> <tit><b>Rover start on position</b></tit></br> X:"+initialPosX+" Y:"+initialPosY+" D:"+facing);
            result.append("</br>"+rover.getPosition());
        } else {
            result.append("<tip>Invalid Command</tip>").toString();
        }

    }

    public String getResult() {
        return result.toString();
    }
}
