package fr.polytech.library.model.piece;

import static javafx.scene.paint.Color.GREEN;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui définit une pièce de type S
 *  ____
 * |__  |__
 *    |____|
 *
 *
 */
public class PieceS extends Piece {
    public PieceS(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        this.color = GREEN;
        int[][] matrix = new int[][]{
                {0, 5, 5},
                {5, 5, 0}
        };
        setMatrix(matrix);
    }
}
