package fr.polytech.library.model.piece;

/**
 * Created by REZIGA on 15/05/2017.
 */
public class PieceBigT extends Piece {

    public PieceBigT(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        int[][] matrix = new int[][]{
                {0, 1, 0},
                {0, 1, 0},
                {1, 1, 1}
        };
        setMatrix(matrix);
    }
}
