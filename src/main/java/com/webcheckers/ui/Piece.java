package com.webcheckers.ui;

public class Piece implements com.webcheckers.Piece{

    private type type;

    private enum color{
        RED, WHITE;
    }

    private color color;

    public color getColor(){
        return this.color;
    }

    public type getType(){
        return this.type;
    }
}
