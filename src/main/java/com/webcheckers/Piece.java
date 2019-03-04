package com.webcheckers;

/**
 * Interface for a piece
 */
public interface Piece {

    //Type of piece
    enum type{
        SINGLE, KING;
    }

    //Color of piece
    enum color{
        RED, WHITE;
    }
}
