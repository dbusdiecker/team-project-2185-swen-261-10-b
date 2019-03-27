package com.webcheckers.model;

import com.webcheckers.ui.BoardView;
import com.webcheckers.util.Message;

import java.util.Map;

/**
 * Object for a single checkers game
 */
public class CheckersGame{

    private Player redPlayer;
    private Player whitePlayer;
    private Player currentUser;
    private Map<String,Object> modeOptionsAsJSON;
    Board board;
    private Message message = null;
    private activeColor activeTurnColor;

    //Colored pieces that can be moved
    public enum activeColor {
        RED,
        WHITE
    }

    /**
     *
     * @return this.redPlayer
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     *
     * @return this.whitePlayer
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     *
     * @return this.activeTurnColor
     */
    public activeColor whoseTurn(){
        return activeTurnColor;
    }

    /**
     *
     * @return this.board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Create a new checkers game
     *
     * @param red Player controlling the red pieces
     * @param white Player controlling the white pieces
     * @param currentUser Current view of the game
     */
    public CheckersGame(Player red, Player white, Player currentUser){
        redPlayer = red;
        whitePlayer = white;
        this.currentUser = currentUser;
        redPlayer.startGame();
        whitePlayer.startGame();
        this.board = new Board(redPlayer,whitePlayer);
        activeTurnColor = activeColor.RED;
    }

    /*
    /**
     * Create a new checkers game
     *
     * Currently unused
     *
     * @param red Player controlling the red pieces
     * @param white Player controlling the white pieces
     * @param currentUser Current view of the game
     * @param board Board of the game
     */

    /*

    public CheckersGame(Player red, Player white, Player currentUser, Board board){
        redPlayer = red;
        whitePlayer = white;
        this.currentUser = currentUser;
        this.board = board;
    }


    /**
     * Creates a new checkers game
     *
     * Currently unused
     *
     * @param red Player controlling the red pieces
     * @param white Player controlling the white pieces
     * @param currentUser Current view of the game
     * @param board Board of the game
     * @param message Message for the user
     */

    /*
    public CheckersGame(Player red, Player white, Player currentUser, Board board, Message message){
        redPlayer = red;
        whitePlayer = white;
        this.currentUser = currentUser;
        this.board = board;
        this.message = message;
    }
    */

    /**
     * Changes who's turn it is
     */
    public void ChangeTurn() {
        if(activeTurnColor == activeColor.RED){
            activeTurnColor = activeColor.WHITE;
        }
        else{
            activeTurnColor = activeColor.RED;
        }
    }




}
