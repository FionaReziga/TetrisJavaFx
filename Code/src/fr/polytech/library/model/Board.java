package fr.polytech.library.model;

import javafx.scene.paint.Color;

import java.util.Observable;

/**
 * Created by REZIGA on 16/05/2017.
 * Classe qui génère le plateau de jeu
 */
public class Board  extends Observable {
    protected Grid grid;

    public Board(int width, int height, int sizeCase, Color color) {
        this.grid = new Grid(width, height, sizeCase, color);
    }

    public Grid getGrid() {
        return grid;
    }
}
