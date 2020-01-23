package com.marsrover.marsrover.model;

import java.util.Random;

public class marsMap {

    int[][] map;

    public marsMap(int size) {
        this.map = new int[size][size];
    }


    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public void buildRoadBlocks(int count, int sizeMap) {
        for (int i = 0; i < count; i++) {
            Random r = new Random();
            int roadBlockX = r.nextInt(sizeMap);
            int roadBlockY = r.nextInt(sizeMap);
            this.map[roadBlockX][roadBlockY] = 1;
        }
    }

    @Override
    public String toString() {
        StringBuilder showMap = new StringBuilder();
        for (int y = map.length - 1; y >= 0; y--) {
            for (int x = 0; x < map.length; x++) {
                showMap.append(" " + map[x][y] + " ");
            }
            showMap.append("</br>");
        }
        return showMap.toString();
    }

    public int getMapSize() {
        return map.length - 1;
    }
}
