package com.webcheckers.model;

public class ModelSpace {

    public enum spaceColor{
        LIGHT,
        DARK
    }

    private spaceColor Color;

    private boolean hasPiece;

    public ModelSpace(spaceColor color){
        this.Color = color;
        this.hasPiece = false;

    }
}
