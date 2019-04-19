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
        return (Math.abs(move.cellMovement()) == Math.abs(move.rowMovement())) &&
                Math.abs(move.cellMovement()) <=2; //&& move.cellMovement() > 0;
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

        ModelSpace[][] spaces;
        if(game.boardStates.empty()) {
            spaces = game.getBoard().getSpaces();
        }
        else{
            spaces = game.boardStates.peek().getSpaces();
        }

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
     *
     * @return true if the piece can jump; false otherwise
     */
    public Boolean checkNormalJump(ModelSpace[][] spaces, int row, int col){
        if(spaces[row][col].getPiece().getColor().equals(Piece.color.RED)){
            if(row <= 1){
                return false;
            }
            if(col > 1) {
                if (spaces[row - 1][col - 1].isHasPiece()) {
                    if(spaces[row - 1][col - 1].getPiece().getColor()
                            .equals(Piece.color.WHITE) &&
                            !spaces[row - 2][col - 2].isHasPiece()){
                        return true;
                    }
                }
            }
            if(col < 6) {
                if (spaces[row - 1][col + 1].isHasPiece()) {
                    if(spaces[row - 1][col + 1].getPiece().getColor().equals(Piece.color.WHITE)
                            && !spaces[row - 2][col + 2].isHasPiece()){
                        return true;
                    }
                }
            }
        }
        else{
            if(row >= 6){
                return false;
            }
            if(col > 1) {
                if (spaces[row + 1][col - 1].isHasPiece()) {
                    if(spaces[row + 1][col - 1].getPiece().getColor()
                            .equals(Piece.color.RED) &&
                            !spaces[row + 2][col - 2].isHasPiece()){
                        return true;
                    }
                }
            }
            if(col < 6) {
                if (spaces[row + 1][col + 1].isHasPiece()) {
                    if(spaces[row + 1][col + 1].getPiece().getColor()
                            .equals(Piece.color.RED)&&
                            !spaces[row + 2][col + 2].isHasPiece()){
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
     *
     * @return true if the piece can jump; false otherwise
     */
    public Boolean checkKingJump(ModelSpace[][] spaces, int row, int col){
        if(checkNormalJump(spaces, row, col)){
            return true;
        }
        if(spaces[row][col].getPiece().getColor().equals(Piece.color.WHITE)){
            if(row <= 1){
                return false;
            }
            if(col > 1) {
                if (spaces[row - 1][col - 1].isHasPiece()) {
                    if(spaces[row - 1][col - 1].getPiece().getColor()
                            .equals(Piece.color.RED) &&
                            !spaces[row - 2][col - 2].isHasPiece()){
                        return true;
                    }
                }
            }
            if(col < 6) {
                if (spaces[row - 1][col + 1].isHasPiece()) {
                    if(spaces[row - 1][col + 1].getPiece().getColor()
                            .equals(Piece.color.RED)&&
                            !spaces[row - 2][col + 2].isHasPiece()){
                        return true;
                    }
                }
            }
        }
        else{
            if(row >= 6){
                return false;
            }
            if(col > 1) {
                if (spaces[row + 1][col - 1].isHasPiece()) {
                    if(spaces[row + 1][col - 1].getPiece().getColor()
                            .equals(Piece.color.WHITE) &&
                            !spaces[row + 2][col - 2].isHasPiece()){
                        return true;
                    }
                }
            }
            if(col < 6) {
                if (spaces[row + 1][col + 1].isHasPiece()) {
                    if(spaces[row + 1][col + 1].getPiece().getColor()
                            .equals(Piece.color.WHITE)&&
                            !spaces[row + 2][col + 2].isHasPiece()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the move is in a valid direction
     *
     * @return true if the piece being moved is a king or the piece goes in forward;
     *         false if the piece is a single and moving backwards
     */
    public Boolean validDirection(){
        ModelSpace[][] spaces;
        if(game.boardStates.empty()){
            spaces = game.getBoard().getSpaces();
        }
        else{
            spaces = game.boardStates.peek().getSpaces();
        }

        int startRow = move.getStart().getRow();
        int startCel = move.getStart().getCell();
        ModelPiece piece = spaces[startRow][startCel].getPiece();
        if(piece.getType().equals(Piece.type.KING)){
            return true;
        }

        Piece.color activeColor = Piece.color.WHITE;
        if(game.whoseTurn().equals(CheckersGame.activeColor.RED)){
            activeColor = Piece.color.RED;
        }
        if(activeColor.equals(Piece.color.RED)){
            return move.rowMovement() < 0;
        }
        else{
            return move.rowMovement() > 0;
        }
    }

    /**
     * Checks if the move is a jump and whether the jump is valid
     *
     * @return true if it is a valid jump; false otherwise
     */
    public Boolean jumpIsValid(){
        ModelSpace[][] spaces;
        if(game.boardStates.empty()) {
            spaces = game.getBoard().getSpaces();
        }
        else{
            spaces = game.boardStates.peek().getSpaces();
        }
        int middleRow = (move.getStart().getRow() + move.getEnd().getRow())/2;
        int middleCel = (move.getStart().getCell() + move.getEnd().getCell())/2;

        if(!spaces[middleRow][middleCel].isHasPiece()){
            return false;
        }

        ModelPiece piece = spaces[middleRow][middleCel].getPiece();
        Piece.color pieceColor = piece.getColor();
        CheckersGame.activeColor activeColor = game.whoseTurn();
        return activeColor == CheckersGame.activeColor.RED ? pieceColor.equals(Piece.color.WHITE) : pieceColor.equals(Piece.color.RED);
    }

    /**
     * Move a piece on the board from one space to another and remove a piece
     * if the move is a jump
     *
     * @param board board being changed
     */
    public void movePiece(Board board){
        int startRow = move.getStart().getRow();
        int startCol = move.getStart().getCell();
        int endRow = move.getEnd().getRow();
        int endCol = move.getEnd().getCell();
        if(move.isJumpMove()){
            board.removePiece((startRow + endRow)/2 ,(startCol + endCol) / 2);
        }
        board.movePiece(startRow, startCol, endRow, endCol);
    }

    /**
     * Determines if the move is an initial move or if it is a valid non-initial move
     *
     * @return true if it is the first move or is a valid non-initial move; false otherwise
     */
    public boolean validSecondMoveCheck(){
        if(game.boardStates.empty()){
            return true;
        }
        int startPieces = game.getBoard().numberOfPieces();
        int currentPieces = game.boardStates.peek().numberOfPieces();
        if(startPieces == currentPieces){
            return false;
        }
        else{
            return move.isJumpMove();
        }
    }

    /**
     * Checks to see if a player has any remaining pieces
     *
     * @param playerColor color to be checked for remaining pieces
     *
     * @return true if the player has a piece; false otherwise
     */
    public boolean playerHasPieces(CheckersGame.activeColor playerColor){
        Piece.color pColor;
        if(playerColor == CheckersGame.activeColor.RED){
            pColor = Piece.color.RED;
        }
        else{
            pColor = Piece.color.WHITE;
        }
        ModelSpace[][] spaces;

        spaces = game.getBoard().getSpaces();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if(spaces[row][col].isHasPiece() && spaces[row][col].getPiece().getColor().equals(pColor)){
                        return true;
                    }
                }
            }
            return false;
    }

    /**
     * Checks if the current player can make any move
     *
     * @return true if they can; false otherwise
     */
    public boolean movePossible(){
        if(jumpPossible()){
            return true;
        }
        Piece.color activeColor = Piece.color.WHITE;
        if(game.whoseTurn().equals(CheckersGame.activeColor.RED)){
            activeColor = Piece.color.RED;
        }
        ModelSpace[][] spaces;
        if(game.boardStates.empty()) {
            spaces = game.getBoard().getSpaces();
        }
        else{
            spaces = game.boardStates.peek().getSpaces();
        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if(spaces[row][col].isHasPiece() &&
                        spaces[row][col].getPiece().getColor().equals(activeColor)){
                    if(activeColor.equals(Piece.color.RED) || spaces[row][col].getPiece().getType().equals(Piece.type.KING)) {
                        if (col < 7 && row > 0) {
                            if (!spaces[row - 1][col + 1].isHasPiece()) {
                                return true;
                            }
                        }
                        if (col > 0 && row > 0) {
                            if (!spaces[row - 1][col - 1].isHasPiece()) {
                                return true;
                            }
                        }
                    }
                    if(activeColor.equals(Piece.color.WHITE) || spaces[row][col].getPiece().getType().equals(Piece.type.KING)) {
                        if (col < 7 && row < 7) {
                            if (!spaces[row + 1][col + 1].isHasPiece()) {
                                return true;
                            }
                        }
                        if (col > 0 && row < 7) {
                            if (!spaces[row + 1][col - 1].isHasPiece()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
