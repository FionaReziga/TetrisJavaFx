package fr.polytech.tetris.model;

import fr.polytech.library.model.Board;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class BoardTetris extends Board {
    boolean stop;

    public BoardTetris(int width, int height, int sizeCase, Color color) {
        super(width, height, sizeCase, color);
        grid = new GridTetris(width, height, sizeCase, color);
        stop = false;
        //this.previousGrid = new GridPuzzle(2,2, sizeCase, Color.BROWN);
    }

    public synchronized void movePiece(int offsetX, int offsetY) {
        grid.movePiece(offsetX, offsetY);
        setChanged();
        notifyObservers();
    }

    public void rotatePiece() {
        grid.rotatePiece();
        setChanged();
        notifyObservers();
    }


    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isStop() {

        return stop;
    }
}
