package fr.polytech.tetris.model;

import fr.polytech.library.model.piece.*;

import java.util.ArrayList;
import java.util.List;

import static fr.polytech.tetris.TetrisView.GRID_HEIGHT;
import static fr.polytech.tetris.TetrisView.GRID_WIDTH;
import static java.util.Arrays.asList;

/**
 * Created by REZIGA on 15/05/2017.
 */
public class PieceTetris extends Piece {
    public PieceTetris(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
    }

    public static Piece generateRandomPiece(int[][] caseFulled) {
        List<Piece> pieceList = new ArrayList<>();
        pieceList.addAll(asList(
                new PieceI4(0, 3, GRID_HEIGHT, GRID_WIDTH),
                new PieceL3(0, 3, GRID_HEIGHT, GRID_WIDTH),
                new PieceO4(0, 3, GRID_HEIGHT, GRID_WIDTH),
                new PieceL6(0, 3, GRID_HEIGHT, GRID_WIDTH)
        ));
        return generateRandomPiece(caseFulled, pieceList);
    }
}
