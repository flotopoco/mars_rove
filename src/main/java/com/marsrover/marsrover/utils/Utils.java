package com.marsrover.marsrover.utils;

public class Utils {

    public static boolean isValidCommand(String cmd) {
        if (cmd != null && !cmd.isEmpty()) {
            for (int i = 0; i < cmd.length(); i++) {
                String aux = Character.toString(cmd.charAt(i));
                if (!("FfBbLlRr".contains(aux))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
