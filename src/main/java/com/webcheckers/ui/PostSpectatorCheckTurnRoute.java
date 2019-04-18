package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.CheckersGame.activeColor;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

import static com.webcheckers.model.CheckersGame.*;

/**
 * The UI Controller to POST check turn for the player
 */
public class PostSpectatorCheckTurnRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostSpectatorCheckTurnRoute.class.getName());

    private final Gson gson;
    private final GameCenter gameCenter;

    /**
     * Construct a new PostSpectatorCheckTurnRoute
     *
     * @param gameCenter gameCenter to hold games
     * @param gson Gson to handle JSON objects
     */
    public PostSpectatorCheckTurnRoute(final GameCenter gameCenter, final Gson gson){
        this.gson = Objects.requireNonNull(gson, "gameCenter is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        LOG.config("PostSpectatorCheckTurnRoute is initialized.");
    }

    /**
     * Check to see if it is the player's turn
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     *
     * @return Message saying true if it is the players turn; Message saying false otherwise
     */
    @Override
    public Object handle(Request request, Response response){
        LOG.finer("PostSpectatorCheckTurnRoute is invoked.");

        final Session httpSession = request.session();

        CheckersGame clientGame = httpSession.attribute(GetSpectatorGameRoute.SPECTATOR_ATTR);
        if (clientGame != null) {
            System.out.println("cg not null");
            String gameIDAsString = request.queryParams("gameID");
            Integer gameID = Integer.parseInt(gameIDAsString);
            CheckersGame serverGame = gameCenter.getGameByID(gameID);



            if (serverGame != null) {
                System.out.println("sg not null");
                if ( !clientGame.equals(serverGame)){
                    return gson.toJson(Message.info("true"));
                }
            }


        }

        return gson.toJson(Message.info("false"));
    }
}
