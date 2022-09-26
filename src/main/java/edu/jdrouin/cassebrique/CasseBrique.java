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

        int[] vitesseHorizontaleBalle = -5;
        int vitesseVerticaleBalle = 4;
        int diametreBalle = 150;
        int diametrePetiteBalle = diametreBalle * 30/100;
        int decalagePetiteBalle = diametreBalle * 20/100;
        int[] xBalle = 500 - diametreBalle;
        int[] yBalle = 500 - diametreBalle;

        while(true) {
            indexFrame ++;
            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //-----------------------------------------------
            // reset dessin par un panneau blanc
            dessin.setColor(Color.WHITE);
            dessin.fillRect(0, 0, 500, 500);

            // dessin balle rouge
            dessin.setColor(Color.MAGENTA);
            dessin.fillOval(xBalle, yBalle, diametreBalle, diametreBalle);

            // dessin 2eme balle blanche
            dessin.setColor(Color.WHITE);
            dessin.fillOval(xBalle+decalagePetiteBalle, yBalle+decalagePetiteBalle, diametrePetiteBalle, diametrePetiteBalle);

            //mouvement balle
            xBalle += vitesseHorizontaleBalle;
            yBalle += vitesseVerticaleBalle;
            if(yBalle < 0 || yBalle > 500 - diametreBalle){
                vitesseVerticaleBalle *= -1;
            }
            if(xBalle < 0 || xBalle > 500 - diametreBalle){
                vitesseHorizontaleBalle *= -1;
            }

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