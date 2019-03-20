package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.Piece;
import com.webcheckers.model.ModelPiece;
import com.webcheckers.model.ModelSpace;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the Space class
 */
@Tag("UI-tier")
public class SpaceTest {

    private static final int CELL_INDEX = 3;

    /**
     * Test that the main constructor works without failure.
     */
    @Test
    public void ctor_withArg() {
        final Space CuT = new Space(CELL_INDEX, mock(ModelSpace.class));
        assertNotNull(CuT);
    }

    /**
     * Test getting the cell index
     */
    @Test
    public void test_cell_index(){
        final Space CuT = new Space(CELL_INDEX, mock(ModelSpace.class));
        assertEquals(CELL_INDEX, CuT.getCellIdx());
    }

    /**
     * Test that the space properly shows that it is valid
     */
    @Test
    public void test_valid(){
        ModelSpace testModel1 = mock(ModelSpace.class);
        when(testModel1.getColor()).thenReturn(ModelSpace.spaceColor.LIGHT);
        when(testModel1.isHasPiece()).thenReturn(false);
        Space CuT = new Space(CELL_INDEX, testModel1);
        assertFalse(CuT.isValid());

        ModelSpace testModel2 = mock(ModelSpace.class);
        when(testModel2.getColor()).thenReturn(ModelSpace.spaceColor.DARK);
        when(testModel2.isHasPiece()).thenReturn(false);
        CuT = new Space(CELL_INDEX, testModel2);
        assertTrue(CuT.isValid());
    }

    /**
     * Test the piece related functions
     */
    @Test
    public void test_piece(){
        ModelSpace testModel1 = mock(ModelSpace.class);
        when(testModel1.getColor()).thenReturn(ModelSpace.spaceColor.DARK);
        when(testModel1.isHasPiece()).thenReturn(false);
        Space CuT = new Space(CELL_INDEX, testModel1);
        assertNull(CuT.getPiece());

        ModelSpace testModel2 = mock(ModelSpace.class);
        when(testModel2.getColor()).thenReturn(ModelSpace.spaceColor.DARK);
        when(testModel2.isHasPiece()).thenReturn(true);
        ModelPiece testPiece = mock(ModelPiece.class);
        when(testPiece.getColor()).thenReturn(Piece.color.RED);
        when(testPiece.getType()).thenReturn(Piece.type.SINGLE);
        when(testModel2.getPiece()).thenReturn(testPiece);
        CuT = new Space(CELL_INDEX, testModel2);
        assertNotNull(CuT.getPiece());
    }
}