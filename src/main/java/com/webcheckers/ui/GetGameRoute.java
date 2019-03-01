package com.webcheckers.ui;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());
    private final TemplateEngine templateEngine;

    private Player currentUser;
    private enum viewMode{
        PLAY,
        SPECTATOR,
        REPLAY
    }

    private Map<String,Object> modeOptionsAsJSON;
    private Player redPlayer;
    private Player whitePlayer;
    private enum ActiveColor{
        RED,
        WHITE
    }
    BoardView board;
    private Message message;


    public GetGameRoute(final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("GetGameRoute is initialized.");
    }

    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Let's Play Checkers!");


        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }

}
