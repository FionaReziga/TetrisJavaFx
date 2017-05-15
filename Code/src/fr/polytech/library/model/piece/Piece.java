package fr.polytech.library.model.piece;

import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

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

    public void move(int offsetX, int offsetY) {
        int futurPosX = this.posX + offsetX;
        int futurPosY = this.posY + offsetY;
        // this.matrix.length + 1 car on souhaite descendre la pièce d'une case et non pas de sa taille totale
        if (futurPosX >= 0 && futurPosX <= (gridHeight - this.matrix.length + 1)) this.posX = futurPosX;
        if (futurPosY >= 0 && futurPosY <= (gridWidth - this.matrix[0].length)) this.posY = futurPosY;
    }

    public void rotate() {
        int columns = this.matrix[0].length;
        int rows = this.matrix.length;

        int[][] newMatrix = new int[columns][rows];

        for (int j = 0; j < columns; j++) {
            for (int i = 0, k = rows - 1; i < rows; i++, k--) {
                newMatrix[j][i] = matrix[k][j];
            }
        }

        this.matrix = newMatrix;

        int futurPosX = this.posX + columns;
        int futurPosY = this.posY + rows;
        if (futurPosX < 0 || futurPosX > gridHeight + 1) {
            move(-1, 0);
            //futurPosX = this.posX + rows;
        }
        if (futurPosY < 0 || futurPosY > gridWidth) {
            move(0, -1);
            //futurPosY = this.posY + columns;
        }
    }

    private Color generateRandomColor() {
        List<Color> colorList = asList(RED, ORANGE, YELLOW, GREEN, CYAN, BLUE, DARKBLUE, PINK, PURPLE, GREY, BROWN);
        return colorList.get(new Random().nextInt(colorList.size()));
    }
}
