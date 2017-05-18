package fr.polytech.puissance4.model;

        import fr.polytech.library.model.Board;
        import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui définit le plateau du Tetris
 */
public class BoardPuissance extends Board {
    boolean gameOver;

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
        gameOver = false;
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
            gameOver = currentGrid.checkWinner();
            if(!gameOver) currentGrid.generateNewPiece();
        }
        setChanged();
        notifyObservers();
        return move;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}

