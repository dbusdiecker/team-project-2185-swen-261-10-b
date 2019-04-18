package com.webcheckers.application;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Testing suite for the GameCenter class
 */
@Tag("Application-tier")
public class GameCenterTest {

    Player mockP1;
    Player mockP2;
    Player mockP3;
    Player mockP4;
    GameCenter CuT;

    /**
     * Creates the needed players and initializes the CuT
     */
    @BeforeEach
    public void setup(){
        mockP1 = mock(Player.class);
        mockP2 = mock(Player.class);
        mockP3 = mock(Player.class);
        mockP4 = mock(Player.class);
        CuT = new GameCenter();
    }

    /**
     * Test to ensure the correct players are inserted into a new game when it is created
     */
    @Test
    public void createGameSuccess(){
        int test = CuT.createGame(mockP1, mockP2);
        assertEquals(test, 0);
        assertEquals(CuT.getGameByID(0).getRedPlayer(), mockP1);
        assertEquals(CuT.getGameByID(0).getWhitePlayer(), mockP2);
    }

    /**
     * Test to ensure the correct game is returned by the getGameByID method
     */
    @Test
    public void getGameByIDTest(){
        int g1 = CuT.createGame(mockP1, mockP2);
        int g2 = CuT.createGame(mockP3, mockP4);
        assertEquals(CuT.getGameByID(g1).getRedPlayer(), mockP1);
        assertEquals(CuT.getGameByID(g2).getRedPlayer(), mockP3);
    }

    /**
     * Test to ensure you can get the correct id is returned by the getIDByPlayer method
     */
    @Test
    public void get_id_by_opponents_test(){
        Integer g1 = CuT.createGame(mockP1, mockP2);
        Integer g2 = CuT.createGame(mockP3, mockP4);
        assertEquals(g1, CuT.getIDByOpponents(mockP1,mockP2));
        assertEquals(g2, CuT.getIDByOpponents(mockP3,mockP4));
    }

    /**
     * Test for ending a game
     *
     *
     */
    @Test
    public void end_game_test(){
        Integer g1 = CuT.createGame(mockP1,mockP2);
        Integer g2 = CuT.createGame(mockP3, mockP4);
        CuT.endGame(g1);

        assertNull(CuT.getGameByID(g1));
        assertNotNull(CuT.getGameByID(g2));
    }

    @Test
    public void resign_all_games_test(){

        mockP1 = new Player("test");
        mockP2 = new Player("test2");
        mockP3 = new Player("test3");
        mockP4 = new Player("test4");

        Integer g1 = CuT.createGame(mockP1,mockP2);
        Integer g2 = CuT.createGame(mockP1, mockP4);
        Integer g3 = CuT.createGame(mockP3,mockP1);

        CuT.resignAllGames(mockP1);

        assertNull(CuT.getGameByID(g1));
        assertNull(CuT.getGameByID(g2));
        assertNull(CuT.getGameByID(g3));

        assertTrue(mockP1.getCurrentOpponents().isEmpty());

    }
}
