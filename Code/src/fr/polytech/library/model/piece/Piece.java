package fr.polytech.library.model.piece;

import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by REZIGA on 14/05/2017.
 * Classe qui permet de définir une pièce
 */
public abstract class Piece {
    private static long idCounter = 1;

    //Propriétés
    private Long id;
    private int posX;
    private int posY;
    private int[][] matrix;
    private int gridHeight;
    private int gridWidth;

    protected Color color;

    /**
     * Constructeur de la pièce
     * @param posX
     * @param posY
     * @param gridHeight
     * @param gridWidth
     */
    public Piece(int posX, int posY, int gridHeight, int gridWidth) {
        this.id = idCounter++;
        this.posX = posX;
        this.posY = posY;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
    }

    /**
     * Retourne la couleur de la pièce
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Retourne un tableu qui correspond à la matrice de la pièce
     * @return
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Définit la matrice de la pièce
     * @param matrix
     */
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Retourne l'id de la pièce
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Retourne la position X de la pièce qui correspond à la ligne
     * @return X
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Retourne la position Y de la pièce qui correspond à la colonne
     * @return Y
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Fonction qui permet de faire bouger la pièce et qui verifie les colisions
     * @param offsetX
     * @param offsetY
     * @param caseFulled
     * @return boolean
     */
    public boolean move(int offsetX, int offsetY, Color[][] caseFulled) {
        int futurPosX = this.posX + offsetX;
        int futurPosY = this.posY + offsetY;

        if (!checkCollisions(caseFulled, null, futurPosX, null)) this.posX = futurPosX;
        else if (offsetX == 1 && offsetY == 0) return false;
        if (!checkCollisions(caseFulled, null, null, futurPosY)) this.posY = futurPosY;

        return true;
    }

    /**
     * Fonction qui permet de faire tourner la pièce sur elle même et qui vérifie les colisions
     * @param caseFulled
     */
    public void rotate(Color[][] caseFulled) {
        int columns = this.matrix[0].length;
        int rows = this.matrix.length;

        int[][] newMatrix = new int[columns][rows];

        for (int j = 0; j < columns; j++) {
            for (int i = 0, k = rows - 1; i < rows; i++, k--) {
                newMatrix[j][i] = matrix[k][j];
            }
        }

        if (!checkCollisions(caseFulled, newMatrix, null, null)) this.matrix = newMatrix;
    }

    /**
     * Fonction qui génère aléatoirement une pièce parmis une liste de pièces
     * @param caseFulled
     * @param pieceList
     * @return
     */
    public static Piece generateRandomPiece(Color[][] caseFulled, List<Piece> pieceList) {
        Piece piece = pieceList.get(new Random().nextInt(pieceList.size()));
        int rotate = (int) (Math.random() * 4);
        IntStream.range(0, rotate).forEach(value -> piece.rotate(caseFulled));
        return piece;
    }

    /**
     * Fonction qui detecte les colisions avec la dernière ligne de la grille
     * @param caseFulled
     * @param matrix
     * @param posX
     * @param posY
     * @return boolean
     */
    private boolean checkCollisions(Color[][] caseFulled, int[][] matrix, Integer posX, Integer posY) {
        if (matrix != null) {
            // Cas d'une rotation
            int columns = matrix[0].length;
            int rows = matrix.length;


            int futurPosX = this.posX + columns;
            int futurPosY = this.posY + rows;

            if (!(futurPosX < 0 || futurPosX > gridHeight) && !(futurPosY < 0 || futurPosY > gridWidth)) {
                if (!checkPieceCollisions(caseFulled, matrix, posX == null ? this.posX : posX, posY == null ? this.posY : posY)) return false;
            }
        } else if (posX != null) {
            // Cas d'une translation
            if (posX >= 0 && posX <= (gridHeight - this.matrix.length)) {
                if (!checkPieceCollisions(caseFulled, this.matrix, posX, posY == null ? this.posY : posY)) return false;
            }
        } else if (posY != null) {
            // Cas d'une translation
            if (posY >= 0 && posY <= (gridWidth - this.matrix[0].length)) {
                if (!checkPieceCollisions(caseFulled, this.matrix, this.posX, posY)) return false;
            }
        }

        return true;
    }

    /**
     * Fonction qui detecte les colisions avec d'autres pièces
     * @param caseFulled
     * @param matrix
     * @param posX
     * @param posY
     * @return boolean
     */
    private boolean checkPieceCollisions(Color[][] caseFulled, int[][] matrix, Integer posX, Integer posY) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(((i + posX) >= caseFulled.length) || ((j + posY) >= caseFulled[0].length)) return true;
                if(matrix[i][j] != 0 && caseFulled[i + posX][j + posY] != null) return true;
            }
        }
        return false;
    }

    /**
     * Fonction qui définit la couleur de la pièce
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    public void getSetMatrixWithRotation(){
        int columns = this.matrix[0].length;
        int rows = this.matrix.length;

        int[][] newMatrix = new int[columns][rows];

        for (int j = 0; j < columns; j++) {
            for (int i = 0, k = rows - 1; i < rows; i++, k--) {
                newMatrix[j][i] = matrix[k][j] ;
            }
        }
        matrix = newMatrix;
    }
}
