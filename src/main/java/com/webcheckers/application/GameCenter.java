package com.webcheckers.application;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;

import java.util.HashMap;


public class GameCenter {

    HashMap<Integer,CheckersGame> games;
    Integer lastGameID;


    public GameCenter(){
        games = new HashMap<>();
        lastGameID = 0;

    }

    public void endGame(Integer gameID){
        games.remove(gameID);
    }

    public void createGame(Player redPlayer, Player whitePlayer){
        CheckersGame game = new CheckersGame(redPlayer,whitePlayer);
        games.put(lastGameID++,game);
    }

    public CheckersGame getGame(Integer gameID){
        return games.get(gameID);
    }
}
