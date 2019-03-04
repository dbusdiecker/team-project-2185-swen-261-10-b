package com.webcheckers.ui;

import com.webcheckers.model.ModelSpace;

import java.util.Iterator;

/**
 * The object to handle the ui rows
 */
public class Row implements Iterable{

    private int index;
    private Space spaces[];

    /**
     * Creates a new row with the given index and model space array
     *
     * @param index The index of the row
     * @param modelRow The model spaces to be simulated
     */
    public Row(int index, ModelSpace modelRow[], String playerColor){
        this.index = index;
        this.spaces = new Space[8];
        if(playerColor.equals("red")) {
            for (int col = 0; col < 8; col++) {
                this.spaces[col] = new Space(col, modelRow[col]);
            }
        }
        else if(playerColor.equals("white")) {
            int colIndex = 0;
            for (int col = 7; col >= 0; col--){
                this.spaces[colIndex] = new Space(col, modelRow[col]);
                colIndex++;
            }
        }
    }

    /**
     *
     * @return this.index
     */
    public int getIndex(){
        return this.index;
    }

    /**
     * Creates a Java iterator of the spaces in a row
     *
     * @return Iterator of spaces
     */
    @Override
    public Iterator<Space> iterator() {
        return new Iterator<Space>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < 8 && spaces != null;
            }

            @Override
            public Space next() {
                return spaces[currentIndex++];
            }
        };
    }
}
