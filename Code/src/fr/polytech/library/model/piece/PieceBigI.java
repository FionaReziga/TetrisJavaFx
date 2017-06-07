package fr.polytech.library.model.piece;

import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.CYAN;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui définit une pièce de type BIG I
 *  _
 * | |
 * | |
 * | |
 * |_|
 *
 */
public class PieceBigI extends Piece {
    public PieceBigI(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        this.color = CYAN;
        int[][] matrix = new int[][]{
                {1},
                {1},
                {1},
                {1}
        };
        setMatrix(matrix);
    }

    /**
     * Renvoie la couleur de la pièce
     * @return color : couleur de la pièce
     */
    public Color getColor() {
        return color;
    }
}
