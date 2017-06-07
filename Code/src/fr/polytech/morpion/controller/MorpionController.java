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
     * @param width : largeur de la grille
     * @param height : hauteur de la grille
     * @param sizeCase : taille de la case
     * @param color : couleur des cases
     */
    public MorpionController(int width, int height, int sizeCase, Color color) {
        board = new BoardMorpion(width, height, sizeCase, color);
    }

    /**
     * Renvoie le plateau du morpion
     *
     * @return : une instance du plateau
     */
    public BoardMorpion getBoard() {
        return board;
    }

    /**
     * Gère les mouvements de la souris lors d'un clic sur une case
     * @param event : evenement de la souris
     */
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
<<<<<<< HEAD
     * Lance un nouveau jeu
=======
     * Nouveau jeu
>>>>>>> master
     */
    public void newGame() {
        board.newGame();
    }
}
