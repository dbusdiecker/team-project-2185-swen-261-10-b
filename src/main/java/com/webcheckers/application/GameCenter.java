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

    public CheckersGame getGameByID(Integer gameID){
        return games.get(gameID);
    }

    public void resignAllGames(Player player){
        for (Integer id: games.keySet()){
            CheckersGame game = games.get(id);
            if (game.hasPlayer(player)) {
                if (game.whoseTurn() == CheckersGame.activeColor.RED){
                    if (game.getRedPlayer() == player){
                        game.ChangeTurn();
                    }
                }
                else if (game.getWhitePlayer() == player){
                    game.ChangeTurn();
                }
                game.endGame(player.getName() + " has resigned.");
            }
        }
    }


    public Integer getIDByPlayer(Player player){
        // This is operating under the assumption that
        // a player is only in one game.

        for (Integer id: games.keySet()){
            CheckersGame game = games.get(id);
            if (game.hasPlayer(player) && !game.isGameOver()) {
                return id;
            }
        }
        return null;
    }

    public Integer getIDByOpponents(Player player1, Player player2){
        for (Integer id: games.keySet()){
            CheckersGame game = games.get(id);
            if (game.hasPlayer(player1) && game.hasPlayer(player2) && !game.isGameOver()) {
                return id;
            }
            if(game.isGameOver()){
                endGame(id);
            }
        }
        return null;
    }
}
