package fr.polytech.library.model;

import fr.polytech.library.model.piece.Piece;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.BLACK;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class Grid {

    protected int width;
    protected int height;
    protected int sizeCase;
    protected GridPane gridPane;
    protected Color color;

    public Grid(int width, int height, int sizeCase, Color color) {
        this.width = width;
        this.height = height;
        this.sizeCase = sizeCase;
        this.color = color;
        generateGridPane();
    }

    private void generateGridPane() {
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Rectangle rectangle = new Rectangle(sizeCase, sizeCase);
                rectangle.setFill(color);
                gridPane.add(rectangle, i, j);
            }
        }

        // Add constraints
        for (int i = 0; i < width; i++) {
            ColumnConstraints column = new ColumnConstraints(sizeCase);
            gridPane.getColumnConstraints().add(column);
        }
        for (int j = 0; j < height; j++) {
            RowConstraints row = new RowConstraints(sizeCase);
            gridPane.getRowConstraints().add(row);
        }
    }

    public void showPiece(Piece piece) {
        // Affichage de la pièce
        int[][] matrix = piece.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Node object = gridPane.getChildren().get((i + piece.getPosX()) + ((j + piece.getPosY()) * height));
                if (object instanceof Rectangle && matrix[i][j] != 0) {
                    Rectangle current = (Rectangle) object;
                    current.setFill(piece.getColor());
                }
            }
        }
    }

    public void clearGrid() {
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j < width; j++) {
                Node object = gridPane.getChildren().get(i + (j * height));
                if (object instanceof Rectangle) {
                    Rectangle current = (Rectangle) gridPane.getChildren().get(i + (j * height));
                    current.setFill(BLACK);
                }
            }
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
