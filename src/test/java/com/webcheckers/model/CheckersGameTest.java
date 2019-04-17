package com.webcheckers.model;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Board;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.mockito.internal.matchers.Equals;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("Model-tier")
public class CheckersGameTest {

    private Player player1;
    private Player player2;
    private String GAME_OVER_MESSAGE_TEST = "Test";
    private GameCenter gameCenter;

    @BeforeEach
    public void setup(){
        player1 = new Player("p1");
        player2 = new Player("p2");
        gameCenter = new GameCenter();
    }

    @Test
    public void CheckersGame_creation_red(){

        CheckersGame CuT = new CheckersGame(player1,player2);

        //Tests constructor values
        assertEquals(CuT.getRedPlayer(),player1);
        assertEquals(CuT.getWhitePlayer(),player2);
        assertEquals(CuT.whoseTurn(), CheckersGame.activeColor.RED);
        assertNotNull(CuT.getBoard());

        CuT.ChangeTurn();
        //Tests Change of Turn
        assertEquals(CuT.whoseTurn(), CheckersGame.activeColor.WHITE);
    }

    @Test
    public void CheckersGame_creation_white(){

        CheckersGame CuT = new CheckersGame(player2,player1);

        //Tests constructor values
        assertEquals(CuT.getRedPlayer(),player2);
        assertEquals(CuT.getWhitePlayer(),player1);
        assertEquals(CuT.whoseTurn(), CheckersGame.activeColor.RED);
        assertNotNull(CuT.getBoard());

        CuT.ChangeTurn();
        //Tests Change of Turn
        assertEquals(CuT.whoseTurn(), CheckersGame.activeColor.WHITE);
    }

    @Test
    public void CheckersGame_end_game(){

        int gameID = gameCenter.createGame(player1,player2);

        CheckersGame CuT = gameCenter.getGameByID(gameID);

        assertNotNull(CuT);
        assertFalse(CuT.isGameOver());
        CuT.endGame(GAME_OVER_MESSAGE_TEST,player1.getName());

        assertTrue(CuT.getOptions().containsKey("isGameOver"));
        assertTrue(CuT.getOptions().containsKey("gameOverMessage"));

        assertEquals(CuT.getRedPlayer().getWinRate(), 100.0);

    }













}
