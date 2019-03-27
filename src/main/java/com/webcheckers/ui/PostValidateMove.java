package com.webcheckers.ui;

import com.webcheckers.application.MoveValidation;
import com.webcheckers.model.Board;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostValidateMove implements Route {

    private static final Logger LOG = Logger.getLogger(PostValidateMove.class.getName());

    private final TemplateEngine templateEngine;
    private Board board;
    private Message messageToPlayer = null;
    private CheckersGame.activeColor activeTurn;
    private Gson gson = new Gson();

    public PostValidateMove(final TemplateEngine templateEngine){
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("PostValidateMove is initialized.");


    }

    @Override
    public String handle(Request request, Response response){

        LOG.finer("PostValidateMove is invoked.");
        Map<String, Object> vm = new HashMap<>();

        //a vm must be put in when this is called for the coordinates the piece lies at, as well as the piece it landed on
        final Session httpSession = request.session();

        Player thisPlayer = httpSession.attribute("currentUser");
        CheckersGame game = thisPlayer.getCurrent_game();

        String moveAsJson = request.queryParams("actionData");
        Move move = gson.fromJson(moveAsJson, Move.class);
        MoveValidation moveValidation = new MoveValidation(move, game);
        if(moveValidation.validMove()){
            moveValidation.movePiece();
            return gson.toJson(Message.info("Valid Move"));
        }
        else{
            return gson.toJson(Message.error("Invalid Move"));

        }
    }
}
