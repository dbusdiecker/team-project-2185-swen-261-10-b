package com.webcheckers.model;

/**
 * Class for the movement of a piece on the board
 */
public class Move {

    private Position start;
    private Position end;

    /**
     * Creates a Move with the given positions
     *
     * @param start start position of the move
     * @param end end position of the move
     */
    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
    }

    /*
    /**
     * Creates a move with the position given in string form of "(x,y)"
     *
     * @param start start position of the move
     * @param end end position of the move
     */
    /*
    public Move(String start, String end){
        this.start = new Position(Integer.parseInt(start.substring(1,2)),
                Integer.parseInt(start.substring(3,4)));
        this.start = new Position(Integer.parseInt(end.substring(1,2)),
                Integer.parseInt(end.substring(3,4)));
    }
    */

    public Position getStart(){
        return start;
    }

    public Position getEnd(){
        return end;
    }

    /**
     * Finds the vertical movement
     *
     * @return absolute value of the difference in rows index
     */
    public int rowMovement(){
        return end.getRow() - start.getRow();
    }

    /**
     * Finds the horizontal movement
     *
     * @return absolute value of the difference in cell index
     */
    public int cellMovement(){
        return end.getCell() - start.getCell();
    }

    /**
     * Creates a string representation of the move
     *
     * @return string of move
     */
    public String toString(){
        return "start:(" + start.toString() + ") end:(" + end.toString() + ")";
    }

    /**
     * Tells whether the move is a jump move or not
     *
     * @return true if the piece move 2 spaces; false otherwise
     */
    public boolean isJumpMove(){
        return Math.abs(cellMovement()) == 2;
    }
}
