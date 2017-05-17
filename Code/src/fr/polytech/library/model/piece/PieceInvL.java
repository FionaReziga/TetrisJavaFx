package fr.polytech.library.model.piece;

import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui définit une pièce de type Inv L
 *   ______
 *  |_____ |
 *       | |
 *       |_|
 */
public class PieceInvL extends Piece{
    public PieceInvL(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        this.color = Color.BLUE;
        int[][] matrix = new int[][]{
                {2, 2, 2},
                {0, 0, 2}
        };
        setMatrix(matrix);
    }
}
