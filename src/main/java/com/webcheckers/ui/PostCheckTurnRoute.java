package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.CheckersGame.activeColor;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.logging.Logger;

import static com.webcheckers.model.CheckersGame.*;

public class PostCheckTurnRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());

    private final Gson gson;

    public PostCheckTurnRoute(Gson gson){
        this.gson = gson;
        //
        LOG.config("PostCheckTurnRoute is initialized.");
    }

    public Object handle(Request request, Response response){
        LOG.finer("PostCheckTurnRoute is invoked.");

        final Session httpSession = request.session();
        Player player = httpSession.attribute("currentUser");
        CheckersGame game = player.getCurrent_game();
        activeColor activeColor = game.whoseTurn();
        
        if(activeColor.equals(CheckersGame.activeColor.RED)){
            if(game.getRedPlayer().equals(player)){
                return gson.toJson(Message.info("true"));
            }
        } else if (activeColor.equals(CheckersGame.activeColor.WHITE)){
            if(game.getWhitePlayer().equals(player)){
                return gson.toJson(Message.info("true"));
            }
        }

        return gson.toJson(Message.info("false"));
    }
}
