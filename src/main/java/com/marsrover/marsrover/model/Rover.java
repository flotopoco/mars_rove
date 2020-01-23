package com.marsrover.marsrover.model;

import com.marsrover.marsrover.utils.CardinalDirections;

public class Rover {

    int posX;
    int posY;
    String facing;
    private boolean flagRoadblock = false;

    public Rover(int posX, int posY, String facing) {
        this.posX = posX;
        this.posY = posY;
        this.facing = facing;
    }

    public void moveForward(marsMap map) {
        if (CardinalDirections.getEAST().equals(facing)) {
            move(map, posX + 1, posY, map.getMapSize());
        }
        if (CardinalDirections.getWEST().equals(facing)) {
            move(map, posX - 1, posY, map.getMapSize());
        }
        if (CardinalDirections.getNORTH().equals(facing)) {
            move(map, posX, posY + 1, map.getMapSize());
        }
        if (CardinalDirections.getSOUTH().equals(facing)) {
            move(map, posX, posY - 1, map.getMapSize());
        }
    }

    public void moveBack(marsMap map) {
        if (CardinalDirections.getWEST().equals(facing)) {
            move(map, posX + 1, posY, map.getMapSize());
        }
        if (CardinalDirections.getEAST().equals(facing)) {
            move(map, posX - 1, posY, map.getMapSize());
        }
        if (CardinalDirections.getSOUTH().equals(facing)) {
            move(map, posX, posY + 1, map.getMapSize());
        }
        if (CardinalDirections.getNORTH().equals(facing)) {
            move(map, posX, posY - 1, map.getMapSize());
        }
    }

    public void move(marsMap map, int finalX, int finalY, int mapSize) {
        int auxX = finalX;
        int auxY = finalY;
        if (finalX < 0 || finalY < 0) {
            auxX = (outOfMap(finalX, mapSize)) ? mapSize : finalX;
            auxY = (outOfMap(finalY, mapSize)) ? mapSize : finalY;
        } else if (finalX > mapSize || finalY > mapSize) {
            auxX = (outOfMap(finalX, mapSize)) ? 0 : finalX;
            auxY = (outOfMap(finalY, mapSize)) ? 0 : finalY;
        }
        if (!isRoadBlock(auxX, auxY, map)) {
            posX = auxX;
            posY = auxY;
        } else {
            flagRoadblock = true;
        }
    }

    public void turnLeft() {
        if (CardinalDirections.getSOUTH().equals(facing)) {
            facing = CardinalDirections.getEAST();
            return;
        } else if (CardinalDirections.getEAST().equals(facing)) {
            facing = CardinalDirections.getNORTH();
            return;
        } else if (CardinalDirections.getNORTH().equals(facing)) {
            facing = CardinalDirections.getWEST();
            return;
        } else if (CardinalDirections.getWEST().equals(facing)) {
            facing = CardinalDirections.getSOUTH();
            return;
        }
    }

    public void turnRight() {
        if (CardinalDirections.getSOUTH().equals(facing)) {
            facing = CardinalDirections.getWEST();
            return;
        } else if (CardinalDirections.getEAST().equals(facing)) {
            facing = CardinalDirections.getSOUTH();
            return;
        } else if (CardinalDirections.getNORTH().equals(facing)) {
            facing = CardinalDirections.getEAST();
            return;
        } else if (CardinalDirections.getWEST().equals(facing)) {
            facing = CardinalDirections.getNORTH();
            return;
        }
    }

    public void executeCommand(String command, marsMap map) {
        if (command != null) {
            command = command.toUpperCase();
            for (int i = 0; i < command.length(); i++) {
                char aux = command.charAt(i);
                processCommand(aux, map);
            }
        }
    }

    public void processCommand(char aux, marsMap map) {
        if (isRoadBlock(posX, posY, map)) {
            flagRoadblock = true;
        }
        if (!isFlagRoadblock()) {
            if (aux == 'L') {
                turnLeft();
            } else if (aux == 'R') {
                turnRight();
            } else if (aux == 'F') {
                moveForward(map);
            } else if (aux == 'B') {
                moveBack(map);
            }
        }
    }

    public boolean outOfMap(int position, int max) {
        return (position < 0 || position > max) ? true : false;
    }

    public boolean isRoadBlock(int x, int y, marsMap map) {
        return (map.getMap()[x][y] == 1) ? true : false;
    }

    public String getPosition() {
        if (!isFlagRoadblock()) {
            return "<tit><b>Final position:</tit> </br></b>X:" + posX + " Y:" + posY + " D:" + facing;
        } else {
            return "<b><error>OBSTACLE DETECTED - MISSION ABORTED</error></br> <tit>Final position:</tit> </br></b> X:" + posX + "Y:" + posY + "D:" + facing;
        }
    }

    public boolean isFlagRoadblock() {
        return flagRoadblock;
    }

    public String getFacing() {
        return facing;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
