package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.logging.Logger;

public class PostGameRoute  implements Route {

    private static final Logger LOG = Logger.getLogger(PostGameRoute.class.getName());

    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    public PostGameRoute(PlayerLobby playerLobby, GameCenter gameCenter){
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
        LOG.config("PostGameRoute is initialized.");
    }

    public Object handle(Request request, Response response){
        LOG.finer("PostGameRoute is invoked.");

        String opponentName = request.queryParams("opponent");
        Player opponent = playerLobby.getPlayerByUsername(opponentName);

        final Session httpSession = request.session();
        Player player = httpSession.attribute("currentUser");

        if (opponent != null && player != null){
            if((player.canJoinNewGame()) && opponent.canJoinNewGame()) {
                int gameId;
                if (gameCenter.getIDByOpponents(player, opponent) != null) {
                    gameId = gameCenter.getIDByOpponents(player, opponent);
                } else {
                    gameId = gameCenter.createGame(player, opponent);
                    player.addOponent(opponent);
                    opponent.addOponent(player);
                }
                String gameURL = String.format(WebServer.GAME_WITH_ID_URL, gameId);
                response.redirect(gameURL);
                return null;
            }
        }

        response.redirect(WebServer.HOME_URL);
        return null;
    }
}
