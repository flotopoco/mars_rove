package com.marsrover.marsrover.utils;

public class CardinalDirections {

    private static final String NORTH = "NORTH";
    private static final String EAST = "EAST";
    private static final String SOUTH = "SOUTH";
    private static final String WEST = " WEST";

    public static String getNORTH() {
        return NORTH;
    }

    public static String getEAST() {
        return EAST;
    }

    public static String getSOUTH() {
        return SOUTH;
    }

    public static String getWEST() {
        return WEST;
    }

    public static boolean isCardinalDirection(String aux){
        return (aux != null && (aux.equals(WEST) || aux.equals(EAST) || aux.equals(SOUTH) || aux.equals(NORTH)));
    }
}
