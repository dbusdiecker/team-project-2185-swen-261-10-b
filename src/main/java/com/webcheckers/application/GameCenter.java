package com.webcheckers.application;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;

import java.util.*;


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
        ArrayList<Integer> keysToRemove = new ArrayList<Integer>();
        for (Integer id: games.keySet()){
            CheckersGame game = games.get(id);
            Player opponent;
            if(game.getRedPlayer() == player){
                opponent = game.getWhitePlayer();
            }
            else{
                opponent = game.getRedPlayer();
            }
            if (game.hasPlayer(player)) {
                if (game.whoseTurn() == CheckersGame.activeColor.RED){
                    if (game.getRedPlayer() == player){
                        game.ChangeTurn();
                    }
                }
                else if (game.getWhitePlayer() == player){
                    game.ChangeTurn();
                }
                game.endGame(player.getName() + " has resigned.",opponent.getName());
                keysToRemove.add(id);
            }
        }
        for (Integer id:keysToRemove) {
            endGame(id);
        }
    }

/** Function OUT OF DATE!
 *
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
**/
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
        HashMap<Integer, CheckersGame> currentGames = new HashMap<>();
        Iterator<Map.Entry<Integer, CheckersGame>> cgit = games.entrySet().iterator();
        GameCompare sorter = new GameCompare();

        // Switches key and value because tree map sorts by key and we want to sort by CheckersGame
        TreeMap<CheckersGame, Integer> sorted = new TreeMap<CheckersGame, Integer>(sorter);

        while (cgit.hasNext()) {
            Map.Entry<Integer, CheckersGame> entry = cgit.next();

            sorted.put(entry.getValue(), entry.getKey());
        }

        Iterator<Map.Entry<CheckersGame, Integer>> treeit = sorted.entrySet().iterator();

        while (treeit.hasNext()) {
            Map.Entry<CheckersGame, Integer> entry = treeit.next();

            // Remove entry if key is null or equals 0.
            if (!entry.getKey().isGameOver()) {
                currentGames.put(entry.getValue(), entry.getKey());
            }
        }
        return currentGames;
    }
}

class GameCompare implements Comparator<CheckersGame> {
    @Override
    public int compare(CheckersGame g1, CheckersGame g2) {
        double g1avg = (g1.getWhitePlayer().getWinRate() + g1.getRedPlayer().getWinRate()) / 2;
        double g2avg = (g2.getWhitePlayer().getWinRate() + g2.getRedPlayer().getWinRate()) / 2;
        Double diff = g1avg - g2avg;
        return diff.intValue();
    }
}