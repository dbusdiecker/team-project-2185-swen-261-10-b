package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private static final Message GAME_CREATION_ERROR_MSG = Message.error("Game Creation Error: Cannot create a game with a player that is currently in a game.");

    private enum viewMode {
        PLAY,
        SPECTATOR,
        REPLAY
    }

    public static Message getGAME_CREATION_ERROR_MSG() {
        return GAME_CREATION_ERROR_MSG;
    }

    public GetGameRoute(final PlayerLobby playerLobby, final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required.");
        //
        LOG.config("GetGameRoute is initialized.");
    }

    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();

        final Session httpSession = request.session();
        final String opponentName = request.queryParams("player");
        Player opponent = playerLobby.getPlayerByUsername(opponentName);
        Player thisPlayer = httpSession.attribute(GetHomeRoute.PLAYER_ATTR);
        if (opponent != null && thisPlayer != null) { // Both players are online, opponent is null if not online, player is null if user not logged in
            if (opponent.isInGame()) {
                // See if this player is the opponent's opponent
                if(thisPlayer.getCurrent_game() == null){
                    httpSession.attribute("message", GAME_CREATION_ERROR_MSG);
                    response.redirect(WebServer.HOME_URL);
                }
                else if (((thisPlayer.getCurrent_game().getWhitePlayer().equals(opponent))|| (thisPlayer.getCurrent_game().getRedPlayer().equals(opponent)))) {
                    vm.put("title", "Let's Play Checkers!");
                    vm.put(GetHomeRoute.PLAYER_ATTR, thisPlayer);
                    vm.put("viewMode", viewMode.PLAY);
                    // modeOptionsAsJSON is skipped for Sprint 1 - Insert here
                    vm.put("redPlayer", thisPlayer.getCurrent_game().getRedPlayer());
                    vm.put("whitePlayer", thisPlayer.getCurrent_game().getWhitePlayer());
                    vm.put("activeColor", thisPlayer.getCurrent_game().whoseTurn());
                    vm.put("board", thisPlayer.getCurrent_game().getBoard().getBoardView(thisPlayer));

                    return templateEngine.render(new ModelAndView(vm, "game.ftl"));
                } else {
                    httpSession.attribute("message", GAME_CREATION_ERROR_MSG);
                    response.redirect(WebServer.HOME_URL);
                }
            } else {

                CheckersGame game = new CheckersGame(thisPlayer, opponent, thisPlayer);
                thisPlayer.setCurrent_game(game);
                opponent.setCurrent_game(game);
                // Put all attrs, viewMode = PLAY
                vm.put("title", "Let's Play Checkers!");
                vm.put(GetHomeRoute.PLAYER_ATTR, thisPlayer);
                vm.put("viewMode", viewMode.PLAY);
                // modeOptionsAsJSON is skipped for Sprint 1 - Insert here
                vm.put("redPlayer", thisPlayer);
                vm.put("whitePlayer", opponent);
                vm.put("activeColor", CheckersGame.activeColor.RED);
                vm.put("board", game.getBoard().getBoardView(thisPlayer));

                return templateEngine.render(new ModelAndView(vm, "game.ftl"));

            }
        }
        return null;
    }
}
