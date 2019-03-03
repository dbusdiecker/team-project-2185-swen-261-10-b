package com.webcheckers.model;

import com.webcheckers.Piece;

public class ModelPiece implements Piece{

    private Player owner;

    private Board board;

    private type type;

    private color color;

    public ModelPiece(Board board, Player owner, String colorStr){
        this.board = board;
        this.owner = owner;
        this.type = Piece.type.SINGLE;
        if(colorStr.equals("red")){
            this.color = Piece.color.RED;
        }
        else if(colorStr.equals("white")){
            this.color = Piece.color.WHITE;
        }
    }

    public void king(){
        this.type = Piece.type.KING;
    }

    public Piece.type getType(){
        return type;
    }

    public Piece.color getColor(){
        return color;
    }
}
