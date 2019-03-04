package com.webcheckers.model;

public class Piece {

    private enum type{
        SINGLE, KING;
    }

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
