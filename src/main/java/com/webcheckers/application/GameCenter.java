package com.webcheckers.application;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;

import java.util.HashMap;


public class GameCenter {

    private HashMap<Integer,CheckersGame> games;
    private Integer lastGameID;


    public GameCenter(){
        games = new HashMap<>();
        lastGameID = 0;

    }

    public void endGame(Integer gameID){
        CheckersGame game = games.get(gameID);
        Player red = game.getRedPlayer();
        Player white = game.getWhitePlayer();
        red.endGame();
        white.endGame();
        games.remove(gameID);
    }

    public Integer createGame(Player redPlayer, Player whitePlayer){
        Integer gameID = lastGameID;
        CheckersGame game = new CheckersGame(redPlayer,whitePlayer);
        redPlayer.startGame();
        whitePlayer.startGame();
        games.put(gameID,game);
        lastGameID++;
        return gameID;
    }

    public CheckersGame getGame(Integer gameID){
        return games.get(gameID);
    }
}
