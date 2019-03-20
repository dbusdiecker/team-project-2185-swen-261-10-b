package com.webcheckers.ui;

import com.webcheckers.Piece;
import com.webcheckers.model.ModelPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;

@Tag ("UI-tier")
public class PieceTest {

    private com.webcheckers.ui.Piece CuT;
    private ModelPiece modelPiece;

    @BeforeEach
    public void setup(){
        modelPiece = mock(ModelPiece.class);
        CuT = new com.webcheckers.ui.Piece(modelPiece);
    }

    @Test
    public void ctor_test(){
        assertEquals(CuT.getType(), modelPiece.getType());
        assertEquals(CuT.getColor(), modelPiece.getColor());
    }

}
