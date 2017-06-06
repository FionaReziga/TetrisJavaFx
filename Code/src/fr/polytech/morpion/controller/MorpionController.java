package fr.polytech.morpion.controller;

import fr.polytech.morpion.model.BoardMorpion;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by REZIGA on 16/05/2017.
 * Classe qui définit le controleur du jeu Puissance 4
 */
public class MorpionController implements EventHandler<MouseEvent> {
    private BoardMorpion board;

    /**
     * Controleur
     *
     * @param width
     * @param height
     * @param sizeCase
     * @param color
     */
    public MorpionController(int width, int height, int sizeCase, Color color) {
        board = new BoardMorpion(width, height, sizeCase, color);
    }

    /**
     * Renvoie le plateau du puissance 4
     *
     * @return
     */
    public BoardMorpion getBoard() {
        return board;
    }

    @Override
    public void handle(MouseEvent event) {
        if (!board.getGrid().isGameOver()) {
            Object currentObject = event.getTarget();
                if (currentObject instanceof Rectangle){
                    Rectangle currentRectangle = (Rectangle) currentObject;
                    int posX = (int) currentRectangle.getProperties().get("gridpane-row");
                    int posY = (int) currentRectangle.getProperties().get("gridpane-column");
                    if(board.getGrid().getCaseFulled(posX, posY) == null)
                    {
                        board.getGrid().generateNewPiece(posX, posY);
                        board.movePiece(0, 0);
                    }
                }
        }
    }

    /**
     * Nouveau jeu
     */
    public void newGame() {
        board.newGame();
    }
}
