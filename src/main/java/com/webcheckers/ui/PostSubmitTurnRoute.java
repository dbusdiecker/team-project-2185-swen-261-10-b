package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.application.MoveValidation;
import com.webcheckers.model.ModelPiece;
import com.webcheckers.model.ModelSpace;
import com.webcheckers.model.Move;
import com.webcheckers.util.Message;
import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * The UI Controller to submit a turn
 */
public class PostSubmitTurnRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostSubmitTurnRoute.class.getName());

    private static final Message ADDITIONAL_JUMP_POSSIBLE = Message.error("Cannot end turn while a jump is still possible.");
    private static final String PIECES_CAPTURED_STRING = "%s has captured all of the pieces.";
    private final GameCenter gameCenter;
    private final Gson gson;

    /**
     * Create a new PostSubmitTurnRoute
     *
     * @param gameCenter gameCenter holding the games
     * @param gson Gson to handle JSON objects
     */
    public PostSubmitTurnRoute(final GameCenter gameCenter, final Gson gson){
        LOG.config("PostSubmitTurnRoute is initialized.");
        this.gameCenter =  Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.gson =  Objects.requireNonNull(gson, "gson is required");
    }

    /**
     * Check if the current state of the turn is valid
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     *
     * @return info Message if the turn is valid; error Message saying why the turn is invalid otherwise
     */
    @Override
    public Object handle(Request request, Response response){

        LOG.finer("PostSubmitTurnRoute is invoked.");

        String gameIDAsString = request.queryParams("gameID");
        Integer gameID = Integer.parseInt(gameIDAsString);
        CheckersGame game = gameCenter.getGameByID(gameID);
        String moveAsJson = request.queryParams("actionData");
        Move move = gson.fromJson(moveAsJson, Move.class);
        MoveValidation jumpChecker = new MoveValidation(move, game);
        int startNum = game.getBoard().numberOfPieces();
        int endNum = game.boardStates.peek().numberOfPieces();
        Piece.color activeColor = com.webcheckers.Piece.color.WHITE;
        if(game.whoseTurn().equals(CheckersGame.activeColor.RED)){
            activeColor = com.webcheckers.Piece.color.RED;
        }
        ModelSpace prevSpaces[][] = game.boardStates.peek().getSpaces();
        ModelSpace currSpaces[][] = game.getBoard().getSpaces();
        ModelPiece piece;
        boolean check = false;
        if (jumpChecker.jumpPossible() && (endNum < startNum)){
            for (int row = 0; row < 8; row++){
                for (int col = 0; col < 8; col++){
                    if (!currSpaces[row][col].isHasPiece() &&
                            prevSpaces[row][col].isHasPiece() &&
                            prevSpaces[row][col].getPiece().getColor().equals(activeColor)){
                        piece = prevSpaces[row][col].getPiece();
                        if (piece.getType() == com.webcheckers.Piece.type.SINGLE){
                            if(jumpChecker.checkNormalJump(prevSpaces, row, col)){
                                return gson.toJson(ADDITIONAL_JUMP_POSSIBLE);
                            }
                            check = true;
                            break;
                        }
                        else{
                            if (jumpChecker.checkKingJump(prevSpaces, row, col)){
                                return gson.toJson(ADDITIONAL_JUMP_POSSIBLE);
                            }
                            check = true;
                            break;
                        }
                    }
                }
                if (check){
                    break;
                }
            }
        }
        if(!jumpChecker.movePossible()){
            String loser;
            String winner;
            if (activeColor == com.webcheckers.Piece.color.WHITE){
                loser = game.getWhitePlayer().getName();
                winner = game.getRedPlayer().getName();

            }
            else{
                loser = game.getRedPlayer().getName();
                winner = game.getWhitePlayer().getName();
            }
            game.endGame(loser + " cannot make a move",winner);
        }
        game.ChangeTurn();
        game.setBoard(game.boardStates.pop());
        while(!game.boardStates.empty()){
            game.boardStates.pop();
        }
        String name;
        if(!jumpChecker.playerHasPieces(CheckersGame.activeColor.RED) ||
                !jumpChecker.playerHasPieces(CheckersGame.activeColor.WHITE)){
            if (activeColor == com.webcheckers.Piece.color.WHITE){
                name = game.getWhitePlayer().getName();
            }
            else{
                name = game.getRedPlayer().getName();
            }
            game.endGame(String.format(PIECES_CAPTURED_STRING, name),name);
        }
        if(!jumpChecker.movePossible()){
            String loser;
            String winner;
            if (activeColor == com.webcheckers.Piece.color.WHITE){
                winner = game.getRedPlayer().getName();
                loser = game.getWhitePlayer().getName();
            }
            else{
                loser = game.getRedPlayer().getName();
                winner = game.getWhitePlayer().getName();
            }
            game.endGame(loser + " cannot make a move",winner);
        }

        return gson.toJson(Message.info("Submit successful"));
    }
}
