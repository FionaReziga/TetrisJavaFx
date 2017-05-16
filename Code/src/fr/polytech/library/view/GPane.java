package fr.polytech.library.view;

import fr.polytech.library.model.Board;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Observer;

import static javafx.scene.paint.Color.BLACK;

/**
 * Created by REZIGA on 16/05/2017.
 */
public class GPane extends GridPane implements Observer{

    public GPane(Board board){
        board.addObserver(this);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        Board board = (Board) o;

        // Affichage de la pièce
        int[][] matrix =  board.getGrid().getCurrentPiece().getMatrix();
        int height = board.getGrid().getHeight();
        int width = board.getGrid().getWidth();
        Color[][] caseFulled = board.getGrid().getCaseFulled();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Node object = getChildren().get(i + (j * height));
                if (object instanceof Rectangle && caseFulled[i][j] == null) {
                    Rectangle current = (Rectangle) getChildren().get(i + (j * height));
                    current.setFill(BLACK);
                }
            }
        }

        int[][] matrixPiece = board.getGrid().getCurrentPiece().getMatrix();
        for (int i = 0; i < matrixPiece.length; i++) {
            for (int j = 0; j < matrixPiece[0].length; j++) {
                Node object = getChildren().get((i + board.getGrid().getCurrentPiece().getPosX()) + ((j + board.getGrid().getCurrentPiece().getPosY()) * height) + 1);
                if (object instanceof Rectangle && matrixPiece[i][j] != 0) {
                    Rectangle current = (Rectangle) object;
                    current.setFill(board.getGrid().getCurrentPiece().getColor());
                }
            }
        }
    }
}
