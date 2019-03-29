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
public class PostCheckTurnRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());

    private final Gson gson;
    private final GameCenter gameCenter;

    /**
     * Construct a new PostCheckTurnRoute
     *
     * @param gameCenter gameCenter to hold games
     * @param gson Gson to handle JSON objects
     */
    public PostCheckTurnRoute(final GameCenter gameCenter, final Gson gson){
        this.gson = gson;
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        //
        LOG.config("PostCheckTurnRoute is initialized.");
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
        LOG.finer("PostCheckTurnRoute is invoked.");

        final Session httpSession = request.session();
        Player player = httpSession.attribute("currentUser");

        String gameIDAsString = request.queryParams("gameID");
        Integer gameID = Integer.parseInt(gameIDAsString);
        CheckersGame game = gameCenter.getGameByID(gameID);

        activeColor activeColor = game.whoseTurn();
        
        if(activeColor.equals(CheckersGame.activeColor.RED)){
            if(game.getRedPlayer().equals(player)){
                return gson.toJson(Message.info("true"));
            }
        } else if (activeColor.equals(CheckersGame.activeColor.WHITE)){
            if(game.getWhitePlayer().equals(player)){
                return gson.toJson(Message.info("true"));
            }
        }

        return gson.toJson(Message.info("false"));
    }
}
