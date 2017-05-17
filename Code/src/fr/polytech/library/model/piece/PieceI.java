package fr.polytech.library.model.piece;

import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.YELLOW;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui définit une pièce de type I
 *  _
 * | |
 * | |
 * |_|
 *
 */
public class PieceI extends Piece {
    public PieceI(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        this.color = YELLOW;
        int[][] matrix = new int[][]{
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
