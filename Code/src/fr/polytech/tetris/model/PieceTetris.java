package fr.polytech.tetris.model;

import fr.polytech.library.model.piece.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static fr.polytech.tetris.TetrisView.GRID_HEIGHT;
import static fr.polytech.tetris.TetrisView.GRID_WIDTH;
import static java.util.Arrays.asList;

/**
 * Created by REZIGA on 15/05/2017.
 * Classe qui définit les pièces du Tetris
 */
public class PieceTetris extends Piece {
    public PieceTetris(int posX, int posY, int gridHeight, int gridWidth) {
        super(posX, posY, gridHeight, gridWidth);
    }

    /**
     * Fonction qui génère aléatoirement une pièce
     *
     * @param caseFulled : matrice de couleur contenant les couleurs des pièces présentes dans la grille
     * @return : une pièce générée aléatoirement
     */
    public static Piece generateRandomPiece(Color[][] caseFulled) {
        List<Piece> pieceList = new ArrayList<>();
        pieceList.addAll(asList(
                new PieceBigI(0, 3, GRID_HEIGHT, GRID_WIDTH),
                new PieceL(0, 3, GRID_HEIGHT, GRID_WIDTH),
                new PieceInvL(0, 3, GRID_HEIGHT, GRID_WIDTH),
                new PieceO(0, 3, GRID_HEIGHT, GRID_WIDTH),
                new PieceS(0, 3, GRID_HEIGHT, GRID_WIDTH),
                new PieceT(0, 3, GRID_HEIGHT, GRID_WIDTH)
        ));
        return generateRandomPiece(caseFulled, pieceList);
    }
}
