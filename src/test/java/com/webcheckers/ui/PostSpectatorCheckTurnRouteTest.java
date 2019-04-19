package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-Tier")
public class PostSpectatorCheckTurnRouteTest {

    private Gson gson;
    private GameCenter gameCenter;
    private Response response;
    private Request request;
    private Session session;
    private Player PR1;
    private Player PR2;
    private Player PW1;
    private Player PW2;
    private PostSpectatorCheckTurnRoute CuT;

    @BeforeEach
    public void setup(){
        response = mock(Response.class);
        request = mock(Request.class);
        session = mock(Session.class);
        gameCenter = new GameCenter();
        PR1 = new Player("PR1");
        PW1 = new Player("PW1");
        PW2 = new Player("PW2");
        gson = new Gson();
        gameCenter.createGame(PR1, PW1);
        gameCenter.createGame(PW2, PR1);
        when(request.session()).thenReturn(session);
        CuT = new PostSpectatorCheckTurnRoute(gameCenter, gson);
    }

    @Test
    public void test_turn_not_changed(){
        when(session.attribute(GetSpectatorGameRoute.SPECTATOR_ATTR)).thenReturn(gameCenter.getGameByID(0));
        when(request.queryParams("gameID")).thenReturn("0");
        assertEquals(gson.toJson(Message.info("false")), CuT.handle(request, response));
    }

}
