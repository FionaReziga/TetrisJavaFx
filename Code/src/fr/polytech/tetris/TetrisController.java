package fr.polytech.tetris;

import fr.polytech.library.model.Board;
import fr.polytech.library.model.piece.Piece;
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
        Piece currentPiece = generateRandomPiece();
        Piece nextPiece = generateRandomPiece();
        this.grid = new GridTetris(width, height, sizeCase, color, currentPiece, nextPiece);
        //this.previousGrid = new GridTetris(PREVIOUS_GRID_WIDTH, PREVIOUS_GRID_HEIGHT, sizeCase, previousColor, currentPiece, nextPiece);
        this.board = new Board(grid);
    }

    public Board getBoard() {
        return board;
    }

    public void movePiece(int offsetX, int offsetY) {
        grid.getCurrentPiece().move(offsetX, offsetY);
        grid.drawGridTetris();
    }

    public void rotatePiece() {
        grid.getCurrentPiece().rotate();
        grid.drawGridTetris();
    }

/*    private Piece generateNewPiece() {
        nextPiece = new PieceL3(1, 3, grid);
        return nextPiece;
    }*/
}
