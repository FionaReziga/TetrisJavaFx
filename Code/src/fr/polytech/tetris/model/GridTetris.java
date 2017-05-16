package fr.polytech.tetris.model;

import fr.polytech.library.model.Grid;
import fr.polytech.library.model.piece.Piece;
import javafx.scene.paint.Color;

import static fr.polytech.tetris.model.PieceTetris.generateRandomPiece;

/**
 * Created by REZIGA on 15/05/2017.
 */
public class GridTetris extends Grid {
    private Piece nextPiece;

    public GridTetris(int width, int height, int sizeCase, Color color) {
        super(width, height, sizeCase, color);
        initializeMapCases();
        this.currentPiece = PieceTetris.generateRandomPiece(caseFulled);
        this.nextPiece = PieceTetris.generateRandomPiece(caseFulled);

    }

    private void initializeMapCases() {
        caseFulled = new Color[height][width];
        for (int i = 0; i < caseFulled.length; i++) {
            for (int j = 0; j < caseFulled[0].length; j++) {
                caseFulled[i][j] = null;
            }
        }
    }

    public void clearOneRow(int rowIndex) {
        for (int i = 0; i < caseFulled[rowIndex].length; i++) {
            caseFulled[rowIndex][i] = null;
        }
        goDownMatrix(rowIndex);
    }

    private void goDownMatrix(int rowIndex) {
        for (int i = rowIndex; i > 0; i--) {
            caseFulled[i] = caseFulled[i - 1];
        }
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    @Override
    public synchronized boolean movePiece(int offsetX, int offsetY) {
        boolean move = super.movePiece(offsetX, offsetY);
        if(move) {
            checkRowComplete();
            generateNewPieceWithNextPiece();
        }
        return move;
    }

    private void generateNewPieceWithNextPiece() {
        currentPiece = nextPiece;
        nextPiece = generateRandomPiece(caseFulled);
    }

    public void checkRowComplete() {
        boolean checkRow;
        for (int i = caseFulled.length - 1; i >= 0; i--) {
            checkRow = true;
            for (int j = 0; j < caseFulled[0].length; j++) {
                if (caseFulled[i][j] == null) checkRow = false;
            }
            if (checkRow) clearOneRow(i);
        }
    }

    public Color[][] getCaseFulled() {
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
