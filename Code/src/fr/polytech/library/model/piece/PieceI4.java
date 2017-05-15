package fr.polytech.library.model.piece;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class PieceI4 extends Piece {

    public PieceI4(int posX, int posY,  int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        int[][] matrix = new int[][]{
                {1},
                {1},
                {1},
                {1}
        };
        setMatrix(matrix);
    }
}
