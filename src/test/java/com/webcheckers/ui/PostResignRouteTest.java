package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
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

@Tag("UI_Tier")
public class PostResignRouteTest {

    private Gson gson;
    private GameCenter gamecenter;
    private Request request;
    private Session session;
    private Response response;
    private Player p1;
    private Player p2;
    private PostResignRoute CuT;

    @BeforeEach
    public void setup(){
        p1 = new Player("Garrie");
        p2 = new Player("Jerrie");
        gson = new Gson();
        gamecenter = new GameCenter();
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        gamecenter.createGame(p1, p2);
        CuT = new PostResignRoute(gamecenter, gson);
        when(request.queryParams("gameID")).thenReturn("0");
        when(request.session()).thenReturn(session);
    }

    @Test
    public void test_successful_resignation_p1(){
        when(session.attribute("currentUser")).thenReturn(p1);
        Object result = CuT.handle(request, response);
        assertEquals(gson.toJson(Message.info(p1.getName() + " resigned")), result);
    }

    @Test
    public void test_successful_resignation_p2(){
        when(session.attribute("currentUser")).thenReturn(p2);
        Object result = CuT.handle(request, response);
        assertEquals(gson.toJson(Message.info(p2.getName() + " resigned")), result);
    }
}
