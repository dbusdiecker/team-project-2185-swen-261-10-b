package com.webcheckers.ui;

import com.webcheckers.model.ModelSpace;

/**
 * The object to handle the ui spaces
 */
public class Space {

    private int cellIndex;
    private boolean valid;
    private Piece piece;

    /**
     * Creates a new space with the given cellIndex and valid
     *
     * @param cellIndex Index of the space in the row
     *
     * @param valid Whether or not a piece can land on the space
     */
    public Space(int cellIndex, boolean valid){
        this.cellIndex = cellIndex;
        this.valid = valid;
        this.piece = null;
    }

    /**
     * Creates a new space with the given cellIndex and modelSpace
     *
     * @param cellIndex Index of the space in the row
     *
     * @param modelSpace The model space to be simulated
     */
    public Space(int cellIndex, ModelSpace modelSpace){
        this.cellIndex = cellIndex;
        this.valid = modelSpace.getColor() == ModelSpace.spaceColor.DARK;
        if(modelSpace.isHasPiece()){
            this.piece = new Piece(modelSpace.getPiece());
        }
    }

    /**
     *
     * @return this.cellIndex
     */
    public int getCellIndex(){
        return cellIndex;
    }

    /**
     *
     * @return this.valid
     */
    public boolean isValid(){
        return this.valid;
    }

    /**
     *
     * @return this.piece
     */
    public Piece getPiece(){
        return piece;
    }
}
