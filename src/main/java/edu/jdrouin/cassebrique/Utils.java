package edu.jdrouin.cassebrique;

import java.awt.*;

public class Utils {

    public Utils() {
    }

    public static int nombreRandom(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException();
        }

        return (int) (Math.random() * max + min);
    }



}
