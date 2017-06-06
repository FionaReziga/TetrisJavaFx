package fr.polytech.puissance4.model;

import fr.polytech.library.model.Board;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui définit le plateau du Jeu Puissance 4
 */
public class BoardPuissance extends Board {

    /**
     * Constructeur du plateau de puissance 4
     * @param width
     * @param height
     * @param sizeCase
     * @param color
     */
    public BoardPuissance(int width, int height, int sizeCase, Color color) {
        super(width, height, sizeCase, color);
        grid = new GridPuissance(width, height, sizeCase, color);
    }

    /**
     * Synchronisation des mouvements de la pièce
     * @param offsetX
     * @param offsetY
     */
    public synchronized boolean movePiece(int offsetX, int offsetY) {
        boolean move = grid.movePiece(offsetX, offsetY);
        GridPuissance currentGrid = (GridPuissance) grid;
        if(move) {
            currentGrid.checkWinner();
            if(!currentGrid.isGameOver()) currentGrid.generateNewPiece();
        }
        setChanged();
        notifyObservers();
        return move;
    }

    /**
     * Fin du jeu
     * @return
     */
    public boolean gameOver(){
        GridPuissance currentGrid = (GridPuissance) grid;
        return currentGrid.isGameOver();
    }

    /**
     * Renvoie la grille du puissance 4
     * @return
     */
    public GridPuissance getGrid() {
        return (GridPuissance) grid;
    }

    /**
     * Réinitialise la grille
     */
    public void newGame() {
        GridPuissance currentGrid = (GridPuissance) grid;
        currentGrid.reset();
    }
}

