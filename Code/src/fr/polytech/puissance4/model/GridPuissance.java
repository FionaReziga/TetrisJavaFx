package fr.polytech.puissance4.model;

import fr.polytech.library.model.Grid;
import fr.polytech.library.model.piece.Piece;
import fr.polytech.library.model.piece.PieceLittleO;
import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.ORANGE;
import static javafx.scene.paint.Color.RED;

/**
 * Created by REZIGA on 15/05/2017.
 * Classe qui définit la grille du Puissance 4
 */
public class GridPuissance extends Grid {
    private boolean gameOver;
    private Color currentColor;

    /**
     * Constructeur
     *
     * @param width
     * @param height
     * @param sizeCase
     * @param color
     */
    public GridPuissance(int width, int height, int sizeCase, Color color) {
        super(width, height, sizeCase, color);
        this.currentPiece = new PieceLittleO(0, 3, height, width);
        currentColor = RED;
        gameOver = false;
    }

    /**
     * Génération d'une nouvelle pièce
     */
    protected void generateNewPiece() {
        Piece piece = new PieceLittleO(0, 3, height, width);
        piece.setColor(currentColor == RED ? ORANGE : RED);
        currentColor = piece.getColor();
        currentPiece = piece;
    }

    public Color[][] getCaseFulled() {
        return caseFulled;
    }

    public boolean checkWinner() {
        boolean result = false;
        if(currentPiece.getPosX() == 0) result = true;
        for (int i = 0; i < caseFulled.length; i++) {
            if (checkRows(i)) result = true;
        }
        for (int j = 0; j < caseFulled[0].length; j++) {
            if (checkColumns(j)) result = true;

        }
        if(checkAntiDiagonals()) result = true;
        if(checkDiagonals()) result = true;
        return result;
    }

    private boolean checkAntiDiagonals() {
        for (int i = 0; i < height - 3; i++) {
            for (int j = 0; j < width - 3; j++) {
                if (caseFulled[i][j] != null) {
                    if (caseFulled[i][j] == caseFulled[i + 1][j + 1] && caseFulled[i + 1][j + 1] == caseFulled[i + 2][j + 2] && caseFulled[i + 2][j + 2] == caseFulled[i + 3][j + 3]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        for (int i = 0; i < height - 3; i++) {
            for (int j = 3; j < width; j++) {
                if (caseFulled[i][j] != null) {
                    if (caseFulled[i][j] == caseFulled[i + 1][j - 1] && caseFulled[i + 1][j - 1] == caseFulled[i + 2][j - 2] && caseFulled[i + 2][j - 2] == caseFulled[i + 3][j - 3]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkRows(int i) {
        int nbColor = 1;
        Color color = caseFulled[i][0];
        for (int j = 1; j < width; j++) {
            if (caseFulled[i][j] != null) {
                if (caseFulled[i][j] == color) nbColor++;
                else {
                    nbColor = 1;
                    color = caseFulled[i][j];
                }
                if (nbColor == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumns(int j) {
        int nbColor = 1;
        Color color = caseFulled[0][j];
        for (int i = 0; i < height; i++) {
            if (caseFulled[i][j] != null) {
                if (caseFulled[i][j] == color) nbColor++;
                else {
                    nbColor = 1;
                    color = caseFulled[i][j];
                }
                if (nbColor == 4) {
                    return true;
                }
            }
        }
        return false;
    }
}