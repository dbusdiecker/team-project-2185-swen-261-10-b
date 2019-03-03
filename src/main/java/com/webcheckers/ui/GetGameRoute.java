package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private enum viewMode{
        PLAY,
        SPECTATOR,
        REPLAY
    }
    private enum activeColor{
        RED,
        WHITE
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
        if ( opponent != null && thisPlayer != null){ // Both players are online, opponent is null if not online, player is null if user not logged in
            if (opponent.isInGame()){
                // See if this player is the opponent's opponent
                // Make a GameCenter Application Class?
                // GameCenter would need function that takes two players,
                // determines if they are in a game against one another
                /* Pseudocode
                if thisPlayer is opponent's opponent
                    set attrs, viewMode = PLAY
                    render game.ftl
                else
                    redirect to home, don't worry about spectate mode yet

`                */
            } else { //Opponent not in game
                // Create the game with GameCenter class
                // GameCenter can use CheckersGame model Class
                // Assign thisPlayer to be red, opponent to be white
                opponent.startGame(); //These can be called by GameCenter
                thisPlayer.startGame();
                // Put all attrs, viewMode = PLAY
                // vm.put("title", "Let's Play Checkers!");
                // vm.put(GetHomeRoute.PLAYER_ATTR, thisPlayer);
                // vm.put("viewMode",viewMode.PLAY);
                // modeOptionsAsJSON is skipped for Sprint 1 - Insert here
                // vm.put("redPlayer", thisPlayer);
                // vm.put("whitePlayer", opponent);
                // vm.put("activeColor",activeColor.RED);

                //TODO MAKE CHANGE TO GETHOMEROUTE TO CHECK IF PLAYER HAS BEEN ASSIGNED TO A GAME UPON LOADING, REDIRECT TO /GAME IF SO

                // Render game.ftl
            }
        }




        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }

}
