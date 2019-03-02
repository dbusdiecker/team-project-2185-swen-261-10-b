package com.webcheckers.ui;

import com.webcheckers.model.ModelSpace;

import java.util.Iterator;

public class Row implements Iterable{
    private int index;
    private Space spaces[];


    public Row(int index){
        this.index = index;
        this.spaces = new Space[8];
        for(int x = 0; x < 8; x++){
            this.spaces[x] = (new Space(x, false));
        }
    }

    public Row(int index, ModelSpace modelRow[]){
        this.index = index;
        this.spaces = new Space[8];
        for(int col = 0; col < 8; col++){
            this.spaces[col] = new Space(col, modelRow[col]);
        }
    }

    public int getIndex(){
        return this.index;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
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
