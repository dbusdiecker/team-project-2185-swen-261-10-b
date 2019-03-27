package com.webcheckers.application;

import com.webcheckers.model.Board;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import com.webcheckers.ui.BoardView;

import java.util.ArrayList;
import java.util.Hashtable;

public class GameCenter {

    Hashtable<Integer,CheckersGame> games;
    Integer last_game_ID;


    public GameCenter(){
        games = new Hashtable<Integer, CheckersGame>();
        last_game_ID = 0;

    }

    public void createGame(Player redPlayer, Player whitePlayer){
        Integer new_ID = last_game_ID + 1;
        CheckersGame game = new CheckersGame(redPlayer,whitePlayer);
        games.put(new_ID,game);
        last_game_ID = new_ID;

    }

    public Hashtable<Integer,CheckersGame> getGames(){
        return games;
    }

    public ArrayList<Player> getPlayers(Integer game_ID){
        CheckersGame current_game = games.get(game_ID);
        ArrayList<Player> result = new ArrayList<Player>();
        result.add(current_game.getRedPlayer());
        result.add(current_game.getWhitePlayer());

        return result;
    }

    public Board getBoard(Integer game_ID){
        CheckersGame current_game = games.get(game_ID);
        return current_game.getBoard();
    }

    //endGame()

}
