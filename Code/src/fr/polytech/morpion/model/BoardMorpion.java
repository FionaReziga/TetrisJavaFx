package fr.polytech.morpion.model;

import fr.polytech.library.model.Board;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.TRANSPARENT;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui définit le plateau du Jeu Puissance 4
 */
public class BoardMorpion extends Board {

    /**
     * Constructeur du plateau de Morpion
     * @param width : largeur de la grille
     * @param height : hauteur de la grille
     * @param sizeCase : taille de la case
     * @param color : couleur des cases
     */
    public BoardMorpion(int width, int height, int sizeCase, Color color) {
        super(width, height, sizeCase, color);
        grid = new GridMorpion(width, height, sizeCase, color);
    }

    /**
     * Synchronise les mouvements de la pièce
     * @param offsetX : future position X
     * @param offsetY : future position Y
     */
    public synchronized boolean movePiece(int offsetX, int offsetY) {
        GridMorpion currentGrid = (GridMorpion) grid;
        grid.savePiece();
        currentGrid.checkWinner();
        setChanged();
        notifyObservers();
        return true;
    }

    /**
     * Retourne vrai si le jeu est terminé
     * @return GameOver : vrai ou faux selon la valeur de l'attribut gameOver de la grille
     */
    public boolean gameOver(){
        GridMorpion currentGrid = (GridMorpion) grid;
        return currentGrid.isGameOver();
    }

    /**
     * Retourne la grille du morpion
     * @return GridMorpion
     */
    public GridMorpion getGrid() {
        return (GridMorpion) grid;
    }

    /**
     * Réinitialise la grille
     */
    public void newGame() {
        GridMorpion currentGrid = (GridMorpion) grid;
        currentGrid.reset();
    }
}

