package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import com.webcheckers.model.ModelSpace.spaceColor;

/**
 * The unit test suite for the ModelSpace class
 */
@Tag("Model-tier")
public class ModelSpaceTest {

    /**
     * Test that the main constructor works without failure.
     */
    @Test
    public void ctor_withArg() {
        ModelSpace CuT = new ModelSpace(spaceColor.DARK);
        assertNotNull(CuT);
        CuT = new ModelSpace(spaceColor.DARK);
        assertNotNull(CuT);
    }

    /**
     * Test that getColor() function
     */
    @Test
    public void test_correct_color(){
        ModelSpace CuT = new ModelSpace(spaceColor.DARK);
        assertSame(CuT.getColor(), spaceColor.DARK);
        CuT = new ModelSpace(spaceColor.LIGHT);
        assertSame(CuT.getColor(), spaceColor.LIGHT);
    }

    /**
     * Test the piece related elements of the space
     */
    @Test
    public void test_piece(){
        final ModelSpace CuT = new ModelSpace(spaceColor.DARK);
        assertFalse(CuT.isHasPiece());
        CuT.addPiece(mock(ModelPiece.class));
        assertTrue(CuT.isHasPiece());
        assertNotNull(CuT.getPiece());
        CuT.removePiece();
        assertFalse(CuT.isHasPiece());
        assertNull(CuT.getPiece());
    }
}