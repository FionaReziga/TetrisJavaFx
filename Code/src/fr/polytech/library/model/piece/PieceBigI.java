package fr.polytech.library.model.piece;

import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.BEIGE;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class PieceBigI extends Piece {
    public PieceBigI(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        this.color = BEIGE;
        int[][] matrix = new int[][]{
                {1},
                {1},
                {1},
                {1}
        };
        setMatrix(matrix);
    }

    public Color getColor() {
        return color;
    }
}
