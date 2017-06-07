package fr.polytech.library.model;

import fr.polytech.library.model.piece.Piece;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui définit la grille du plateau de jeu
 */
public class Grid {

    protected int width;
    protected int height;
    private int sizeCase;
    protected Color color;
    protected Color[][] caseFulled;
    protected Piece currentPiece;

    /**
     * Constructeur de la Grille
     * @param width : largeur de la grille
     * @param height : hauteur de la grille
     * @param sizeCase : taille de la case
     * @param color : couleur de la grille
     */
    public Grid(int width, int height, int sizeCase, Color color) {
        this.width = width;
        this.height = height;
        this.sizeCase = sizeCase;
        this.color = color;
        initializeMapCases();
    }

    /**
     * Initialisation de la matrice de couleur contenant la couleur des pièces sur la grille
     */
    protected void initializeMapCases() {
        caseFulled = new Color[height][width];
        for (int i = 0; i < caseFulled.length; i++) {
            for (int j = 0; j < caseFulled[0].length; j++) {
                caseFulled[i][j] = null;
            }
        }
    }

    /**
     * Fonction qui retourne vrai si la position est possible
     * @param offsetX : future position X
     * @param offsetY : future position Y
     * @return vrai ou faux en fonction de la possibilité du déplacement
     */
    public boolean movePiece(int offsetX, int offsetY) {
        boolean movePiece = !currentPiece.move(offsetX, offsetY, caseFulled);
        if (movePiece) {
            savePiece();
        }
        return movePiece;
    }

    /**
     * Procédure qui permet de faire tourner une pièce
     */
    public void rotatePiece() {
        currentPiece.rotate(caseFulled);
    }

    /**
     * Procédure qui sauvegarde la position de la pièce dans la matrice de couleur
     */
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

    /**
     * Fonction qui retourne la largeur de la grille
     * @return width : largeur de la grille
     */
    public int getWidth() {
        return width;
    }

    /**
     * Fonction qui retourne la hauteur de la grille
     * @return height : hauteur de la grille
     */
    public int getHeight() {
        return height;
    }

    /**
     * Fonction qui retourne la matrice qui correspond aux pièces déja en place dans la grille
     * @return caseFulled : matrice qui correspond aux pièces déja en place dans la grille
     */
    public Color[][] getCaseFulled() {
        return caseFulled;
    }


    /**
     * Fonction qui retourne la pièce courante
     * @return currentPiece : une instance de piece
     */
    public Piece getCurrentPiece() {
        return currentPiece;
    }

    /**
     * Fonction qui renvoie la couleur de la grille
     * @return color : couleur de la grille
     */
    public Color getColor() {
        return color;
    }
}
