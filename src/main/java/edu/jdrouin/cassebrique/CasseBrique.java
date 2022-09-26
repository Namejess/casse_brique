package edu.jdrouin.cassebrique;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class CasseBrique extends Canvas {

    protected int largeurEcran = 500;
    protected int hauteurEcran = 700;


    public CasseBrique() throws InterruptedException {
        JFrame fenetre = new JFrame("Casse brique");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();
        //On définie la hauteur / largeur de l'écran
        panneau.setPreferredSize(new Dimension(largeurEcran, hauteurEcran));
        setBounds(0, 0, largeurEcran,hauteurEcran);
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
        ArrayList<Balle> listeBalle = new ArrayList<Balle>();

        for (int i = 0; i <= 100; i ++){
                listeBalle.add(
                    new Balle(
                        Utils.nombreRandom(250, 500),
                        Utils.nombreRandom(250, 500),
                        Utils.nombreRandom(1, 6),
                        Utils.nombreRandom(1, 6),
                        Utils.nombreRandom(5, 40),
                    new Color(
                        (float) Math.random(),
                        (float) Math.random(),
                        (float) Math.random())));
        }

        while(true) {
            indexFrame ++;
            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //-----------------------------
            //reset dessin
            dessin.fillRect(0,0,largeurEcran,hauteurEcran);

            //dessin balle
            for (Balle balle : listeBalle){
                balle.dessinerPoint(dessin);
                balle.dessiner(dessin);
                balle.deplacer();
                balle.testCollision(largeurEcran, hauteurEcran);
            }

            //-----------------------------
            dessin.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new CasseBrique();
    }
}