package com.webcheckers.model;

import com.webcheckers.Space;

/**
 * Object for the model of a space on a checkers board
 */
public class ModelSpace implements Space {

    //Colors of the spaces
    public enum spaceColor{
        LIGHT,
        DARK
    }


    private spaceColor color;

    private boolean hasPiece;

    private ModelPiece piece = null;

    /**
     * Creates a new checkers space of the given color
     *
     * @param color Color of the space
     */
    public ModelSpace(spaceColor color){
        this.color = color;
        this.hasPiece = false;
    }

    /**
     * Creates a new checkers space to match the given model space
     *
     * @param modelSpace space being matched
     */
    public ModelSpace(ModelSpace modelSpace){
        this.color = modelSpace.getColor();
        this.hasPiece = modelSpace.hasPiece;
        this.piece = modelSpace.piece;
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
     * Removes the piece on the space
     */
    public void removePiece(){
        this.piece = null;
        this.hasPiece = false;
    }

    /**
     *
     * @return this.color
     */
    public spaceColor getColor(){
        return color;
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  ModelSpace){
            ModelSpace that = (ModelSpace) obj;

            ModelPiece thisPiece = this.getPiece();
            ModelPiece thatPiece = that.getPiece();
            if(thisPiece == null){
                if (thatPiece != null) //thisPiece is null thatPiece isn't, return false
                    return false;
            } else {
                if (thatPiece == null) { //thatPiece is null thisPiece isn't, return false
                    return false;
                } else {
                    if (!thisPiece.equals(thatPiece)){ //Both spaces have pieces, check if they are equal
                        return false;
                    }
                }
            }


            return this.getColor().equals(that.getColor());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 789998212;
    }
}
