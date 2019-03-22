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

    /**
     * Creates a move with the position given in string form of "(x,y)"
     *
     * @param start start position of the move
     * @param end end position of the move
     */
    public Move(String start, String end){
        this.start = new Position(Integer.parseInt(start.substring(1,2)),
                Integer.parseInt(start.substring(3,4)));
        this.start = new Position(Integer.parseInt(end.substring(1,2)),
                Integer.parseInt(end.substring(3,4)));
    }

    public Position getStart(){
        return start;
    }

    public Position getEnd(){
        return end;
    }

    /**
     * Creates a string representation of the move
     *
     * @return string of move
     */
    public String toString(){
        return "start:(" + start.toString() + ") end:(" + end.toString() + ")";
    }
}
