package fr.polytech.library.model.piece;

import static javafx.scene.paint.Color.YELLOW;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class PieceO extends Piece {
    public PieceO(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        this.color = YELLOW;
        int[][] matrix = new int[][]{
                {4, 4},
                {4, 4}
        };
        setMatrix(matrix);
    }
}
