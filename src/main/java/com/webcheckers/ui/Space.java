package com.webcheckers.ui;

import com.webcheckers.model.ModelSpace;

public class Space {

    private int cellIndex;
    private boolean valid;
    private Piece piece;

    public Space(int cellIndex, boolean valid){
        this.cellIndex = cellIndex;
        this.valid = valid;
        this.piece = null;
    }

    public Space(int cellIndex, ModelSpace modelSpace){
        this.cellIndex = cellIndex;
        this.valid = modelSpace.getColor() == ModelSpace.spaceColor.DARK;
    }

    public int getCellIndex(){
        return cellIndex;
    }

    public boolean isValid(){
        return this.valid;
    }

    public Piece getPiece(){
        return piece;
    }
}
