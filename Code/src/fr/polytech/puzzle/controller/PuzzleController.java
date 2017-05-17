package fr.polytech.puzzle.controller;

import fr.polytech.puzzle.model.BoardPuzzle;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 16/05/2017.
 */
public class PuzzleController implements EventHandler<KeyEvent> {
    BoardPuzzle board;

    public PuzzleController(int width, int height, int sizeCase, Color color) {
        this.board = new BoardPuzzle(width, height, sizeCase, color);
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                board.movePiece(-1, 0);
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

    public BoardPuzzle getBoard() {
        return board;
    }
}
