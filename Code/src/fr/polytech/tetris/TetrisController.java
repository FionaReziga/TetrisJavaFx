package fr.polytech.tetris;

import fr.polytech.library.model.Board;
import fr.polytech.library.model.piece.Piece;
import fr.polytech.library.model.piece.PieceL3;
import fr.polytech.tetris.model.GridTetris;
import javafx.scene.paint.Color;

import static fr.polytech.tetris.TetrisView.GRID_HEIGHT;
import static fr.polytech.tetris.TetrisView.GRID_WIDTH;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class TetrisController {
    Board board;
    GridTetris grid;


    public TetrisController(int width, int height, int sizeCase, Color color) {
        //this.board = new Board(width, height, sizeCase, color);
        Piece currentPiece = new PieceL3(1, 3, GRID_HEIGHT, GRID_WIDTH);
        Piece nextPiece = new PieceL3(1, 3, GRID_HEIGHT, GRID_WIDTH);
        this.grid = new GridTetris(width, height, sizeCase, color, currentPiece, nextPiece);
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
