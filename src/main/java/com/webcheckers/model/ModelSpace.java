package com.webcheckers.model;

public class ModelSpace {

    public enum spaceColor{
        LIGHT,
        DARK
    }

    private spaceColor Color;

    private boolean hasPiece;

    private ModelPiece piece = null;

    public ModelSpace(spaceColor color){
        this.Color = color;
        this.hasPiece = false;
    }

    public void addPiece(ModelPiece piece){
        this.piece = piece;

    }
}
