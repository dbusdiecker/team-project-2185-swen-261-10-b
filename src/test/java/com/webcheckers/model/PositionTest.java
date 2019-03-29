package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The unit test suite for the Position Class
 */
@Tag("Model-tier")
public class PositionTest {

    private static final int ROW = 3;
    private static final int CELL = 5;

    /**
     * Test that the main constructor works without failure.
     */
    @Test
    public void ctor_withArg() {
        final Position CuT = new Position(ROW,CELL);
        assertEquals("row: 3 cell: 5", CuT.toString());
    }

    /**
     * Test the getRow() method
     */
    @Test
    public void test_correct_row(){
        final Position CuT = new Position(ROW,CELL);
        assertEquals(ROW,CuT.getRow());
    }

    /**
     * Test the getCell() method
     */
    @Test
    public void test_correct_cell(){
        final Position CuT = new Position(3,5);
        assertEquals(CELL,CuT.getCell());
    }
}
