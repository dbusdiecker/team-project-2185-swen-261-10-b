package com.webcheckers.ui;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

public class PostSubmitTurn implements Route {

    private static final Logger LOG = Logger.getLogger(PostSubmitTurn.class.getName());

    private final TemplateEngine templateEngine;

    public PostSubmitTurn(final TemplateEngine templateEngine){
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("PostValidateMove is initialized.");
    }

    @Override
    public Object handle(Request request, Response response){
        LOG.finer("PostSubmitTurn is invoked.");
        final Session httpSession = request.session();

        Player thisPlayer = httpSession.attribute("currentUser");
        CheckersGame game = thisPlayer.getCurrent_game();
        game.ChangeTurn();

        return Message.info("Submit successful");
    }
}
