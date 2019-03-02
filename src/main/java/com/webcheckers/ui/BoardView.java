package com.webcheckers.ui;

import com.webcheckers.model.Board;

import java.util.ArrayList;
import java.util.Iterator;

public class BoardView implements Iterable{

    private Row rows[];

    public BoardView(Board board){
        this.rows = new Row[8];
        for(int row = 0; row < 8; row++){
            this.rows[row] = new Row(row, board.getSpaces()[row]);
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Row> iterator() {
        return new Iterator<Row>() {

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
    }
}
