package edu.jdrouin.cassebrique;

public class Utils {

    public Utils() {
    }

    public static int nombreRandom(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (int) (Math.random() * max + min);
    }

}
