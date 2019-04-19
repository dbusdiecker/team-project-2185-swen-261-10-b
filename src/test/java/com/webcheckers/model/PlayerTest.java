package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class PlayerTest {

    @Test
    public void testEquals(){
        Player playerOne = new Player("George");
        assertEquals(playerOne, playerOne);

        Player playerTwo = new Player("George");
        assertEquals(playerOne, playerTwo);

        Player playerThree = new Player("Jorge");
        assertNotEquals(playerOne, playerThree);

        Object obj = new Object();
        assertNotEquals(playerOne, obj);
    }

    @Test
    public void testName(){
        String name = "Kevin";
        Player player = new Player(name);
        assertEquals(name, player.getName());
    }

    @Test
    public void test_game_start_end(){
        String name = "John";
        Player CuT = new Player(name);
        String name2 = "Kevin";
        String name3 = "Lydia";
        Player CuT2 = new Player(name2);
        Player CuT3 = new Player(name3);
        CuT.startGame();
        CuT.addOponent(CuT2);
        assertSame(CuT2, CuT.getCurrentOpponents().get(0));
        CuT.startGame();
        CuT.addOponent(CuT3);
        assertSame(CuT3, CuT.getCurrentOpponents().get(1));
        CuT.removeOpponent(CuT2);
        CuT.endGame(true);
        assertEquals(50.0, CuT.getWinRate());
        assertEquals(1, CuT.getCurrentOpponents().size());
        CuT.endGame(false);
        CuT.removeOpponent(CuT3);
        assertEquals(0, CuT.getCurrentOpponents().size());
        assertEquals(50.0, CuT.getWinRate());
        assertTrue(CuT.canJoinNewGame());
        CuT.startGame();
        CuT.startGame();
        CuT.startGame();
        CuT.startGame();
        CuT.startGame();
        assertFalse(CuT.canJoinNewGame());
    }

}
