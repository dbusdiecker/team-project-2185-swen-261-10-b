package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

public class PostSubmitTurnRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostSubmitTurnRoute.class.getName());

    private final GameCenter gameCenter;
    private final Gson gson;

    public PostSubmitTurnRoute(final GameCenter gameCenter, final Gson gson){
        LOG.config("PostSubmitTurnRoute is initialized.");
        this.gameCenter =  Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.gson =  Objects.requireNonNull(gson, "gson is required");
    }

    @Override
    public Object handle(Request request, Response response){

        LOG.finer("PostSubmitTurnRoute is invoked.");

        String gameIDAsString = request.queryParams("gameID");
        Integer gameID = Integer.parseInt(gameIDAsString);
        CheckersGame game = gameCenter.getGameByID(gameID);

        game.ChangeTurn();
        game.setBoard(game.boardStates.pop());
        while(!game.boardStates.empty()){
            game.boardStates.pop();
        }
        return gson.toJson(Message.info("Submit successful"));
    }
}
