package com.webcheckers.model;

import com.webcheckers.Piece;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Object for a single checkers game
 */
public class CheckersGame {

    private Player redPlayer;
    private Player whitePlayer;

    private Map<String, Object> modeOptionsAsJSON;
    Board board;
    private activeColor activeTurnColor;




    public Stack<Board> boardStates;

    //Colored pieces that can be moved
    public enum activeColor {
        RED,
        WHITE
    }


    /**
     * @return this.redPlayer
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * @return this.whitePlayer
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * @return this.activeTurnColor
     */
    public activeColor whoseTurn() {
        return activeTurnColor;
    }

    /**
     * @return this.board
     */
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board newBoard) {
        board = newBoard;
    }

    public boolean hasPlayer(Player player) {
        return player.equals(redPlayer) || player.equals(whitePlayer);
    }

    /**
     * Create a new checkers game
     *
     * @param red         Player controlling the red pieces
     * @param white       Player controlling the white pieces
     */
    public CheckersGame(Player red, Player white) {
        redPlayer = red;
        whitePlayer = white;
        this.board = new Board(redPlayer, whitePlayer);
        modeOptionsAsJSON = new HashMap<>(2);
        activeTurnColor = activeColor.RED;
        boardStates = new Stack<>();

    }


    /**
     * Changes who's turn it is
     */
    public void ChangeTurn() {
        if (activeTurnColor == activeColor.RED) {
            redPlayer.changeOpponentPriority(whitePlayer);
            whitePlayer.changeOpponentPriority(redPlayer);
            activeTurnColor = activeColor.WHITE;
        } else {
            redPlayer.changeOpponentPriority(whitePlayer);
            whitePlayer.changeOpponentPriority(redPlayer);
            activeTurnColor = activeColor.RED;
        }
    }

    public Map<String, Object> getOptions() {
        return modeOptionsAsJSON;
    }

    /**
     * End a game between two players
     * @param gameOverMessage message to be shown to the players
     * @param winner name of the player who won
     */
    public void endGame(String gameOverMessage, String winner) {
        whitePlayer.removeOpponent(redPlayer);
        redPlayer.removeOpponent(whitePlayer);
        if(winner.equals(whitePlayer.getName())){
            whitePlayer.endGame(true);
            redPlayer.endGame(false);
        }
        else{
            whitePlayer.endGame(false);
            redPlayer.endGame(true);
        }
        modeOptionsAsJSON.put("isGameOver", true);
        modeOptionsAsJSON.put("gameOverMessage", gameOverMessage);
    }

    public boolean isGameOver(){
        return (modeOptionsAsJSON.containsKey("isGameOver") && modeOptionsAsJSON.get("isGameOver").equals(true));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CheckersGame){
            CheckersGame other = (CheckersGame) obj;
            return this.getBoard().equals(other.getBoard());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return redPlayer.hashCode() + whitePlayer.hashCode() + 143;
    }
}