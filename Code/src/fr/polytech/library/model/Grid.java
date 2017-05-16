package fr.polytech.library.model;

import fr.polytech.library.model.piece.Piece;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class Grid {

    protected int width;
    protected int height;
    private int sizeCase;
    protected Color color;
    protected Color[][] caseFulled;
    protected boolean gameOver;
    protected Piece currentPiece;

    public Grid(int width, int height, int sizeCase, Color color) {
        this.width = width;
        this.height = height;
        this.sizeCase = sizeCase;
        this.color = color;
        this.gameOver = false;
        initializeMapCases();
    }

    private void initializeMapCases() {
        caseFulled = new Color[height][width];
        for (int i = 0; i < caseFulled.length; i++) {
            for (int j = 0; j < caseFulled[0].length; j++) {
                caseFulled[i][j] = null;
            }
        }
    }

    public boolean movePiece(int offsetX, int offsetY) {
        boolean movePiece = !currentPiece.move(offsetX, offsetY, caseFulled);
        if (movePiece) {
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

    public boolean isGameOver() {
        return gameOver;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color[][] getCaseFulled() {
        return caseFulled;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }
}
