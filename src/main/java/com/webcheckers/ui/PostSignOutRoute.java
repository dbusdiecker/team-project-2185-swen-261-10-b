package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * The UI Controller to sign out a player
 */
public class PostSignOutRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignOutRoute.class.getName());
    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    /**
     * Create a new PostSignOutRoute
     *
     * @param playerLobby playerLobby holding all the players
     */
    public PostSignOutRoute(final PlayerLobby playerLobby, final GameCenter gameCenter) {
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "playerLobby is required");
        LOG.config("PostSignOutRoute is initialized.");
    }

    /**
     * Function to handle the request to sign out
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     *
     * @return null
     */
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignOutRoute is invoked.");

        final Session httpSession = request.session();

        Player player = httpSession.attribute(GetHomeRoute.PLAYER_ATTR);
        if (player != null){
            gameCenter.resignAllGames(player);
            playerLobby.removePlayer(player);
            httpSession.removeAttribute(GetHomeRoute.PLAYER_ATTR);
        }

        response.redirect(WebServer.HOME_URL);
        return null;
    }
}