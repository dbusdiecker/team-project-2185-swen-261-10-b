package com.webcheckers.model;

import com.webcheckers.Piece;

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
     * @param currentUser Current view of the game
     */
    public CheckersGame(Player red, Player white, Player currentUser) {
        redPlayer = red;
        whitePlayer = white;
        modeOptionsAsJSON = new HashMap<>(2);
        this.board = new Board(redPlayer, whitePlayer);
        activeTurnColor = activeColor.RED;
        boardStates = new Stack<>();
    }

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
            activeTurnColor = activeColor.WHITE;
        } else {
            activeTurnColor = activeColor.RED;
        }
    }

    public Map<String, Object> getOptions() {
        return modeOptionsAsJSON;
    }

    public void endGame(String gameOverMessage) {
        modeOptionsAsJSON.put("isGameOver", true);
        modeOptionsAsJSON.put("gameOverMessage", gameOverMessage);
    }

    public boolean isGameOver(){
        return (modeOptionsAsJSON.containsKey("isGameOver") && modeOptionsAsJSON.get("isGameOver").equals(true));
    }

}
