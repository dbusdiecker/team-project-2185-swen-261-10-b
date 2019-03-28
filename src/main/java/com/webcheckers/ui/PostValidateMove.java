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

    private static final Message INVALID_RANGE_MSG = Message.error("Move must be diagonal and 1 or 2 spaces");
    private static final Message BACKWARDS_MOVE_MSG = Message.error("Piece cannot move backwards");
    private static final Message INVALID_JUMP_MSG = Message.error("Piece cannot jump here");

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

        if(!moveValidation.validRange()){
            return gson.toJson(INVALID_RANGE_MSG);
        }
        /*
        if(!moveValidation.validDirection()){
            return gson.toJson(BACKWARDS_MOVE_MSG);
        }
        */
        if(!moveValidation.checkJump()){
            return gson.toJson(INVALID_JUMP_MSG);
        }
        return gson.toJson(Message.info(""));

        /*
        if(moveValidation.validMove()){
            return gson.toJson(Message.info("Valid Move"));
        }
        else{
            return gson.toJson(Message.error("Invalid Move"));
        }
        */
    }
}
