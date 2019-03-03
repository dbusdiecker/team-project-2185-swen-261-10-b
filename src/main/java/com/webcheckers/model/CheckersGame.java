package com.webcheckers.model;

import com.webcheckers.ui.BoardView;
import com.webcheckers.util.Message;

import java.util.Map;

public class CheckersGame{

    private Player redPlayer;
    private Player whitePlayer;
    private Player currentUser;
    private Map<String,Object> modeOptionsAsJSON;
    Board board;
    private Message message = null;
    private activeColor activeTurnColor;

    public enum activeColor {
        RED,
        WHITE
    }

    public Player getRedPlayer() {
        return redPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public activeColor whoseTurn(){
        return activeTurnColor;
    }

    public Board getBoard() {
        return board;
    }

    public CheckersGame(Player red, Player white, Player currentUser){
        redPlayer = red;
        whitePlayer = white;
        this.currentUser = currentUser;
        redPlayer.startGame();
        whitePlayer.startGame();
        this.board = new Board(redPlayer,whitePlayer);
    }

    public CheckersGame(Player red, Player white, Player currentUser, Board board){
        redPlayer = red;
        whitePlayer = white;
        this.currentUser = currentUser;
        this.board = board;
    }
    public CheckersGame(Player red, Player white, Player currentUser, Board board, Message message){
        redPlayer = red;
        whitePlayer = white;
        this.currentUser = currentUser;
        this.board = board;
        this.message = message;
    }

    public void ChangeTurn() {
        if(activeTurnColor == activeColor.RED){
            activeTurnColor = activeColor.WHITE;
        }
        else{
            activeTurnColor = activeColor.RED;
        }
    }




}
