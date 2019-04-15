package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;
import java.util.logging.Logger;

public class PostResignRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostResignRoute.class.getName());

    private final Gson gson;
    private final GameCenter gameCenter;

    /**
     * Construct a new PostCheckTurnRoute
     *
     * @param gameCenter gameCenter to hold games
     * @param gson Gson to handle JSON objects
     */
    public PostResignRoute(final GameCenter gameCenter, final Gson gson){
        this.gson = Objects.requireNonNull(gson, "gson is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        //
        LOG.config("PostBackupRoute is initialized.");
    }

    /**
     * Ends the game and sends a message to the game to show why the game has ended
     *
     * @param request
     * @param response
     * @return
     */
    public Object handle(Request request, Response response) {
        LOG.finer("PostResignRoute is invoked.");

        String gameIDAsString = request.queryParams("gameID");
        Integer gameID = Integer.parseInt(gameIDAsString);
        CheckersGame game = gameCenter.getGameByID(gameID);

        final Session httpSession = request.session();
        Player player = httpSession.attribute("currentUser");

        if (game.whoseTurn() == CheckersGame.activeColor.RED){
            if (game.getRedPlayer() == player){
                game.ChangeTurn();
            }
        }
        else if (game.getWhitePlayer() == player){
            game.ChangeTurn();
        }
        game.endGame(player.getName() + " has resigned.");

        return gson.toJson(Message.info(player.getName() + " resigned"));
    }
}
