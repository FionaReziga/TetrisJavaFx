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
     * @param width
     * @param height
     * @param sizeCase
     * @param color
     */
    public Board(int width, int height, int sizeCase, Color color) {
        this.grid = new Grid(width, height, sizeCase, color);
    }

    /**
     * Renvoie le plateau de jeu
     * @return
     */
    public Grid getGrid() {
        return grid;
    }

}
