package fr.polytech.library.model.piece;

import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.GREY;

/**
 * Created by REZIGA on 16/05/2017.
 * Classe qui définit une pièce de type petit I
 *  _
 * | |
 * |_|
 */
public class PieceLittleI extends Piece {
    public PieceLittleI(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        this.color = GREY;
        int[][] matrix = new int[][]{
                {1},
                {1}
        };
        setMatrix(matrix);
    }

    public Color getColor() {
        return color;
    }
}
