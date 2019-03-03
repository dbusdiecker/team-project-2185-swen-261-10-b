package com.webcheckers.ui;

import com.webcheckers.model.ModelSpace;

public class Space {

    private int cellIdx;
    private boolean valid;
    private Piece piece;

    public Space(int cellIndex, boolean valid){
        this.cellIdx = cellIndex;
        this.valid = valid;
        this.piece = null;
    }

    public Space(int cellIndex, ModelSpace modelSpace){
        this.cellIdx = cellIndex;
        this.valid = modelSpace.getColor() == ModelSpace.spaceColor.DARK;
        if(modelSpace.isHasPiece()){
            this.piece = new Piece(modelSpace.getPiece());
        }
    }

    public int getCellIdx(){
        return cellIdx;
    }

    public boolean isValid(){
        return this.valid;
    }

    public Piece getPiece(){
        return piece;
    }
}
