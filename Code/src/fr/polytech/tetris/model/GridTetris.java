package fr.polytech.tetris.model;

import fr.polytech.library.model.Grid;
import fr.polytech.library.model.piece.Piece;
import javafx.scene.paint.Color;

import static fr.polytech.tetris.model.PieceTetris.generateRandomPiece;

/**
 * Created by REZIGA on 15/05/2017.
 * Classe qui définit la grille du Tetris
 */
public class GridTetris extends Grid {
    private Piece nextPiece;
    private boolean gameOver;

    /**
     * Constructeur
     * @param width
     * @param height
     * @param sizeCase
     * @param color
     */
    public GridTetris(int width, int height, int sizeCase, Color color) {
        super(width, height, sizeCase, color);
        this.currentPiece = generateRandomPiece(caseFulled);
        this.nextPiece = generateRandomPiece(caseFulled);
        gameOver = false;
    }

    /**
     * Vide une ligne si elle est pleine
     * @param rowIndex
     */
    public void clearOneRow(int rowIndex) {
        for (int i = 0; i < caseFulled[rowIndex].length; i++) {
            caseFulled[rowIndex][i] = null;
        }
        goDownMatrix(rowIndex);
    }

    /**
     * Fait descendre la matrice des pièces
     * @param rowIndex
     */
    private void goDownMatrix(int rowIndex) {
        for (int i = rowIndex; i > 0; i--) {
            caseFulled[i] = caseFulled[i - 1];
        }
    }

    /**
     * Génération d'une nouvelle pièce
     */
    protected void generateNewPieceWithNextPiece() {
        Piece piece = generateRandomPiece(caseFulled);
        if(checkFuturPiece(piece)) {
            currentPiece = nextPiece;
            nextPiece = generateRandomPiece(caseFulled);
        } else {
            gameOver = true;
        }
    }

    /**
     * Vérifie si la ligne est complète
     */
    public int checkRowComplete() {
        boolean checkRow;
        int numberRowComplete = 0;
        for (int i = caseFulled.length - 1; i >= 0; i--) {
            checkRow = true;
            for (int j = 0; j < caseFulled[0].length; j++) {
                if (caseFulled[i][j] == null) checkRow = false;
            }
            if (checkRow) {
                clearOneRow(i);
                i++;
                numberRowComplete += 1;
            }
        }
        return numberRowComplete;
    }

    /**
     * Verifie la future pièce
     * @param piece
     * @return
     */
    public boolean checkFuturPiece(Piece piece){
        for (int i = 0; i < piece.getMatrix().length; i++) {
            for (int j = 0; j < piece.getMatrix()[0].length; j++) {
                if(caseFulled[i + piece.getPosX()][j + piece.getPosY()] != null) return false;
            }
        }
        return true;
    }

    public void reset() {
        initializeMapCases();
        currentPiece = generateRandomPiece(caseFulled);
        nextPiece = generateRandomPiece(caseFulled);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Color[][] getCaseFulled() {
        return caseFulled;
    }

    public Piece getNextPiece() {
        return nextPiece;
    }

}
