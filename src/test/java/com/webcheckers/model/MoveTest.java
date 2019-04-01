package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the Move Class
 */
@Tag("Model-tier")
public class MoveTest {

    private static final int START_ROW = 1;
    private static final int START_CELL = 4;
    private static final int END_ROW = 2;
    private static final int END_CELL = 5;

    private Position startPos;
    private Position endPos;

    @BeforeEach
    public void testSetup() {
        startPos = mock(Position.class);
        when(startPos.getRow()).thenReturn(START_ROW);
        when(startPos.getCell()).thenReturn(START_CELL);
        when(startPos.toString()).thenReturn("row: " + START_ROW + " cell: " + START_CELL);

        endPos = mock(Position.class);
        when(endPos.getRow()).thenReturn(END_ROW);
        when(endPos.getCell()).thenReturn(END_CELL);
        when(endPos.toString()).thenReturn("row: " + END_ROW + " cell: " + END_CELL);
    }

    /**
     * Test that the main constructor works without failure.
     */
    @Test
    public void ctor_withArg() {
        final Move CuT = new Move(startPos, endPos);
        assertEquals("start:(row: " + START_ROW + " cell: " + START_CELL + ")" +
                " end:(row: " + END_ROW + " cell: " + END_CELL + ")",CuT.toString());
    }

    /**
     * Test the rowMovement() function
     */
    @Test
    public void test_row_movement(){
        final Move CuT = new Move(startPos, endPos);
        assertEquals(END_ROW - START_ROW, CuT.rowMovement());
    }

    /**
     * Test the cellMovement() function
     */
    @Test
    public void test_cell_movement(){
        final Move CuT = new Move(startPos, endPos);
        assertEquals(END_CELL - START_CELL, CuT.cellMovement());
    }

    /**
     * Test the getStart() function
     */
    @Test
    public void test_correct_start(){
        final Move CuT = new Move(startPos, endPos);
        assertEquals(startPos,CuT.getStart());
    }

    /**
     * Test the getEnd() function
     */
    @Test
    public void test_correct_end(){
        final Move CuT = new Move(startPos, endPos);
        assertEquals(endPos,CuT.getEnd());
    }
}
