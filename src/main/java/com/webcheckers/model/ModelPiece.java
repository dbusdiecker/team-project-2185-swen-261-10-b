package com.webcheckers.model;

import com.webcheckers.Piece;

public class ModelPiece implements Piece{

    private Player owner;

    private Board board;

    private type type;

    public ModelPiece(Board board, Player owner){
        this.board = board;
        this.owner = owner;
        this.type = Piece.type.SINGLE;
    }

    public void king(){
        this.type = Piece.type.KING;
    }
}
