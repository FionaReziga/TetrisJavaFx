package fr.polytech.library.model.piece;

import static javafx.scene.paint.Color.RED;

/**
 * Created by REZIGA on 16/05/2017.
 * Classe qui définit une pièce de type petit O
 *  _
 * |_|
 */
public class PieceLittleO extends Piece {
    public PieceLittleO(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
        this.color = RED;
        int[][] matrix = new int[][]{
                {4}
        };
        setMatrix(matrix);
    }
}