package com.webcheckers.ui;

import com.webcheckers.model.ModelSpace;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("UI-Tier")
public class RowTest {

    private Row CuT;
    private ModelSpace modelRow[];
    private final String playerColor = "red";

    @BeforeEach
    public void setup(){
        modelRow = new ModelSpace[8];
        for (int col = 0; col < 8; col++){
            modelRow[col] = mock(ModelSpace.class);
        }
        CuT = new Row(3, modelRow, playerColor);

    }

    @Test
    public void ctor_witArgs(){
        for (int col = 0; col < 8; col++){
            assertNotNull(CuT.getSpaces()[col]);
        }
        assertEquals(CuT.getIndex(), 3);
    }
}
