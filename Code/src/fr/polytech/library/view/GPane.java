package fr.polytech.library.view;

import fr.polytech.library.model.Board;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Observer;

import static javafx.scene.paint.Color.BLUE;

/**
 * Created by REZIGA on 16/05/2017.
 * Classe qui définit une Grid Pane
 */
public class GPane extends GridPane implements Observer {

    public GPane(Board board) {
        board.addObserver(this);
    }

    /**
     * Mise à jour de la Grid Pane
     * @param o
     * @param arg
     */
    @Override
    public void update(java.util.Observable o, Object arg) {
        Board board = (Board) o;

        int height = board.getGrid().getHeight();
        int width = board.getGrid().getWidth();
        Color[][] caseFulled = board.getGrid().getCaseFulled();

        // On nettoie la grille
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if((i + (j * height) + 1) >= getChildren().size()) break;
                Node object = getChildren().get(i + (j * height) + 1);
                if (object instanceof Rectangle) {
                    Rectangle current = (Rectangle) getChildren().get(i + (j * height) + 1);
                    current.setFill(board.getGrid().getColor());
                } else if (object instanceof Circle) {
                    Circle current = (Circle) getChildren().get(i + (j * height));
                    current.setFill(i == 0 ? BLUE : board.getGrid().getColor());
                }
            }
        }

        // On parcourt la matrice concernant les pieces déjà dans la grille
        for (int i = 0; i < caseFulled.length; i++) {
            for (int j = 0; j < caseFulled[0].length; j++) {
                Node object;
                if((i + (j * height) + 1) >= getChildren().size()) object = getChildren().get(i + (j * height));
                else object = getChildren().get(i + (j * height) + 1);
                if (object instanceof Rectangle) {
                    Rectangle current = (Rectangle) object;
                    if (caseFulled[i][j] != null) current.setFill(caseFulled[i][j]);
                } else if (object instanceof Circle) {
                    Circle current = (Circle) getChildren().get(i + (j * height));
                    if (caseFulled[i][j] != null) current.setFill(caseFulled[i][j]);
                }
            }
        }

        // Affichage de la pièce
        int[][] matrixPiece = board.getGrid().getCurrentPiece().getMatrix();
        for (int i = 0; i < matrixPiece.length; i++) {
            for (int j = 0; j < matrixPiece[0].length; j++) {
                if(((i + board.getGrid().getCurrentPiece().getPosX()) + ((j + board.getGrid().getCurrentPiece().getPosY()) * height) + 1) >= getChildren().size()) break;
                Node object = getChildren().get((i + board.getGrid().getCurrentPiece().getPosX()) + ((j + board.getGrid().getCurrentPiece().getPosY()) * height) + 1);
                if (object instanceof Rectangle && matrixPiece[i][j] != 0) {
                    Rectangle current = (Rectangle) object;
                    current.setFill(board.getGrid().getCurrentPiece().getColor());
                }  else if (object instanceof Circle && matrixPiece[i][j] != 0) {
                    Circle current = (Circle) getChildren().get((i + board.getGrid().getCurrentPiece().getPosX()) + ((j + board.getGrid().getCurrentPiece().getPosY()) * height));
                    current.setFill(board.getGrid().getCurrentPiece().getColor());
                }
            }
        }
    }
}
