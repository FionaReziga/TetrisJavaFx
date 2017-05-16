package fr.polytech.tetris;

import fr.polytech.tetris.model.BoardTetris;

import javax.swing.*;

/**
 * Created by REZIGA on 16/05/2017.
 */
public class ThreadTetris implements Runnable {
    private BoardTetris board;

    public ThreadTetris(BoardTetris board) {
        this.board = board;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while(!board.isStop() || !board.getGrid().isGameOver()){
                Thread.sleep(1000);
                board.movePiece(1, 0);
            }
            if(board.getGrid().isGameOver()) {
                JOptionPane.showMessageDialog(null, "Perdu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
