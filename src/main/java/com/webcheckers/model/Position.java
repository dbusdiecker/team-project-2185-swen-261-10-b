package com.webcheckers.model;

/**
 * Object to represent a position on the board
 */
public class Position {

    private int row;
    private int cell;

    /**
     * Creates a new Position with the given row and cell index
     *
     * @param row row of the position
     * @param cell cell index of the position
     */
    public Position(int row, int cell){
        this.row = row;
        this.cell = cell;
    }

    public int getRow(){
        return row;
    }

    public int getCell(){
        return cell;
    }

    /**
     * Creates a string representation of the position
     *
     * @return string of position
     */
    public String toString(){
        return "row: " + row + " cell: " + cell;
    }
}
