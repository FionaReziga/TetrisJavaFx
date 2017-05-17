package fr.polytech.library.model.piece;

import static javafx.scene.paint.Color.DARKORCHID;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui définit une pièce de type T
 *
 *  _____
 * |_   _|
 *   | |
 *   |_|
 *
 */
public class PieceT extends Piece {
    public PieceT(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        this.color = DARKORCHID;
        int[][] matrix = new int[][]{
                {0, 6, 0},
                {6, 6, 6}
        };
        setMatrix(matrix);
    }
}
