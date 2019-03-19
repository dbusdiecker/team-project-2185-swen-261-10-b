package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

public class PostSignOutRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignOutRoute.class.getName());
    private final PlayerLobby playerLobby;


    public PostSignOutRoute(final PlayerLobby playerLobby) {
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        LOG.config("PostSignOutRoute is initialized.");
    }

    public Object handle(Request request, Response response) {
        LOG.finer("PostSignOutRoute is invoked.");

        final Session httpSession = request.session();

        Player player = httpSession.attribute(GetHomeRoute.PLAYER_ATTR);
        if (player != null){
            playerLobby.removePlayer(player);
            httpSession.removeAttribute(GetHomeRoute.PLAYER_ATTR);
        }

        response.redirect(WebServer.HOME_URL);
        return null;
    }
}