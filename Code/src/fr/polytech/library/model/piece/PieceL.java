package fr.polytech.library.model.piece;

import static javafx.scene.paint.Color.BLUE;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class PieceL extends Piece{
    public PieceL(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        this.color = BLUE;
        int[][] matrix = new int[][]{
                {2, 2, 2},
                {2, 0, 0}
        };
        setMatrix(matrix);
    }
}
