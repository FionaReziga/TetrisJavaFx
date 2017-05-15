package fr.polytech.tetris.model;

import fr.polytech.library.model.Grid;
import fr.polytech.library.model.piece.Piece;
import fr.polytech.library.model.piece.PieceL3;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;
import static javafx.scene.paint.Color.BLACK;

/**
 * Created by REZIGA on 15/05/2017.
 */
public class GridTetris extends Grid {
    private List<IdentifierBoolean> caseFulled;
    private Piece currentPiece;
    private Piece nextPiece;

    public GridTetris(int width, int height, int sizeCase, Color color, Piece currentPiece, Piece nextPiece) {
        super(width, height, sizeCase, color);
        this.currentPiece = currentPiece;
        this.nextPiece = nextPiece;
        initializeMapCases();
    }

    private void initializeMapCases() {
        caseFulled = new ArrayList<>();
        range(0, width * height).forEach(value -> caseFulled.add(new IdentifierBoolean(0l, false)));
    }

    public void showPieceTetris() {
        // Affichage de la pièce
        int[][] matrix = currentPiece.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Node object = gridPane.getChildren().get((i + currentPiece.getPosX()) + ((j + currentPiece.getPosY()) * height));
                if (object instanceof Rectangle && matrix[i][j] != 0) {
                    Rectangle current = (Rectangle) object;
                    current.setFill(currentPiece.getColor());
                    caseFulled.set((i + currentPiece.getPosX()) + ((j + currentPiece.getPosY()) * height), new IdentifierBoolean(currentPiece.getId(), true));
                }
            }
        }
    }

    public void drawGridTetris() {
/*        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Node object = gridPane.getChildren().get(i * height + j);
                if (object instanceof Rectangle) {
                    IdentifierBoolean currentCase = caseFulled.get(i * width + j - 1);
                    if (currentCase.isCaseEmpty() || currentCase.getIdentifierPiece() == currentPiece.getId()) {
                            Rectangle current = (Rectangle) gridPane.getChildren().get(i * height + j);
                            current.setFill(BLACK);
                            caseFulled.set((i * height + j - 1), new IdentifierBoolean(0l, false));
                    }
                }
            }
        }*/

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Node object = gridPane.getChildren().get(i * height + j );
                if (object instanceof Rectangle) {
                    IdentifierBoolean currentCase = caseFulled.get(i * width + j - 1);
                    if (currentCase.isCaseEmpty() || currentCase.getIdentifierPiece() == currentPiece.getId()) {
                        Rectangle current = (Rectangle) gridPane.getChildren().get(i + (j * width));
                        current.setFill(BLACK);
                        caseFulled.set((i * height + j - 1), new IdentifierBoolean(0l, false));
                    }
                }
            }
        }

        currentPiece = nextPiece;
        nextPiece = new PieceL3(1, 3, height, width);
        showPieceTetris();
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }
}
