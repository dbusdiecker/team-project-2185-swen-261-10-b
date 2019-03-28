package com.webcheckers.ui;

import java.util.Map;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import spark.*;

import static org.junit.jupiter.api.Assertions.*;
import com.webcheckers.ui.*;
import com.webcheckers.application.PlayerLobby;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public class GetHomeRouteTest {

    private GetHomeRoute CuT;

    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine engine;

    private PlayerLobby playerLobby;
    private GameCenter gameCenter;


    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        engine = mock(TemplateEngine.class);

        playerLobby = new PlayerLobby();
        gameCenter = new GameCenter();

        when(request.session()).thenReturn(session);

        CuT = new GetHomeRoute(playerLobby, gameCenter, engine);
    }

    @Test
    public void redirectUserToGame(){
        Player thisPlayer = new Player("Consuela");
        Player thatPlayer = new Player("Nikolai");

        gameCenter.createGame(thisPlayer, thatPlayer);

        when(session.attribute(GetHomeRoute.PLAYER_ATTR)).thenReturn(thisPlayer);
        assertNull(CuT.handle(request, response));
    }
}
