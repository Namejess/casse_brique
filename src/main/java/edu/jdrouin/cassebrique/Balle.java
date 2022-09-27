package edu.jdrouin.cassebrique;

import java.awt.*;
import java.util.ArrayList;

public class Balle {

    protected int x;
    protected int y;
    protected int vitesseHorizontal;
    protected int vitesseVertical;
    protected int diametre;
    protected int diametreReflet;
    protected int decalageReflet;
    protected Color couleur;
    protected ArrayList<Balle> listePoints = new ArrayList<>();

    protected long indexFrame = 0;

    public Balle(double x, double y, int vitesseHorizontal, int vitesseVertical, int diametre, Color couleur) {
        this.x = (int) x;
        this.y = (int) y;
        this.vitesseHorizontal = vitesseHorizontal == 0 ? 1 : vitesseVertical;
        this.vitesseVertical = vitesseVertical == 0 ? 1 : vitesseHorizontal;
        this.couleur = couleur;
        this.setDiametre(diametre);
        this.setListePoints(getListePoints());
    }

    public Balle(int x, int y, int diametre) {
        this.x = x;
        this.y = y;
        this.diametre = diametre;
    }



    public void testCollision(int largeurEcran, int hauteurEcran){
        if(x < 0 || x > largeurEcran - diametre) {
            inverseVitesseHorizontal();
        }

        if(y < 0 || y > hauteurEcran - diametre) {
            inverseVitesseVertical();
        }
    }

    public void deplacer (){
        x += vitesseHorizontal;
        y += vitesseVertical;
    }
    public void inverseVitesseVertical(){
        vitesseVertical *= -1;
    }

    public void inverseVitesseHorizontal(){
        vitesseHorizontal *= -1;
    }

    public void dessiner (Graphics2D dessin){

        dessin.setColor(couleur);
        dessin.fillOval(x, y, diametre, diametre);
        dessin.setColor(Color.WHITE);
        dessin.fillOval(x + decalageReflet,
                y + decalageReflet,
                diametreReflet,
                diametreReflet);
    }


    public void dessinerPoint (Graphics2D dessin) {

        indexFrame ++;
        int cumul = 0;

        if(indexFrame % 10 == 0 && vitesseVertical != 0 && vitesseHorizontal != 0){
            Balle points = new Balle(x, y, diametre);
            listePoints.add(points);
        }

        for (Balle point : listePoints) {
            cumul += 1;
            dessin.setColor(Color.YELLOW);
            dessin.fillOval(point.x, point.y, point.diametre, point.diametre);

        }
        if(cumul == 10){
            Balle premierListePoints = listePoints.get(0);
            listePoints.remove(premierListePoints);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVitesseHorizontal() {
        return vitesseHorizontal;
    }

    public void setVitesseHorizontal(int vitesseHorizontal) {
        this.vitesseHorizontal = vitesseHorizontal;
    }

    public int getVitesseVertical() {
        return vitesseVertical;
    }

    public void setVitesseVertical(int vitesseVertical) {
        this.vitesseVertical = vitesseVertical;
    }

    public int getDiametre() {
        return diametre;
    }

    public void setDiametre(int diametre) {
        this.diametre = diametre;
        this.diametreReflet = (int) (diametre * 0.3f);
        this.decalageReflet = (int) (diametre * 0.2f);
    }

    public int getDiametreReflet() {
        return diametreReflet;
    }


    public int getDecalageReflet() {
        return decalageReflet;
    }


    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public ArrayList<Balle> getListePoints() {
        return listePoints;
    }

    public void setListePoints(ArrayList<Balle> listePoints) {
        this.listePoints = listePoints;
    }


}


