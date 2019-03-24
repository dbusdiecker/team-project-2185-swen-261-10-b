package com.webcheckers.ui;

import com.webcheckers.model.Board;
import com.webcheckers.model.Player;

import java.util.Iterator;

/**
 * The object to handle the ui game board
 */
public class BoardView implements Iterable{

    private Row rows[];
    private Iterator<Row> iterator;

    /**
     * Creates a Board View with the given board
     *
     * @param board The board to be simulated
     */
    public BoardView(Board board, Player playerColor){
        this.rows = new Row[8];
        if (playerColor.equals(board.getRedPlayer())){
            for(int row = 0; row < 8; row++) {
                this.rows[row] = new Row(row, board.getSpaces()[row], "red");
            }
        }
        else{
            int index = 0;
            for (int row = 7; row >= 0; row--){
                this.rows[index] = new Row(row, board.getSpaces()[row], "white");
                index++;
            }
        }
    }

    /**
     * Creates a Java iterator of the rows on a checkers board
     *
     * @return Iterator of rows
     */
    @Override
    public Iterator<Row> iterator() {
        iterator = new Iterator<Row>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < 8 && rows != null;
            }

            @Override
            public Row next() {
                return rows[currentIndex++];
            }
        };
        return iterator;
    }
}
