package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostSignOutRouteTest {

    private PostSignOutRoute CuT;

    private Request request;
    private Response response;
    private Session session;
    private GameCenter gameCenter;
    private PlayerLobby playerLobby;
    private Player player;

    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        playerLobby = new PlayerLobby();
        gameCenter = new GameCenter();
        player = new Player("TEST");
        playerLobby.addPlayer(player);

        CuT = new PostSignOutRoute(playerLobby, gameCenter);
        when(request.session()).thenReturn(session);
        when(session.attribute(GetHomeRoute.PLAYER_ATTR)).thenReturn(player);
    }

    @Test
    public void test_sign_out(){
        Object result = CuT.handle(request,response);
        assertNull(result);
        assertFalse(playerLobby.usernameAlreadyInUse("TEST"));
    }

}
