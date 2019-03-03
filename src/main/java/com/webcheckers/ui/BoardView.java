package com.webcheckers.ui;

import com.webcheckers.model.Board;

import java.util.Iterator;

/**
 * The object to handle the ui game board
 */
public class BoardView implements Iterable{

    private Row rows[];
    public Iterator<Row> iterator;

    /**
     * Creates a Board View with the given board
     *
     * @param board The board to be simulated
     */
    public BoardView(Board board){
        this.rows = new Row[8];
        for(int row = 0; row < 8; row++){
            this.rows[row] = new Row(row, board.getSpaces()[row]);
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
                return currentIndex < 7 && rows != null;
            }

            @Override
            public Row next() {
                return rows[currentIndex++];
            }
        };
        return iterator;
    }
}
