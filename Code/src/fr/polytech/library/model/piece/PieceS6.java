package fr.polytech.library.model.piece;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class PieceS6 extends Piece {

    public PieceS6(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        int[][] matrix = new int[][]{
                {0, 1, 1},
                {1, 1, 0}
        };
        setMatrix(matrix);
    }
}
