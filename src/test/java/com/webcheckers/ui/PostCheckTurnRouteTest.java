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
public class PostCheckTurnRouteTest {

    private Gson gson;
    private GameCenter gameCenter;
    private Response responce;
    private Request request;
    private Session session;
    private Player currentUser;
    private CheckersGame game;
    private Player otherPlayer;
    private PostCheckTurnRoute CuT;

    @BeforeEach
    public void setup(){

        responce = mock(Response.class);
        request = mock(Request.class);
        session = mock(Session.class);
        currentUser = new Player("current user");
        otherPlayer = new Player("other");
        gameCenter = new GameCenter();
        gson = new Gson();

        when(request.session()).thenReturn(session);
        when(session.attribute("currentUser")).thenReturn(currentUser);
        when(request.queryParams("gameID")).thenReturn("0");

        CuT = new PostCheckTurnRoute(gameCenter,gson);

    }

    @Test
    public void testIsTurn(){

        gameCenter.createGame(currentUser,otherPlayer);
        Object result = CuT.handle(request,responce);

        assertEquals(gson.toJson(Message.info("true")),result);

    }

    @Test
    public void testIsNotTurn(){

        gameCenter.createGame(otherPlayer,currentUser);
        Object result = CuT.handle(request,responce);

        assertEquals(gson.toJson(Message.info("false")),result);

    }







}
