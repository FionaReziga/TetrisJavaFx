package fr.polytech.library.model;

import fr.polytech.library.model.piece.Piece;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class Grid {

    protected int width;
    protected int height;
    protected int sizeCase;
    protected Color color;
    protected Color[][] caseFulled;

    protected Piece currentPiece;

    public Color[][] getCaseFulled() {
        return caseFulled;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public Grid(int width, int height, int sizeCase, Color color) {
        this.width = width;
        this.height = height;
        this.sizeCase = sizeCase;
        this.color = color;
    }

    public boolean movePiece(int offsetX, int offsetY) {
        boolean movePiece = !currentPiece.move(offsetX, offsetY, caseFulled);
        if(movePiece) {
            savePiece();
        }
        return movePiece;
    }

    public void rotatePiece() {
        currentPiece.rotate(caseFulled);
    }

    public void savePiece() {
        int[][] matrix = currentPiece.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    caseFulled[currentPiece.getPosX() + i][currentPiece.getPosY() + j] = currentPiece.getColor();
                }
            }
        }
    }

 /*   private void generateGridPane() {
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Rectangle rectangle = new Rectangle(sizeCase, sizeCase);
                rectangle.setFill(color);
                rectangle.setStroke(BLACK);
                rectangle.setStrokeWidth(1);
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

    public void showPiece() {
        // Affichage de la pièce
        int[][] matrix = currentPiece.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Node object = gridPane.getChildren().get((i + currentPiece.getPosX()) + ((j + currentPiece.getPosY()) * height) + 1);
                if (object instanceof Rectangle && matrix[i][j] != 0) {
                    Rectangle current = (Rectangle) object;
                    current.setFill(currentPiece.getColor());
                }
            }
        }
    }

    public void clearGrid() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Node object = gridPane.getChildren().get(i + (j * height) + 1);
                if (object instanceof Rectangle && caseFulled[i][j] == null) {
                    Rectangle current = (Rectangle) gridPane.getChildren().get(i + (j * height) + 1);
                    current.setFill(BLACK);
                }
            }
        }
        showPiece();
    }*/

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
