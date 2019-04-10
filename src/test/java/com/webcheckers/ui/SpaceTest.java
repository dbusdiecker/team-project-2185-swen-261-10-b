package com.webcheckers.ui;

import com.webcheckers.model.ModelPiece;
import com.webcheckers.model.ModelSpace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Testing suite for the space class
 */
@Tag("UI-Tier")
public class SpaceTest {

    ModelSpace modelSpace;
    int index;
    Space CuT;

    /**
     * Test for creating the space when the associated ModelSpace is a dark space with no piece
     */
    @Test
    public void ctor_darkNoPieceTest(){
        modelSpace = new ModelSpace(ModelSpace.spaceColor.DARK);
        index = 2;
        CuT = new Space(index, modelSpace);
        assertTrue(CuT.isValid());
        assertEquals(CuT.getCellIdx(), index);
        assertNull(CuT.getPiece());
    }

    /**
     * Test for creating the space when the associated ModelSpace is a dark space with a piece
     */
    @Test
    public void ctor_darkWithPieceTest(){
        modelSpace = new ModelSpace(ModelSpace.spaceColor.DARK);
        ModelPiece mockPiece = mock(ModelPiece.class);
        modelSpace.addPiece(mockPiece);
        index = 5;
        CuT = new Space(index, modelSpace);
        assertFalse(CuT.isValid());
        assertEquals(index, CuT.getCellIdx());
        assertNotNull(CuT.getPiece());
    }

    /**
     * Test for creating the space when the associated ModelSpace is a light space
     */
   @Test
   public void ctor_lightSpace(){
        modelSpace = new ModelSpace(ModelSpace.spaceColor.LIGHT);
        index = 4;
        CuT = new Space(index, modelSpace);
        assertFalse(CuT.isValid());
        assertEquals(CuT.getCellIdx(), index);
        assertNull(CuT.getPiece());
   }
}
