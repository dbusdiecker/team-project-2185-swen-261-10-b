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

}
