package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Board;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;

import spark.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Testing suite for the PostBackupRoute class
 */
@Tag("UI-Tier")
public class PostBackUpRouteTest {

    private Gson gson;
    private GameCenter gameCenter;
    private Response response;
    private Request request;
    private Board board1;
    private Board board2;
    private Player p1;
    private Player p2;
    private CheckersGame game;
    private PostBackupRoute CuT;

    /**
     * Setup method to initialize variables for the tests
     */
    @BeforeEach
    public void setup(){
        gson = new Gson();
        p1 = mock(Player.class);
        p2 = mock(Player.class);
        gameCenter = mock(GameCenter.class);
        game = new CheckersGame(p1, p2);
        board1 = game.getBoard();
        board2 = board1;
        board2.removePiece(1,2);
        CuT = new PostBackupRoute(gameCenter, gson);
        response = mock(Response.class);
        request = mock(Request.class);
        when(request.queryParams("gameID")).thenReturn("0");
        when(gameCenter.getGameByID(0)).thenReturn(game);
    }

    /**
     * Test to ensure that the route successfully backs up one move
     */
    @Test
    public void backupMoveSuccess(){
        game.boardStates.push(board1);
        game.boardStates.push(board2);
        CuT.handle(request, response);
        assertEquals(board1, game.boardStates.peek());
    }

}
