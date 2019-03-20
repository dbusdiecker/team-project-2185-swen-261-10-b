package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spark.*;

import static org.mockito.Mockito.*;

@Tag("UI-tier")
public class GetSignInRouteTest {

    private GetSignInRoute CuT;


    // mock objects
    private Request request;
    private Response response;
    private Session session;
    private TemplateEngine engine;

    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        engine = mock(TemplateEngine.class);

        CuT = new GetSignInRoute(engine);
    }

    @Test
    public void makeRequestNotLoggedIn(){
        final  TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute("title", GetSignInRoute.TITLE);
        testHelper.assertViewModelAttributeIsAbsent(GetHomeRoute.PLAYER_ATTR);
        testHelper.assertViewModelAttributeIsAbsent("message");
        testHelper.assertViewName("signin.ftl");
    }
}
