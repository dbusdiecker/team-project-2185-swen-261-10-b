package com.webcheckers.application;

import com.webcheckers.Piece;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.ModelPiece;
import com.webcheckers.model.ModelSpace;
import com.webcheckers.model.Move;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the MoveValidation class
 */
@Tag("Application-tier")
public class MoveValidationTest {

    private final static int START_ROW = 3;
    private final static int START_CELL = 2;
    private final static int VALID_END_ROW = 4;
    private final static int VALID_END_CELL = 3;
    private final static int INVALID_END_ROW = 6;
    private final static int INVALID_END_CELL = 3;

    /**
     * Test that the main constructor works without failure.
     */
    @Test
    public void ctor_withArg(){
        Move move = mock(Move.class);
        CheckersGame game = mock(CheckersGame.class);
        MoveValidation CuT = new MoveValidation(move, game);
        assertNotNull(CuT);
    }

    /**
     * Test where a move is in the valid range
     */
    @Test
    public void test_valid_range(){
        Move move = mock(Move.class);
        when(move.cellMovement()).thenReturn(VALID_END_CELL - START_CELL);
        when(move.rowMovement()).thenReturn(VALID_END_ROW - START_ROW);
        CheckersGame game = mock(CheckersGame.class);
        MoveValidation CuT = new MoveValidation(move, game);
        assertTrue(CuT.validRange());
    }

    /**
     * Test where a move is not in the valid range
     */
    @Test
    public void test_invalid_range(){
        Move move = mock(Move.class);
        when(move.cellMovement()).thenReturn(INVALID_END_CELL - START_CELL);
        when(move.rowMovement()).thenReturn(INVALID_END_ROW - START_ROW);
        CheckersGame game = mock(CheckersGame.class);
        MoveValidation CuT = new MoveValidation(move, game);
        assertFalse(CuT.validRange());
    }

    /**
     * Test where the jump of a single piece is valid
     */
    @Test
    public void test_valid_normal_jump(){
        Move move = mock(Move.class);
        CheckersGame game = mock(CheckersGame.class);
        MoveValidation CuT = new MoveValidation(move, game);
        ModelSpace spaces[][] = new ModelSpace[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                spaces[row][col] = mock(ModelSpace.class);
            }
        }

        ModelPiece movingPiece = mock(ModelPiece.class);
        when(movingPiece.getColor()).thenReturn(Piece.color.WHITE);
        ModelPiece sittingPiece = mock(ModelPiece.class);
        when(sittingPiece.getColor()).thenReturn(Piece.color.RED);

        when(spaces[START_ROW][START_CELL].isHasPiece()).thenReturn(true);
        when(spaces[START_ROW][START_CELL].getPiece()).thenReturn(movingPiece);
        when(spaces[START_ROW][START_CELL - 1].isHasPiece()).thenReturn(false);
        when(spaces[VALID_END_ROW][VALID_END_CELL].isHasPiece()).thenReturn(true);
        when(spaces[VALID_END_ROW][VALID_END_CELL].getPiece()).thenReturn(sittingPiece);
        when(spaces[VALID_END_ROW + 1][VALID_END_CELL + 1].isHasPiece()).thenReturn(false);
        assertTrue(CuT.checkNormalJump(spaces,START_ROW,START_CELL));

        when(spaces[VALID_END_ROW - 1][VALID_END_CELL + 1].isHasPiece()).thenReturn(false);
        when(spaces[START_ROW - 1][START_CELL - 1].isHasPiece()).thenReturn(false);
        assertTrue(CuT.checkNormalJump(spaces,VALID_END_ROW,VALID_END_CELL));
    }

    /**
     * Test where the jump of a single piece is invalid
     */
    @Test
    public void test_invalid_normal_jump(){
        Move move = mock(Move.class);
        CheckersGame game = mock(CheckersGame.class);
        MoveValidation CuT = new MoveValidation(move, game);
        ModelSpace spaces[][] = new ModelSpace[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                spaces[row][col] = mock(ModelSpace.class);
            }
        }

        ModelPiece movingPiece = mock(ModelPiece.class);
        when(movingPiece.getColor()).thenReturn(Piece.color.WHITE);
        ModelPiece sittingPiece = mock(ModelPiece.class);
        when(sittingPiece.getColor()).thenReturn(Piece.color.RED);

        when(spaces[START_ROW][START_CELL].isHasPiece()).thenReturn(true);
        when(spaces[START_ROW][START_CELL].getPiece()).thenReturn(movingPiece);
        when(spaces[START_ROW][START_CELL - 1].isHasPiece()).thenReturn(false);
        when(spaces[VALID_END_ROW][VALID_END_CELL].isHasPiece()).thenReturn(true);
        when(spaces[VALID_END_ROW][VALID_END_CELL].getPiece()).thenReturn(sittingPiece);
        when(spaces[VALID_END_ROW + 1][VALID_END_CELL + 1].isHasPiece()).thenReturn(true);
        assertFalse(CuT.checkNormalJump(spaces,START_ROW,START_CELL));

        when(spaces[VALID_END_ROW - 1][VALID_END_CELL + 1].isHasPiece()).thenReturn(false);
        when(spaces[START_ROW - 1][START_CELL - 1].isHasPiece()).thenReturn(true);
        assertFalse(CuT.checkNormalJump(spaces,VALID_END_ROW,VALID_END_CELL));
    }
}
