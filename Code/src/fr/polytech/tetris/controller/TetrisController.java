package fr.polytech.tetris.controller;

import fr.polytech.tetris.ThreadTetris;
import fr.polytech.tetris.model.BoardTetris;
import fr.polytech.tetris.model.GridTetris;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 16/05/2017.
 * Classe qui définit le controleur du jeu TETRIS
 */
public class TetrisController implements EventHandler<KeyEvent> {
    private BoardTetris board;
    private ThreadTetris threadTetris;


    /**
     * Controleur
     * @param width
     * @param height
     * @param sizeCase
     * @param color
     */
    public TetrisController(int width, int height, int sizeCase, Color color) {
        board = new BoardTetris(width, height, sizeCase, color);
        threadTetris = new ThreadTetris(board);
    }

    /**
     * Renvoie le plateau du Tetris
     * @return
     */
    public BoardTetris getBoard() {
        return board;
    }

    @Override
    public void handle(KeyEvent event) {
        GridTetris currentGrid = (GridTetris) board.getGrid();
        switch (event.getCode()) {
            case UP:
                board.rotatePiece();
                break;
            case DOWN:
                board.movePiece(1, 0);
                board.setScore(1);
                break;
            case LEFT:
                board.movePiece(0, -1);
                break;
            case RIGHT:
                board.movePiece(0, 1);
                break;
        }
    }

    public void newGame() {
        board.newGame();
    }

    public void stopGame() {
        board.setRun(false);
    }
}
