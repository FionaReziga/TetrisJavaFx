package fr.polytech.library.model;

import javafx.scene.paint.Color;

import java.util.Observable;

/**
 * Created by REZIGA on 16/05/2017.
 * Classe qui génère le plateau de jeu
 */
public class Board  extends Observable {
    protected Grid grid;

    /**
     * Constructeur de la classe du plateau de jeu
     * @param width : largeur du plateau
     * @param height : hauteur du plateau
     * @param sizeCase : taille des cases
     * @param color : couleur du plateau
     */
    public Board(int width, int height, int sizeCase, Color color) {
        this.grid = new Grid(width, height, sizeCase, color);
    }

    /**
     * Renvoie le plateau de jeu
     * @return : une instance de Grid
     */
    public Grid getGrid() {
        return grid;
    }

}
