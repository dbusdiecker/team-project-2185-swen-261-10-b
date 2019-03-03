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
        final Session httpSession = request.session();
        final Object opposing_player = request.params("player");
        Player init_player = httpSession.attribute(GetHomeRoute.PLAYER_ATTR);
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Let's Play Checkers!");
        vm.put(GetHomeRoute.PLAYER_ATTR, init_player);
        vm.put("viewMode",viewMode.PLAY);
            //modeOptionsAsJSON is skipped for Sprint 1 - Insert here
        vm.put("redPlayer", init_player.getName());
        vm.put("whitePlayer", opposing_player);
        vm.put("activeColor",activeColor.RED);



        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }

}
