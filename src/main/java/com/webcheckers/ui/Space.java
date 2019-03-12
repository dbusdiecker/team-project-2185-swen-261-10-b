package com.webcheckers.ui;

import com.webcheckers.model.ModelSpace;

/**
 * The object to handle the ui spaces
 */
public class Space implements com.webcheckers.Space {

    private int cellIdx;
    private boolean valid;
    public Piece piece;

    /**
     * Creates a new space with the given cellIndex and modelSpace
     * Note that valid is being set to false here if the space is initially created with a piece in it.
     * When it comes tim eto implement turns and piece movement, we will need to create the commands to edit the spaces
     * validity.
     *
     * @param cellIndex Index of the space in the row
     * @param modelSpace The model space to be simulated
     */
    public Space(int cellIndex, ModelSpace modelSpace){
        this.cellIdx = cellIndex;
        this.valid = modelSpace.getColor() == ModelSpace.spaceColor.DARK;
        if(modelSpace.isHasPiece()){
            this.piece = new Piece(modelSpace.getPiece());
            this.valid = false;
        }
    }

    /**
     *
     * @return this.cellIdx
     */
    public int getCellIdx() {
        return cellIdx;
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
