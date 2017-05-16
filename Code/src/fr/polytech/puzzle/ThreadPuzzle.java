package fr.polytech.puzzle;

import fr.polytech.puzzle.model.BoardPuzzle;

/**
 * Created by REZIGA on 16/05/2017.
 */
public class ThreadPuzzle implements Runnable {
    private BoardPuzzle board;

    public ThreadPuzzle(BoardPuzzle board) {
        this.board = board;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
