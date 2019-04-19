package com.webcheckers.ui;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-Tier")
public class GetStopWatchingRouteTest {

    private Session session;
    private Request request;
    private Response response;
    private GetStopWatchingRoute CuT;

    /**
     * Test to ensure this route is created properly
     */
    @Test
    public void create_route_test(){
        session = mock(Session.class);
        request = mock(Request.class);
        response = mock(Response.class);
        CuT = new GetStopWatchingRoute();
        when(request.session()).thenReturn(session);
        assertNull(CuT.handle(request, response));
    }
}
