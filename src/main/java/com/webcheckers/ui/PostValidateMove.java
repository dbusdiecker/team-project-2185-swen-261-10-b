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

    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

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
    public Object handle(Request request, Response response){

        LOG.finer("PostValidateMove is invoked.");
        Map<String, Object> vm = new HashMap<>();

        //a vm must be put in when this is called for the coordinates the piece lies at, as well as the piece it landed on
        final Session httpSession = request.session();

        Player thisPlayer = httpSession.attribute("currentUser");
        Board board = thisPlayer.getCurrent_game().getBoard();

        vm.put("title", "Let's Play Checkers!");
        vm.put(GetHomeRoute.PLAYER_ATTR, thisPlayer);
        vm.put("viewMode", GetGameRoute.viewMode.PLAY);
        // modeOptionsAsJSON is skipped for Sprint 1 - Insert here
        vm.put("redPlayer", thisPlayer.getCurrent_game().getRedPlayer());
        vm.put("whitePlayer", thisPlayer.getCurrent_game().getWhitePlayer());
        vm.put("activeColor", thisPlayer.getCurrent_game().whoseTurn());
        vm.put("board", thisPlayer.getCurrent_game().getBoard().getBoardView(thisPlayer));

        String moveAsJson = request.queryParams("actionData");
        Move move = gson.fromJson(moveAsJson, Move.class);
        MoveValidation moveValidation = new MoveValidation(move, board);
        if(!moveValidation.validMove()){
            vm.put("message", Message.error("Invalid Move"));
            //httpSession.attribute("message", Message.error("Invalid Move"));
        }
        else{
            vm.put("message", Message.error("Valid Move"));
            //httpSession.attribute("message", Message.info("Valid Move"));
        }





        return templateEngine.render(new ModelAndView(vm,"game.ftl"));
    }
}
