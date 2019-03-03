package com.webcheckers.ui;

import com.webcheckers.model.ModelPiece;

public class Piece implements com.webcheckers.Piece{

    private type type;

    private enum color{
        RED, WHITE;
    }

    private color color;

    public Piece(ModelPiece modelPiece){
        this.type = modelPiece.getType();
    }

    public color getColor(){
        return this.color;
    }

    public type getType(){
        return this.type;
    }
}
