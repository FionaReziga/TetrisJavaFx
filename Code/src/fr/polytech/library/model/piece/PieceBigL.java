package fr.polytech.library.model.piece;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class PieceBigL extends Piece{

    public PieceBigL(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        int[][] matrix = new int[][]{
                {1, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        };
        setMatrix(matrix);
    }
}
