package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
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
public class PostGameRouteTest {

    private Gson gson;
    private PlayerLobby playerLobby;
    private GameCenter gameCenter;
    private Response response;
    private Request request;
    private Session session;
    private Player P1;
    private Player P2;
    private PostGameRoute CuT;
    private int gameID;
    private String gameURL;

    @BeforeEach
    public void setup(){
        playerLobby = new PlayerLobby();
        gameCenter = new GameCenter();
        session = mock(Session.class);
        request = mock(Request.class);
        response = mock(Response.class);
        P1 = new Player("P1");
        P2 = new Player("P2");
        playerLobby.addPlayer(P1);
        playerLobby.addPlayer(P2);
        CuT = new PostGameRoute(playerLobby, gameCenter);
        gameID = 0;
        gameURL = String.format(WebServer.GAME_WITH_ID_URL, gameID);
        when(request.queryParams("opponent")).thenReturn("P2");
        when(request.session()).thenReturn(session);
        when(session.attribute("currentUser")).thenReturn(P1);
    }

    @Test
    public void test_game_create(){
        CuT.handle(request, response);
        assertEquals(1, P1.getCurrentOpponents().size());
        assertEquals(1, P2.getCurrentOpponents().size());
    }
}