package com.webcheckers.ui;

import java.util.Iterator;

public class Row implements Iterable{
    private int index;


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
        return null;
    }
}
