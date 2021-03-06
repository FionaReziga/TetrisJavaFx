package fr.polytech.tetris;

import fr.polytech.tetris.model.BoardTetris;
import fr.polytech.tetris.model.GridTetris;

import javax.swing.*;

/**
 * Created by REZIGA on 16/05/2017.
 * Classe qui génère le thread du jeu TETRIS
 */
public class ThreadTetris implements Runnable {
    private BoardTetris board;
    private GridTetris grid;

    public ThreadTetris(BoardTetris board) {
        this.board = board;
        this.grid = (GridTetris) board.getGrid();
        new Thread(this).start();
    }

    /**
     * Lance le thread du jeu Tetris permettant de rendre le jeu autonome
     */
    @Override
    public void run() {
        try {
            while (board.isRun()) {
                int speed = 1000 - board.getScore();
                Thread.sleep(speed < 400 ? 400 : speed);
                board.movePiece(1, 0);

                if (grid.isGameOver()) {
                    JOptionPane.showMessageDialog(null, "Perdu");
                    Thread.currentThread().interrupt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
