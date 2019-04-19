package com.webcheckers.ui;

import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.Tag;
import com.webcheckers.application.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spark.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


@Tag("UI-tier")
public class PostSignInRouteTest {

    private PostSignInRoute CuT;

    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    final TemplateEngineTester testHelper = new TemplateEngineTester();

    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);
        playerLobby = new PlayerLobby();

        CuT = new PostSignInRoute(playerLobby, engine);


        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
    }

    @Test
    public void test_invalid_username(){

        when(request.queryParams("name")).thenReturn("   ");

        CuT.handle(request,response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute("message",PostSignInRoute.ERROR_MSG);

    }

    @Test
    public void test_in_use_username(){

        Player user = new Player("TEST");
        playerLobby.addPlayer(user);

        when(request.queryParams("name")).thenReturn("TEST");

        CuT.handle(request,response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute("message",PostSignInRoute.ERROR_MSG);

    }

    @Test
    public void test_valid_username(){

        when(request.queryParams("name")).thenReturn("Garrie");
        assertNull(CuT.handle(request,response));

    }














}
