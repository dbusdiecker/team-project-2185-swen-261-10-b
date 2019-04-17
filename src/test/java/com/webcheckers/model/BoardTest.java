package com.webcheckers.model;

import com.webcheckers.model.Board;
import com.webcheckers.model.ModelSpace;
import com.webcheckers.model.Player;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag ("Model-tier")
public class BoardTest {

    private Player playerRed;
    private Player playerWhite;
    private Board CuT;

    @BeforeEach
    public void setup() {
        playerRed = mock(Player.class);
        playerWhite = mock(Player.class);

        CuT = new Board(playerRed, playerWhite);
    }

    @Test
    public void ctor_player_check() {

        //Tests that the players were set to the correct side
        assertEquals(CuT.getRedPlayer(), playerRed);
        assertEquals(CuT.getWhitePlayer(), playerWhite);
    }

    @Test
    public void ctor_spaces_check() {
        ModelSpace.spaceColor currColor = ModelSpace.spaceColor.LIGHT;
        //Tests all spaces on the board for the correct space color, and for pieces on correct spaces
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                assertEquals(CuT.getSpaces()[row][col].getColor(), currColor);
                if (row > 4) {
                    if (currColor == ModelSpace.spaceColor.DARK) {
                        assertTrue(CuT.getSpaces()[row][col].isHasPiece());
                    } else {
                        assertFalse(CuT.getSpaces()[row][col].isHasPiece());
                    }
                } else if (row < 3) {
                    if (currColor == ModelSpace.spaceColor.DARK) {
                        assertTrue(CuT.getSpaces()[row][col].isHasPiece());
                    } else {
                        assertFalse(CuT.getSpaces()[row][col].isHasPiece());
                    }
                }
                if (currColor == ModelSpace.spaceColor.LIGHT) {
                    currColor = ModelSpace.spaceColor.DARK;
                } else {
                    currColor = ModelSpace.spaceColor.LIGHT;
                }
            }
            if (currColor == ModelSpace.spaceColor.LIGHT) {
                currColor = ModelSpace.spaceColor.DARK;
            } else {
                currColor = ModelSpace.spaceColor.LIGHT;
            }
        }
    }

    @Test
    public void test_number_of_pieces(){
        assertEquals(CuT.numberOfPieces(), 24);
        CuT.removePiece(0, 1);
        assertFalse(CuT.getSpaces()[0][1].isHasPiece());
        assertEquals(CuT.numberOfPieces(), 23);
    }

    @Test
    public void test_movePiece(){
        CuT.movePiece(2, 1, 3, 0);
        assertFalse(CuT.getSpaces()[2][1].isHasPiece());
        assertTrue(CuT.getSpaces()[3][0].isHasPiece());
    }
}
