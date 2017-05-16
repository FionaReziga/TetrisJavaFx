package fr.polytech.tetris.model;

import fr.polytech.library.model.Board;
import fr.polytech.library.model.Grid;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class BoardTetris extends Board {
    boolean stop;
    Grid previousGrid;
    private int score;

    public BoardTetris(int width, int height, int sizeCase, Color color) {
        super(width, height, sizeCase, color);
        grid = new GridTetris(width, height, sizeCase, color);
        stop = false;
        this.previousGrid = new GridTetris(2, 2, sizeCase, Color.BROWN);
        score = 0;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }
}
