package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

/**
 * Unit test suite for the ModelPiece class
 */
@Tag ("Model-Tier")
public class ModelPieceTest {

    private Player playerRed;
    private Board board;
    private ModelPiece CuT;

    @BeforeEach
    public void setup(){
        playerRed = mock(Player.class);
        board = mock(Board.class);
        CuT = new ModelPiece(board, playerRed, "red");
    }
    /**
     * Test that the constructor works without failure
     */
    @Test
    public void ctor_withArg(){
        assertSame(CuT.getColor(), Piece.color.RED);
        assertSame(CuT.getType(), Piece.type.SINGLE);
    }

    /**
     * Test that turning a piece into a king works without failure
     */
    @Test
    public void test_make_king(){
        assertSame(CuT.getType(), Piece.type.SINGLE);
        CuT.king();
        assertSame(CuT.getType(), Piece.type.KING);
    }


}
