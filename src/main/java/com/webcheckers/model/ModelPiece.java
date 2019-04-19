package com.webcheckers.model;

import com.webcheckers.Piece;

/**
 * Object for the model of a checkers piece
 */

public class ModelPiece implements Piece{

    private Player owner;

    private Board board;

    private type type;

    private color color;

    /**
     * Creates a model piece with the given board and owner
     *
     * @param board Board the piece is on
     * @param owner Player who controls the piece
     */
    public ModelPiece(Board board, Player owner, String colorStr){
        this.board = board;
        this.owner = owner;
        this.type = Piece.type.SINGLE;
        if(colorStr.equals("red")){
            this.color = Piece.color.RED;
        }
        else if(colorStr.equals("white")){
            this.color = Piece.color.WHITE;
        }
    }

    /**
     * Makes this piece a king piece
     */
    public void king(){
        this.type = Piece.type.KING;
    }

    /**
     *
     * @return this.type
     */
    public Piece.type getType(){
        return type;
    }

    /**
     *
     * @return this.color
     */
    public Piece.color getColor(){
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  ModelPiece){
            ModelPiece that = (ModelPiece) obj;
            return  this.getColor().equals(that.getColor()) && this.getType().equals(that.getType());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 48;
    }
}
