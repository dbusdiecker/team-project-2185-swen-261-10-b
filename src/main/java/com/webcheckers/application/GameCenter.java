package com.webcheckers.application;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


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
                String opponent = "";
                if (game.whoseTurn() == CheckersGame.activeColor.RED){
                    if (game.getRedPlayer() == player){
                        game.ChangeTurn();
                        opponent = game.getWhitePlayer().getName();
                    }
                }
                else if (game.getWhitePlayer() == player){
                    game.ChangeTurn();
                    opponent = game.getRedPlayer().getName();
                }
                game.endGame(player.getName() + " has resigned.", opponent );
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


    /**
     * Get the id of a game with the given players
     *
     * @param player1 first player in game
     * @param player2 second player in game
     *
     * @return gameid if there is a game; null otherwise
     */
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

    public HashMap<Integer, CheckersGame> getCurrentGames() {
        HashMap<Integer, CheckersGame> currentGames = games;
        Iterator<HashMap.Entry<Integer, CheckersGame>> it = currentGames.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Integer, CheckersGame> entry = it.next();

            // Remove entry if key is null or equals 0.
            if (entry.getValue().isGameOver()) {
                currentGames.remove(entry.getKey());
            }
        }
        return currentGames;
    }
}
