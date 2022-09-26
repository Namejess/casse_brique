package edu.jdrouin.cassebrique;

import java.awt.*;
import javax.swing.*;

public class CasseBrique extends Canvas {

    public CasseBrique() throws InterruptedException {
        JFrame fenetre = new JFrame("Casse brique");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();
        //On définie la hauteur / largeur de l'écran
        panneau.setPreferredSize(new Dimension(500, 500));
        setBounds(0, 0, 500,500);
        //On ajoute cette classe (qui hérite de Canvas) comme composant du panneau principal
        panneau.add(this);

        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.requestFocus();

        //On indique que le raffraichissement de l'ecran doit être fait manuellement.
        createBufferStrategy(2);
        setIgnoreRepaint(true);
        this.setFocusable(false);

        demarrer();
    }

    public void demarrer() throws InterruptedException {

        long indexFrame = 0;
        int xBalle = 500;
        int yBalle = 500;
        int vitesseHorizontaleBalle = -2;
        int vitesseVerticaleBalle = 2;

        while(true) {
            indexFrame ++;
            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //-----------------------------------------------

            dessin.setColor(Color.WHITE); // reset dessin par un panneau blanc
            dessin.fillRect(0, 0, 500, 500);

            //dessin balle

            xBalle += vitesseHorizontaleBalle;

            if(xBalle < 0 || xBalle > 500){
               vitesseHorizontaleBalle *= -1;
            }

            if(yBalle < 0 || yBalle > 500){
                vitesseVerticaleBalle *= -1;
            }

            dessin.setColor(Color.MAGENTA); // dessin balle rouge
            dessin.fillOval(xBalle, 235, 30, 30);

            //-----------------------------------------------
            dessin.dispose();
            getBufferStrategy().show();

            Thread.sleep(1000 / 60);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new CasseBrique();
    }
}