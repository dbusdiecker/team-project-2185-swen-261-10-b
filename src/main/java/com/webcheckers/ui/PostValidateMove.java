package com.webcheckers.ui;

import com.webcheckers.model.Board;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.ModelSpace;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PostValidateMove implements Route {

    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private TemplateEngine templateEngine;
    private Board board;
    private Message messageToPlayer = null;
    private CheckersGame.activeColor activeTurn;

    public PostValidateMove(final TemplateEngine templateEngine, CheckersGame game){

        //One is created PER GAME
        this.templateEngine = templateEngine;
        this.board = game.getBoard();
        this.activeTurn = game.whoseTurn();
        LOG.config("PostValidateMove is initialized.");


    }

    @Override
    public Object handle(Request request, Response response){

        LOG.finer("PostValidateMove is invoked.");
        Map<String, Object> vm = new HashMap<>();

        //a vm must be put in when this is called for the coordinates the piece lies at, as well as the piece it landed on
        final Session httpSession = request.session();
        ModelSpace pieceStart = httpSession.attribute("pieceStart");
        ModelSpace pieceEnd = httpSession.attribute("pieceEnd");

        if(pieceEnd.isHasPiece()){
            //move invalid
        }






        return templateEngine.render(new ModelAndView(vm,"game.ftl"));
    }
}
