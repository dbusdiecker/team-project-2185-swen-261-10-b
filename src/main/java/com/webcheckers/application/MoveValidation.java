package com.webcheckers.application;

import com.webcheckers.model.Board;
import com.webcheckers.model.Move;

/**
 * Class for checking the validity of a move
 */
public class MoveValidation {

    private Move move;
    private Board board;

    public MoveValidation(Move move, Board board){
        this.move = move;
        this.board = board;
    }

    /**
     * Check the overall validity of the move
     *
     * @return boolean based on validity of the move
     */
    public Boolean validMove(){
        return validRange();
    }

    /**
     * Determines if the move is in the valid range from the start
     *
     * @return true if the move is 1 or 2 spaces diagonally; false otherwise
     */
    public Boolean validRange(){
        return (move.cellMovement() == move.rowMovement()) &&
                move.cellMovement() <=2 && move.cellMovement() > 0;
    }

    public Boolean jumpPossible(){
        return false;
    }

    public Boolean canMultiJump(){
        return false;
    }
}
