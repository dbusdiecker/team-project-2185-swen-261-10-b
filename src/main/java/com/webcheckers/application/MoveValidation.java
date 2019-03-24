package com.webcheckers.application;

import com.webcheckers.Piece;
import com.webcheckers.model.*;

/**
 * Class for checking the validity of a move
 */
public class MoveValidation {

    private Move move;
    private CheckersGame game;

    /**
     * Creates a new moveValidation with the given information
     *
     * @param move move being checked
     * @param game game
     */
    public MoveValidation(Move move, CheckersGame game){
        this.move = move;
        this.game = game;
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

    /**
     * Determines if a jump move can be made
     *
     * @return true if a jump is possible; false otherwise
     */
    public Boolean jumpPossible(){
        Piece.color activeColor = Piece.color.WHITE;
        if(game.whoseTurn().equals(CheckersGame.activeColor.RED)){
            activeColor = Piece.color.RED;
        }

        ModelSpace[][] spaces = game.getBoard().getSpaces();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if(spaces[row][col].isHasPiece() &&
                        spaces[row][col].getPiece().getColor().equals(activeColor)){
                    ModelPiece piece = spaces[row][col].getPiece();
                    if(piece.getType().equals(Piece.type.SINGLE)){
                        if(checkNormalJump(spaces, row, col)){
                            return true;
                        }
                    }
                    else{
                        if(checkKingJump(spaces, row, col)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Helper function for jumpPossible, checks if a regular piece can make a jump
     *
     * @param spaces 2D array of the board
     * @param row row of the piece being checked
     * @param col column of the piece bing checked
     * @return true if the piece can jump; false otherwise
     */
    private Boolean checkNormalJump(ModelSpace[][] spaces, int row, int col){
        if(spaces[row][col].getPiece().getColor().equals(Piece.color.RED)){
            if(row <= 1){
                return false;
            }
            if(col < 6) {
                if (spaces[row - 1][col - 1].isHasPiece()) {
                    if(spaces[row - 1][col - 1].getPiece().getColor()
                            .equals(Piece.color.WHITE) &&
                            spaces[row - 1][col - 2].isHasPiece()){
                        return true;
                    }
                }
            }
            if(col > 1) {
                if (spaces[row - 1][col + 1].isHasPiece()) {
                    if(spaces[row - 1][col + 1].getPiece().getColor()
                            .equals(Piece.color.WHITE)&&
                            spaces[row - 1][col + 2].isHasPiece()){
                        return true;
                    }
                }
            }
        }
        else{
            if(row >= 6){
                return false;
            }
            if(col < 6) {
                if (spaces[row + 1][col - 1].isHasPiece()) {
                    if(spaces[row + 1][col - 1].getPiece().getColor()
                            .equals(Piece.color.RED) &&
                            spaces[row - 1][col - 2].isHasPiece()){
                        return true;
                    }
                }
            }
            if(col > 1) {
                if (spaces[row + 1][col + 1].isHasPiece()) {
                    if(spaces[row + 1][col + 1].getPiece().getColor()
                            .equals(Piece.color.RED)&&
                            spaces[row - 1][col + 2].isHasPiece()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Helper function for jumpPossible, checks if a king can make a jump
     *
     * @param spaces 2D array of the board
     * @param row row of the piece being checked
     * @param col column of the piece bing checked
     * @return true if the piece can jump; false otherwise
     */
    private Boolean checkKingJump(ModelSpace[][] spaces, int row, int col){
        if(checkNormalJump(spaces, row, col)){
            return true;
        }
        if(spaces[row][col].getPiece().getColor().equals(Piece.color.WHITE)){
            if(row <= 1){
                return false;
            }
            if(col < 6) {
                if (spaces[row - 1][col - 1].isHasPiece()) {
                    if(spaces[row - 1][col - 1].getPiece().getColor()
                            .equals(Piece.color.RED) &&
                            spaces[row - 1][col - 2].isHasPiece()){
                        return true;
                    }
                }
            }
            if(col > 1) {
                if (spaces[row - 1][col + 1].isHasPiece()) {
                    if(spaces[row - 1][col + 1].getPiece().getColor()
                            .equals(Piece.color.RED)&&
                            spaces[row - 1][col + 2].isHasPiece()){
                        return true;
                    }
                }
            }
        }
        else{
            if(row >= 6){
                return false;
            }
            if(col < 6) {
                if (spaces[row + 1][col - 1].isHasPiece()) {
                    if(spaces[row + 1][col - 1].getPiece().getColor()
                            .equals(Piece.color.WHITE) &&
                            spaces[row - 1][col - 2].isHasPiece()){
                        return true;
                    }
                }
            }
            if(col > 1) {
                if (spaces[row + 1][col + 1].isHasPiece()) {
                    if(spaces[row + 1][col + 1].getPiece().getColor()
                            .equals(Piece.color.WHITE)&&
                            spaces[row - 1][col + 2].isHasPiece()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean canMultiJump(){
        return false;
    }
}
