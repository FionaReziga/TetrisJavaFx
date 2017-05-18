package fr.polytech.puissance4.controller;

import fr.polytech.puissance4.model.BoardPuissance;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Created by REZIGA on 16/05/2017.
 * Classe qui définit le controleur du jeu Puissance 4
 */
public class PuissanceController implements EventHandler<KeyEvent> {
    private BoardPuissance board;

    /**
     * Controleur
     *
     * @param width
     * @param height
     * @param sizeCase
     * @param color
     */
    public PuissanceController(int width, int height, int sizeCase, Color color) {
        board = new BoardPuissance(width, height, sizeCase, color);
    }

    /**
     * Renvoie le plateau du puissance 4
     *
     * @return
     */
    public BoardPuissance getBoard() {
        return board;
    }

    @Override
    public void handle(KeyEvent event) {
        if (!board.getGrid().isGameOver()) {
            switch (event.getCode()) {
                case DOWN:
                    for (int i = 0; i < board.getGrid().getHeight(); i++) {
                        if (board.movePiece(1, 0)) i = board.getGrid().getHeight();
                    }
                    break;
                case LEFT:
                    board.movePiece(0, -1);
                    break;
                case RIGHT:
                    board.movePiece(0, 1);
                    break;
            }
        }
    }

}
