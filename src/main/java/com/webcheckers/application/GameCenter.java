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

    /**
     * Gets a game with the given id
     *
     * @param gameID id of game being looked for
     *
     * @return CheckersGame with the given id
     */
    public CheckersGame getGameByID(Integer gameID){
        return games.get(gameID);
    }

    /**
     * Resign all currently active games with the given player in it
     *
     * @param player player who's games are being resigned
     */
    public void resignAllGames(Player player){
        synchronized (games){
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
                game.endGame(player.getName() + " has resigned.", "");
            }
        }
    }


    /**
     * Get the id of a game with the given player
     *
     * @param player player whose game is being looked for
     *
     * @return id of game if it exist; null otherwise
     */
    public Integer getIDByPlayer(Player player){
        // This is operating under the assumption that
        // a player is only in one game.

        synchronized (games) {
            for (Integer id: games.keySet()){
                CheckersGame game = games.get(id);
                if (game.hasPlayer(player) && !game.isGameOver()) {
                    return id;
                }
            }
            return null;
        }
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
        synchronized (games) {
            for (Integer id: games.keySet()){
                CheckersGame game = games.get(id);
                if (game.hasPlayer(player1) && game.hasPlayer(player2) && !game.isGameOver()) {
                    return id;
                }
            }
            return null;
        }
    }

    /**
     * Gets all currently active games
     *
     * @return HashMap of all active games
     */
    public HashMap<Integer, CheckersGame> getCurrentGames() {
        HashMap<Integer, CheckersGame> currentGames = new HashMap<>();
        Iterator<Map.Entry<Integer, CheckersGame>> cgit = games.entrySet().iterator();

        while (cgit.hasNext()) {
            Map.Entry<Integer, CheckersGame> entry = cgit.next();

            // Remove entry if key is null or equals 0.
            if (!entry.getValue().isGameOver()) {
                currentGames.put(entry.getKey(), entry.getValue());
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