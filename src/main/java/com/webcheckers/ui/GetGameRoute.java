package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * The UI Controller to GET the game page
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private final Gson gson;
    private static final Message GAME_CREATION_ERROR_MSG = Message.error("Game Creation Error: Cannot create a game with a player that is currently in a game.");

    public enum viewMode {
        PLAY,
        SPECTATOR,
        REPLAY
    }

    public static Message getGAME_CREATION_ERROR_MSG() {
        return GAME_CREATION_ERROR_MSG;
    }

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /game} HTTP requests.
     *
     * @param gameCenter gameCenter to hold games
     * @param gson Gson to handle JSON objects
     * @param templateEngine The HTML template rendering engine.
     */
    public GetGameRoute(final GameCenter gameCenter, final Gson gson, final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required.");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        LOG.config("GetGameRoute is initialized.");
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
        LOG.finer("GetGameRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();


        String gameIDAsString = request.queryParams("gameID");
        if (gameIDAsString != null){
            Integer gameID = Integer.parseInt(gameIDAsString);
            CheckersGame game = gameCenter.getGameByID(gameID);

            if (game != null) {

                final Session httpSession = request.session();
                Player thisPlayer = httpSession.attribute(GetHomeRoute.PLAYER_ATTR);


                    if (thisPlayer != null) {
                        if (game.hasPlayer(thisPlayer)) {
                            vm.put("title", "Let's Play Checkers!");
                            vm.put(GetHomeRoute.PLAYER_ATTR, thisPlayer);
                            vm.put("viewMode", viewMode.PLAY);
                            vm.put("modeOptionsAsJSON", gson.toJson(game.getOptions()));
                            vm.put("redPlayer", game.getRedPlayer());
                            vm.put("whitePlayer", game.getWhitePlayer());
                            vm.put("activeColor", game.whoseTurn());
                            vm.put("opponent_list", thisPlayer.getCurrentOpponents());

                            // Look into removing the chaining here.
                            vm.put("board", game.getBoard().getBoardView(thisPlayer));

                            return templateEngine.render(new ModelAndView(vm, "game.ftl"));
                        }
                    }

            }

        }

        response.redirect(WebServer.HOME_URL);
        return null;
    }

}
