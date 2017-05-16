package fr.polytech.tetris.controller;

import fr.polytech.tetris.ThreadTetris;
import fr.polytech.tetris.model.BoardTetris;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 16/05/2017.
 */
public class TetrisController implements EventHandler<KeyEvent> {
    BoardTetris board;
    boolean pause;

    public TetrisController(int width, int height, int sizeCase, Color color) {
        this.board = new BoardTetris(width, height, sizeCase, color);
        new ThreadTetris(board);
        pause = false;
    }

    public BoardTetris getBoard() {
        return board;
    }

    @Override
    public void handle(KeyEvent event) {
        if (!pause) {
            switch (event.getCode()) {
                case UP:
                    board.rotatePiece();
                    break;
                case DOWN:
                    board.movePiece(1, 0);
                    break;
                case LEFT:
                    board.movePiece(0, -1);
                    break;
                case RIGHT:
                    board.movePiece(0, 1);
                    break;
            }
        }
    }
}
