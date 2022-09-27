package edu.jdrouin.cassebrique;

import java.awt.*;

public class Utils {

    protected Color couleur = new Color(255, 255,255, 100);

    public Utils() {
    }

    public static int nombreRandom(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException();
        }

        return (int) (Math.random() * max + min);
    }


    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
}
