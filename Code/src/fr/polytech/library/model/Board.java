package fr.polytech.library.model;

/**
 * Created by REZIGA on 14/05/2017.
 */
public class Board {

    private Grid grid;

    public Board(Grid grid) {
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }
}
