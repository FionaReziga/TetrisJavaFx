package fr.polytech.puzzle.model;

import fr.polytech.library.model.Board;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class BoardPuzzle extends Board {
    public BoardPuzzle(int width, int height, int sizeCase, Color color) {
        super(width, height, sizeCase, color);
        grid = new GridPuzzle(width, height, sizeCase, color, 1);
    }

    public synchronized void movePiece(int offsetX, int offsetY) {
        grid.movePiece(offsetX, offsetY);
        setChanged();
        notifyObservers();
    }
}
