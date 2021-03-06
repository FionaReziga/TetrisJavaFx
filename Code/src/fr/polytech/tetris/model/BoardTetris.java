package fr.polytech.tetris.model;

import fr.polytech.library.model.Board;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui définit le plateau du Tetris
 */
public class BoardTetris extends Board {
    private int score;
    private boolean run;

    /**
     * Constructeur du plateau du Tetris
     *
     * @param width : largeur
     * @param height : hauteur
     * @param sizeCase : taille de la case
     * @param color : couleur
     */
    public BoardTetris(int width, int height, int sizeCase, Color color) {
        super(width, height, sizeCase, color);
        grid = new GridTetris(width, height, sizeCase, color);
        score = 0;
        run = true;
    }

    /**
     * Synchronisation des mouvements de la pièce
     *
     * @param offsetX : future position X de la piece
     * @param offsetY : future position Y de la piece
     */
    public synchronized void movePiece(int offsetX, int offsetY) {
        boolean move = grid.movePiece(offsetX, offsetY);
        GridTetris currentGrid = (GridTetris) grid;
        if (move) {
            setScore(10);
            int rowsComplete = currentGrid.checkRowComplete();
            if (rowsComplete > 0) setScore(rowsComplete * 100);
            currentGrid.generateNewPieceWithNextPiece();
        }
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

    /**
     * Renvoie le score du joueur
     *
     * @return score : le score
     */
    public int getScore() {
        return score;
    }

    /**
     * Définit le score du joueur
     *
     * @param score : le score incrémenté
     */
    public void setScore(int score) {
        this.score += score;
    }

    /**
     * Réinitialise la grille et le score
     */
    public void newGame() {
        score = 0;
        GridTetris currentGrid = (GridTetris) grid;
        currentGrid.reset();
        ((GridTetris) grid).setGameOver(false);
    }

    /**
     * Renvoie le statut run
     * @return run : vrai ou faux en fonction de si le jeu tourne
     */
    public boolean isRun() {
        return run;
    }

    /**
     * Définit le statut run
     * @param run : le statut du jeu
     */
    public void setRun(boolean run) {
        this.run = run;
    }
}
