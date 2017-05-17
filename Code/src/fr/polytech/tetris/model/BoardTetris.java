package fr.polytech.tetris.model;

import fr.polytech.library.model.Board;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui définit le plateau du Tetris
 */
public class BoardTetris extends Board {
    private boolean stop;
    private int score;
    private int speed;

    /**
     * Constructeur du plateau du Tetris
     * @param width
     * @param height
     * @param sizeCase
     * @param color
     */
    public BoardTetris(int width, int height, int sizeCase, Color color) {
        super(width, height, sizeCase, color);
        grid = new GridTetris(width, height, sizeCase, color);
        stop = false;
        score = 0;
        speed = 1000;
    }

    /**
     * Synchronisation des mouvements de la pièce
     * @param offsetX
     * @param offsetY
     */
    public synchronized void movePiece(int offsetX, int offsetY) {
        grid.movePiece(offsetX, offsetY);
        setChanged();
        notifyObservers();
    }

    /**
     * Rotation de la pièce
     */
    public void rotatePiece() {
        grid.rotatePiece();
        setChanged();
        notifyObservers();
    }

    public int getSpeed() {
        return (int) (score);
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isStop() {
        return stop;
    }

    /**
     * Renvoie le score du joueur
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * Définit le score du joueur
     * @param score
     */
    public void setScore(int score) {
        this.score += score;
    }

    public void newGame() {
        score = 0;
    }
}
