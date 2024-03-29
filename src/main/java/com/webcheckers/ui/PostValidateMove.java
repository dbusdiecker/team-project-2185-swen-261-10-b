package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.MoveValidation;
import com.webcheckers.model.Board;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Move;
import com.webcheckers.util.Message;
import spark.*;
import com.google.gson.Gson;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * The UI Controller to POST validation move
 */
public class PostValidateMove implements Route {

    private static final Logger LOG = Logger.getLogger(PostValidateMove.class.getName());

    private static final Message INVALID_RANGE_MSG = Message.error("Move must be diagonal and 1 or 2 spaces");
    private static final Message BACKWARDS_MOVE_MSG = Message.error("Piece cannot move backwards");
    private static final Message INVALID_JUMP_MSG = Message.error("Piece cannot jump here");
    private static final Message JUMP_POSSIBLE_MSG = Message.error("Cannot make single move when a jump is possible");
    private static final Message VALID_MOVE_MSG = Message.info("Move is valid");
    private static final Message INVALID_SECOND_MOVE_MSG = Message.error("Not a valid second move");

    //private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private final Gson gson;

    /**
     * Construct a new PostCheckTurnRoute
     *
     * @param gameCenter gameCenter to hold games
     * @param gson Gson to handle JSON objects
     */
    public PostValidateMove(final GameCenter gameCenter, final Gson gson){
        //this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = Objects.requireNonNull(gson, "gson is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        //
        LOG.config("PostValidateMove is initialized.");


    }

    /**
     * Check the proposed move to see if it is valid
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     *
     * @return info Message if the move is valid; error Message saying why the move is invalid otherwise
     */
    @Override
    public String handle(Request request, Response response){

        LOG.finer("PostValidateMove is invoked.");


        String gameIDAsString = request.queryParams("gameID");
        Integer gameID = Integer.parseInt(gameIDAsString);
        CheckersGame game = gameCenter.getGameByID(gameID);

        String moveAsJson = request.queryParams("actionData");
        Move move = gson.fromJson(moveAsJson, Move.class);
        MoveValidation moveValidation = new MoveValidation(move, game);

        if(!moveValidation.validSecondMoveCheck()){
            return gson.toJson(INVALID_SECOND_MOVE_MSG);
        }
        if(!move.isJumpMove()){
            if(moveValidation.jumpPossible()){
                return gson.toJson(JUMP_POSSIBLE_MSG);
            }

        } else {
            if (!moveValidation.jumpIsValid()) {
                return gson.toJson(INVALID_JUMP_MSG);
            }
        }

        if (!moveValidation.validRange()) {
            return gson.toJson(INVALID_RANGE_MSG);
        }

        if(!moveValidation.validDirection()){
            return gson.toJson(BACKWARDS_MOVE_MSG);
        }


        Board newBoard = new Board(game.getBoard());
        if(!game.boardStates.empty()){
            newBoard = new Board(game.boardStates.peek());
        }
        moveValidation.movePiece(newBoard);
        game.boardStates.push(newBoard);
        return gson.toJson(VALID_MOVE_MSG);
    }
}
