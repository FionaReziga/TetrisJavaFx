package fr.polytech.tetris;

import fr.polytech.library.model.Board;
import fr.polytech.tetris.model.GridTetris;
import javafx.scene.paint.Color;

import static fr.polytech.tetris.model.PieceTetris.generateRandomPiece;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class TetrisController {
    Board board;
    GridTetris grid;
    //GridTetris previousGrid;

    public TetrisController(int width, int height, int sizeCase, Color color) {
        //this.board = new Board(width, height, sizeCase, color);
        this.grid = new GridTetris(width, height, sizeCase, color);
        //this.previousGrid = new GridTetris(PREVIOUS_GRID_WIDTH, PREVIOUS_GRID_HEIGHT, sizeCase, previousColor, currentPiece, nextPiece);
        this.board = new Board(grid);
    }

    public Board getBoard() {
        return board;
    }

    public void movePiece(int offsetX, int offsetY) {
        if(!grid.getCurrentPiece().move(offsetX, offsetY, grid.getCaseFulled())) {
            grid.savePiece();
            generateNewPiece();
        }
        grid.clearGridTetris();
    }

    public void rotatePiece() {
        grid.getCurrentPiece().rotate(grid.getCaseFulled());
        grid.clearGridTetris();
    }

    private void generateNewPiece() {
        grid.setCurrentPiece(grid.getNextPiece());
        grid.setNextPiece(generateRandomPiece(grid.getCaseFulled()));
    }
}
