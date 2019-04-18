package com.webcheckers.ui;

import com.webcheckers.model.CheckersGame;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.logging.Logger;

public class GetStopWatchingRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSpectatorGameRoute.class.getName());

    public GetStopWatchingRoute() {
        LOG.config("GetSpectatorGameRoute is initialized.");
    }

    /**
     * Render the WebCheckers Game page.
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     *
     * @return the rendered HTML for the Game page
     */
    public Object handle(Request request, Response response) {
        LOG.finer("GetStopWatchingRoute is invoked.");

        final Session httpSession = request.session();
        httpSession.removeAttribute(GetSpectatorGameRoute.SPECTATOR_ATTR);

        response.redirect(WebServer.HOME_URL);
        return null;
    }
}
