package fr.polytech.library.model.piece;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class PieceO4 extends Piece {

    public PieceO4(int posX, int posY,  int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        int[][] matrix = new int[][]{
                {4, 4},
                {4, 4}
        };
        setMatrix(matrix);
    }
}
