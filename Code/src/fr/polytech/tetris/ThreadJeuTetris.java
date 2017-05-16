package fr.polytech.tetris;

import fr.polytech.tetris.model.BoardTetris;

/**
 * Created by REZIGA on 16/05/2017.
 */
public class ThreadJeuTetris implements Runnable {
    private BoardTetris board;

    public ThreadJeuTetris(BoardTetris board) {
        this.board = board;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while(!board.isStop()){
                Thread.sleep(1000);
                board.movePiece(1, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
