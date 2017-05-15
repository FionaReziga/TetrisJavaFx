package fr.polytech.tetris.model;

import fr.polytech.library.model.Grid;
import fr.polytech.library.model.piece.Piece;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.BLACK;

/**
 * Created by REZIGA on 15/05/2017.
 */
public class GridTetris extends Grid {
    private int[][] caseFulled;
    private Piece currentPiece;
    private Piece nextPiece;

    public GridTetris(int width, int height, int sizeCase, Color color) {
        super(width, height, sizeCase, color);
        initializeMapCases();
        this.currentPiece = PieceTetris.generateRandomPiece(caseFulled);
        this.nextPiece = PieceTetris.generateRandomPiece(caseFulled);
    }

    private void initializeMapCases() {
        caseFulled = new int[height][width];
        for (int i = 0; i < caseFulled.length; i++) {
            for (int j = 0; j < caseFulled[0].length; j++) {
                caseFulled[i][j] = 0;
            }
        }
    }

    public void showPieceTetris() {
        super.showPiece(currentPiece);
    }

    public void clearGridTetris() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Node object = gridPane.getChildren().get(i + (j * height) + 1);
                if (object instanceof Rectangle && caseFulled[i][j] != 1) {
                    Rectangle current = (Rectangle) gridPane.getChildren().get(i + (j * height) + 1);
                    current.setFill(BLACK);
                }
            }
        }
        showPieceTetris();
    }

    private void clearOneRow(int rowIndex){
        for (int i = 0; i < caseFulled[rowIndex].length; i++) {
            caseFulled[rowIndex][i] = 0;
        }
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void savePiece() {
        int[][] matrix = currentPiece.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Node object = gridPane.getChildren().get((i + currentPiece.getPosX()) + ((j + currentPiece.getPosY()) * height) + 1);
                if (object instanceof Rectangle && matrix[i][j] != 0) {
                    caseFulled[currentPiece.getPosX() + i][currentPiece.getPosY() + j] = 1;
                }
            }
        }
    }

    public void checkRowComplete() {
        boolean checkRow = true;
        for (int i = 0; i < caseFulled.length; i++) {
            for (int j = 0; j < caseFulled[0].length; j++) {
                if(caseFulled[i][j] != 1) checkRow = false;
            }
            if(checkRow) clearOneRow(i);
        }
    }

    public int[][] getCaseFulled() {
        return caseFulled;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    public Piece getNextPiece() {
        return nextPiece;
    }

    public void setNextPiece(Piece nextPiece) {
        this.nextPiece = nextPiece;
    }
}
