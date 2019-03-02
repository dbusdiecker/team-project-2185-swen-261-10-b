package com.webcheckers.ui;

import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private final TemplateEngine templateEngine;


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

       final Session httpSession = request.session();
       String InitPlayer = httpSession.attribute(GetHomeRoute.PLAYER_ATTR);


        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }

}
