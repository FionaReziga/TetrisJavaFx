package fr.polytech.library.model.piece;

import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static javafx.scene.paint.Color.*;

/**
 * Created by REZIGA on 14/05/2017.
 */
public abstract class Piece {
    private static long idCounter = 1;

    private Long id;
    private int posX;
    private int posY;
    private Color color;
    private int[][] matrix;
    private int gridHeight;
    private int gridWidth;

    public Piece(int posX, int posY, int gridHeight, int gridWidth) {
        this.id = idCounter++;
        this.posX = posX;
        this.posY = posY;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        this.color = generateRandomColor();
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Long getId() {
        return id;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Color getColor() {
        return color;
    }

    public boolean move(int offsetX, int offsetY, int[][] caseFulled) {
        int futurPosX = this.posX + offsetX;
        int futurPosY = this.posY + offsetY;

        if (!checkCollisions(caseFulled, null, futurPosX, null)) this.posX = futurPosX;
        else if (offsetX == 1 && offsetY == 0) return false;
        if (!checkCollisions(caseFulled, null, null, futurPosY)) this.posY = futurPosY;

        return true;
    }

    public void rotate(int[][] caseFulled) {
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

    private Color generateRandomColor() {
        List<Color> colorList = asList(RED, ORANGE, YELLOW, GREEN, CYAN, BLUE, DARKBLUE, PINK, PURPLE, GREY, BROWN);
        return colorList.get(new Random().nextInt(colorList.size()));
    }

    public static Piece generateRandomPiece(int[][] caseFulled, List<Piece> pieceList) {
        Piece piece = pieceList.get(new Random().nextInt(pieceList.size()));
        int rotate = (int) (Math.random() * 4);
        IntStream.range(0, rotate).forEach(value -> piece.rotate(caseFulled));
        return piece;
    }

    private boolean checkCollisions(int[][] caseFulled, int[][] matrix, Integer posX, Integer posY) {
        if (matrix != null) {
            // Cas d'une rotation
            int columns = matrix[0].length;
            int rows = matrix.length;

            // On check les murs et les côtés
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

    private boolean checkPieceCollisions(int[][] caseFulled, int[][] matrix, Integer posX, Integer posY) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 1 && caseFulled[i + posX][j + posY] == 1) return true;
            }
        }
        return false;
    }
}
