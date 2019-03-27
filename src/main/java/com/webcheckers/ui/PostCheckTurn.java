package com.webcheckers.ui;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.logging.Logger;

public class PostCheckTurn implements Route {

    private static final Logger LOG = Logger.getLogger(PostCheckTurn.class.getName());

    private final TemplateEngine templateEngine;

    public PostCheckTurn(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
        //
        LOG.config("PostCheckTurn is initialized.");
    }

    public Object handle(Request request, Response response){
        LOG.finer("PostCheckTurn is invoked.");

        final Session httpSession = request.session();
        Player player = httpSession.attribute("currentUser");
        CheckersGame game = player.getCurrent_game();
        CheckersGame.activeColor activeColor = game.whoseTurn();
        
        if(activeColor.equals(CheckersGame.activeColor.RED)){
            if(game.getRedPlayer().equals(player)){
                return Message.info("true");
            }
        }
        else{
            if(game.getWhitePlayer().equals(player)){
                return Message.info("true");
            }
        }

        return Message.info("false");
    }
}
