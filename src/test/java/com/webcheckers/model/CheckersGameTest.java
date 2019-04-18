package com.webcheckers.model;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@Tag("Model-tier")
public class CheckersGameTest {

    private Player player1;
    private Player player2;
    private Player currentUser;

    @BeforeEach
    public void setup(){
        player1 = mock(Player.class);
        player2 = mock(Player.class);
    }

    @Test
    public void CheckersGame_creation_red(){

        currentUser = player1;

        CheckersGame CuT = new CheckersGame(player1,player2,currentUser);

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

        currentUser = player2;

        CheckersGame CuT = new CheckersGame(player2,player1,currentUser);

        //Tests constructor values
        assertEquals(CuT.getRedPlayer(),player2);
        assertEquals(CuT.getWhitePlayer(),player1);
        assertEquals(CuT.whoseTurn(), CheckersGame.activeColor.RED);
        assertNotNull(CuT.getBoard());

        CuT.ChangeTurn();
        //Tests Change of Turn
        assertEquals(CuT.whoseTurn(), CheckersGame.activeColor.WHITE);
    }













}
