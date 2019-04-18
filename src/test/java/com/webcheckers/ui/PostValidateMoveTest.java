package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.model.Position;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-tier")
public class PostValidateMoveTest {

    private static final Message INVALID_RANGE_MSG = Message.error("Move must be diagonal and 1 or 2 spaces");
    private static final Message BACKWARDS_MOVE_MSG = Message.error("Piece cannot move backwards");
    private static final Message INVALID_JUMP_MSG = Message.error("Piece cannot jump here");
    private static final Message JUMP_POSSIBLE_MSG = Message.error("Cannot make single move when a jump is possible");
    private static final Message VALID_MOVE_MSG = Message.info("Move is valid");
    private static final Message INVALID_SECOND_MOVE_MSG = Message.error("Not a valid second move");


    private PostValidateMove CuT;

    private GameCenter gameCenter;
    private Gson gson;
    private Request request;
    private Session session;
    private CheckersGame game;
    private Player player1;
    private Player player2;
    private Move move;
    private Response response;

    @BeforeEach
    public void setup(){

        gameCenter = new GameCenter();
        gson = new Gson();
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        player1 = new Player("one");
        player2 = new Player("one");
        gameCenter.createGame(player1,player2);

        when(request.queryParams("gameID")).thenReturn("0");
    }

    @Test
    public void singleMove(){

        move = new Move(new Position(6,3),new Position(5,4));
        when(request.queryParams("actionData")).thenReturn(gson.toJson(move));
        CuT = new PostValidateMove(gameCenter,gson);
        String result = CuT.handle(request,response);
        assertEquals(gson.toJson(VALID_MOVE_MSG),result);
    }

    @Test
    public void invalidMove(){

        move = new Move(new Position(6,3),new Position(2,4));
        when(request.queryParams("actionData")).thenReturn(gson.toJson(move));
        CuT = new PostValidateMove(gameCenter,gson);
        String result = CuT.handle(request,response);
        assertEquals(gson.toJson(INVALID_RANGE_MSG),result);
    }

    @Test
    public void invalidBackwardsMove(){

        move = new Move(new Position(6,3),new Position(7,4));
        when(request.queryParams("actionData")).thenReturn(gson.toJson(move));
        CuT = new PostValidateMove(gameCenter,gson);
        String result = CuT.handle(request,response);
        assertEquals(gson.toJson(BACKWARDS_MOVE_MSG),result);
    }


}
