package fr.polytech.morpion.model;

import fr.polytech.library.model.Grid;
import fr.polytech.library.model.piece.Piece;
import fr.polytech.library.model.piece.PieceLittleO;
import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

/**
 * Created by REZIGA on 15/05/2017.
 * Classe qui définit la grille du Puissance 4
 */
public class GridMorpion extends Grid {
    private boolean gameOver;
    private Color currentColor;
    private Color winnerColor;

    /**
     * Constructeur
     *
     * @param width : largeur
     * @param height : hauteur
     * @param sizeCase : taille de la case
     * @param color : couleur
     */
    public GridMorpion(int width, int height, int sizeCase, Color color) {
        super(width, height, sizeCase, color);
        currentColor = RED;
        gameOver = false;
    }

    /**
     * Génération d'une nouvelle pièce
     */
    public void generateNewPiece(int posX, int posY) {
        Piece piece = new PieceLittleO(posX, posY, height, width);
        piece.setColor(currentColor == RED ? BLUE : RED);
        currentColor = piece.getColor();
        currentPiece = piece;
    }

    public Color getCaseFulled(int x, int y) {
        return caseFulled[x][y];
    }

    /**
     * Verifie en cas de gagnant
     *
     * @return : vrai ou faux s'il y a un gagnant
     */
    public boolean checkWinner() {
        boolean result = false;
        if (checkAllCasesFulled()) {
            result = true;
            gameOver = true;
            winnerColor = null;
        }
        if (checkAntiDiagonals() || checkDiagonals() || checkRows() || checkColumns()) {
            result = true;
            gameOver = true;
            winnerColor = currentPiece.getColor();
        }
        return result;
    }

    /**
     * Verifie si la grille est remplie
     * @return vrai ou faux selon que la grille est remplie
     */
    private boolean checkAllCasesFulled() {
        boolean allCasesFulled = true;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (caseFulled[i][j] == null) allCasesFulled = false;
            }
        }
        return allCasesFulled;
    }

    /**
     * Verifie s'il y a un gagnant dans une anti diagonale
     *
     * @return vrai ou faux s'il y a un gagnant ou pas dans la anti diagonale
     */
    private boolean checkAntiDiagonals() {
        if ((caseFulled[0][0] == caseFulled[1][1]) && (caseFulled[1][1] == caseFulled[2][2])) {
            if (caseFulled[0][0] != null) return true;
        }
        return false;
    }

    /**
     * Verifie s'il y a un gagnant dans une diagonale
     *
     * @return vrai ou faux s'il y a un gagnant ou pas dans la diagonale
     */
    private boolean checkDiagonals() {
        if ((caseFulled[2][0] == caseFulled[1][1]) && (caseFulled[1][1] == caseFulled[0][2])) {
            if (caseFulled[2][0] != null) return true;
        }
        return false;
    }


    /**
     * Verifie s'il y a un gagnant dans une ligne
     *
     * @return vrai ou faux s'il y a un gagnant ou pas dans la ligne
     */
    private boolean checkColumns() {
        for (int i = 0; i < width; i++) {
            if ((caseFulled[i][0] == caseFulled[i][1]) && (caseFulled[i][1] == caseFulled[i][2])) {
                if (caseFulled[i][0] != null) return true;
            }
        }
        return false;
    }

    /**
     * Verifie s'il y a un gagnant dans une colonne
     *
     * @return vrai ou faux s'il y a un gagnant ou pas dans la colonne
     */
    private boolean checkRows() {
        for (int j = 0; j < height; j++) {
            if ((caseFulled[0][j] == caseFulled[1][j]) && (caseFulled[1][j] == caseFulled[2][j])) {
                if (caseFulled[0][j] != null) return true;
            }
        }
        return false;
    }

    /**
     * Renvoie si le jeu est terminé
     * @return boolean : gameOver
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Renvoie la couleur du joueur gagant
     * @return : la couleur du gagant
     */
    public Color getWinnerColor() {
        return winnerColor;
    }

    /**
     * Reset le jeu et remet de le joueur actuel comme rouge
     */
    public void reset() {
        initializeMapCases();
        currentColor = RED;
        gameOver = false;
    }
}
