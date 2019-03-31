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
public class PostBackupRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostBackupRoute.class.getName());

    private final Gson gson;
    private final GameCenter gameCenter;

    /**
     * Construct a new PostCheckTurnRoute
     *
     * @param gameCenter gameCenter to hold games
     * @param gson Gson to handle JSON objects
     */
    public PostBackupRoute(final GameCenter gameCenter, final Gson gson){
        this.gson = Objects.requireNonNull(gson, "gson is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        //
        LOG.config("PostBackupRoute is initialized.");
    }

    /**
     * Handle the request to back up a move
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     *
     * @return info Message if the move can be backed up; error Message saying the move cannot be backed up otherwise
     */
    @Override
    public Object handle(Request request, Response response){
        LOG.finer("PostBackupRoute is invoked.");

        String gameIDAsString = request.queryParams("gameID");
        Integer gameID = Integer.parseInt(gameIDAsString);
        CheckersGame game = gameCenter.getGameByID(gameID);

        if (!game.boardStates.empty()){
            game.boardStates.pop();
            return gson.toJson(Message.info("Move undone"));
        } else {
            return gson.toJson(Message.error("How did you get here?"));
        }
    }
}
