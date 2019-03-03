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
     * Creates a new row with the given index
     *
     * @param index The index of the row
     */
    public Row(int index){
        this.index = index;
        this.spaces = new Space[8];
        for(int x = 0; x < 8; x++){
            this.spaces[x] = (new Space(x, false));
        }
    }

    /**
     * Creates a new row with the given index and model space array
     *
     * @param index The index of the row
     *
     * @param modelRow The model spaces to be simulated
     */
    public Row(int index, ModelSpace modelRow[]){
        this.index = index;
        this.spaces = new Space[8];
        for(int col = 0; col < 8; col++){
            this.spaces[col] = new Space(col, modelRow[col]);
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
                return currentIndex < 7 && spaces != null;
            }

            @Override
            public Space next() {
                return spaces[currentIndex++];
            }
        };
    }
}
