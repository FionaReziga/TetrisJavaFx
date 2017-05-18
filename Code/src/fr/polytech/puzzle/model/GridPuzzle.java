
package fr.polytech.puzzle.model;

import fr.polytech.library.model.Grid;
import fr.polytech.library.model.piece.Piece;
import fr.polytech.library.model.piece.PieceI;
import fr.polytech.library.model.piece.PieceLittleI;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static fr.polytech.puzzle.PuzzleView.PUZZLE_GRID_HEIGHT;
import static fr.polytech.puzzle.PuzzleView.PUZZLE_GRID_WIDTH;
import static java.util.Arrays.asList;
import static javafx.scene.paint.Color.*;

/**
 * Created by REZIGA on 15/05/2017.
 * Classe qui définit la grille du puzzle
 */
public class GridPuzzle extends Grid {
    List<Piece> pieceList;
    private List<Color> colorList = asList(AQUAMARINE, LIGHTBLUE, CORNFLOWERBLUE, TURQUOISE, TEAL, ROYALBLUE, DARKTURQUOISE, PALETURQUOISE, DEEPSKYBLUE, DARKCYAN, DODGERBLUE);

    /**
     * Constructeur de la grille
     * @param width
     * @param height
     * @param sizeCase
     * @param color
     * @param level
     */
    public GridPuzzle(int width, int height, int sizeCase, Color color, int level) {
        super(width, height, sizeCase, color);
        this.currentPiece = new PieceLittleI(2, 0, PUZZLE_GRID_HEIGHT, PUZZLE_GRID_WIDTH);
        currentPiece.getSetMatrixWithRotation();
        currentPiece.setColor(RED);
        initializePieceList(level);
        fillCaseFulled();
    }

    /**
     * Initialisation de la liste des pièces
     * @param level
     */
    private void initializePieceList(int level) {
        pieceList = new ArrayList<>();
        List<Piece> pieceHorizontalList = asList(
                new PieceLittleI(3, 0, PUZZLE_GRID_HEIGHT, PUZZLE_GRID_WIDTH),
                new PieceLittleI(4, 4, PUZZLE_GRID_HEIGHT, PUZZLE_GRID_WIDTH),
                new PieceI(0, 1, PUZZLE_GRID_HEIGHT, PUZZLE_GRID_WIDTH),
                new PieceI(1, 1, PUZZLE_GRID_HEIGHT, PUZZLE_GRID_WIDTH)
        );
        pieceHorizontalList.forEach(piece ->
                piece.getSetMatrixWithRotation());
        pieceList.addAll(pieceHorizontalList);
        if (level == 1) {
            pieceList.addAll(asList(
                    new PieceLittleI(0, 0, PUZZLE_GRID_HEIGHT, PUZZLE_GRID_WIDTH),
                    new PieceLittleI(2, 2, PUZZLE_GRID_HEIGHT, PUZZLE_GRID_WIDTH),
                    new PieceLittleI(2, 3, PUZZLE_GRID_HEIGHT, PUZZLE_GRID_WIDTH),
                    new PieceLittleI(4, 3, PUZZLE_GRID_HEIGHT, PUZZLE_GRID_WIDTH),
                    new PieceLittleI(0, 5, PUZZLE_GRID_HEIGHT, PUZZLE_GRID_WIDTH),
                    new PieceLittleI(2, 5, PUZZLE_GRID_HEIGHT, PUZZLE_GRID_WIDTH),
                    new PieceI(0, 4, PUZZLE_GRID_HEIGHT, PUZZLE_GRID_WIDTH)
            ));
        }
        for(int i = 0; i < colorList.size(); i ++){
            pieceList.get(i).setColor(colorList.get(i));
        }

    }

    /**
     * 
     */
    private void fillCaseFulled() {
        pieceList.forEach(piece ->
        {
            for (int i = 0; i < caseFulled.length; i++) {
                for (int j = 0; j < caseFulled[0].length; j++) {
                    int[][] matrix = piece.getMatrix();
                    for (int x = 0; x < matrix.length; x++) {
                        for (int y = 0; y < matrix[0].length; y++) {
                            if (matrix[x][y] != 0) {
                                if((piece.getPosX() + x) > 5 || (piece.getPosY() + y) > 7) {
                                    movePiece(1,1);
                                }
                                caseFulled[piece.getPosX() + x][piece.getPosY() + y] = piece.getColor();
                            }
                        }
                    }
                }
            }
        });
    }


    @Override
    public synchronized boolean movePiece(int offsetX, int offsetY) {
        super.movePiece(offsetX, offsetY);
        return true;
    }
}
