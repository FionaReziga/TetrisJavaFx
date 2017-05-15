package fr.polytech.library.model.piece;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class PieceL3 extends Piece{

    public PieceL3(int posX, int posY,  int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        int[][] matrix = new int[][]{
                {3, 3, 3},
                {3, 0, 0}
        };
        setMatrix(matrix);
    }
}
