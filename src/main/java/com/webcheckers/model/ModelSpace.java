package com.webcheckers.model;

/**
 * Object for the model of a space on a checkers board
 */
public class ModelSpace {

    //Colors of the spaces
    public enum spaceColor{
        LIGHT,
        DARK
    }

    private spaceColor Color;

    private boolean hasPiece;

    private ModelPiece piece = null;

    /**
     * Creates a new checkers space of the given color
     *
     * @param color Color of the space
     */
    public ModelSpace(spaceColor color){
        this.Color = color;
        this.hasPiece = false;
    }

    /**
     * Puts a piece on the space
     *
     * @param piece Piece being put on the space
     */
    public void addPiece(ModelPiece piece){
        this.piece = piece;
        this.hasPiece = true;
    }

    /**
     *
     * @return this.color
     */
    public spaceColor getColor(){
        return Color;
    }

    /**
     *
     * @return this.hasPiece
     */
    public boolean isHasPiece(){
        return hasPiece;
    }

    /**
     *
     * @return this.piece
     */
    public ModelPiece getPiece(){
        return piece;
    }
}
