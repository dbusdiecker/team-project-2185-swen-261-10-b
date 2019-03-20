package com.webcheckers.ui;

import java.util.Map;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import spark.*;

import static org.junit.jupiter.api.Assertions.*;
import com.webcheckers.ui.*;
import com.webcheckers.application.PlayerLobby;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public class GetHomeRouteTester {
    PlayerLobby mockPlayerLobby;
    TemplateEngine mockTemplateEngine;
    GetHomeRoute CuT;
    Request mockRequest;
    Response mockResponse;
    Session mockSession;

    @BeforeEach
    public void setup(){
        mockPlayerLobby = mock(PlayerLobby.class);
        mockTemplateEngine = mock(TemplateEngine.class);
        Request mockRequest = mock(Request.class);
        Response mockResponse = mock(Response.class);
        Session mockSession = mock(Session.class);
        when(mockRequest.session()).thenReturn(mockSession);

        CuT = new GetHomeRoute(mockPlayerLobby, mockTemplateEngine);
    }

    @Test
    public void testRequest(){
        mockPlayerLobby = mock(PlayerLobby.class);
        mockTemplateEngine = mock(TemplateEngine.class);
        Request mockRequest = mock(Request.class);
        Response mockResponse = mock(Response.class);
        Session mockSession = mock(Session.class);
        when(mockRequest.session()).thenReturn(mockSession);

        CuT = new GetHomeRoute(mockPlayerLobby, mockTemplateEngine);

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(mockTemplateEngine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(mockRequest, mockResponse);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewName("home.ftl");
    }
}
