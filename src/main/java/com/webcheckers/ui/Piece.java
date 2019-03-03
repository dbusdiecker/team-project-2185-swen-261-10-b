package com.webcheckers.ui;

import com.webcheckers.model.ModelPiece;

/**
 * The object to handle the ui game pieces
 */
public class Piece implements com.webcheckers.Piece{

    private type type;

    //The colors for a piece
    private enum color{
        RED, WHITE;
    }

    private color color;

    /**
     * Create a piece with the given model piece
     *
     * @param modelPiece The piece to be simulated
     */
    public Piece(ModelPiece modelPiece){
        this.type = modelPiece.getType();
    }

    /**
     *
     * @return this.color
     */
    public color getColor(){
        return this.color;
    }

    /**
     *
     * @return this.type
     */
    public type getType(){
        return this.type;
    }
}
