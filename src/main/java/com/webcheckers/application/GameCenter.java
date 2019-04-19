package com.webcheckers.application;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class to handle all the games
 */
public class GameCenter {

    private HashMap<Integer,CheckersGame> games;
    private Integer lastGameID;


    public GameCenter(){
        games = new HashMap<>();
        lastGameID = 0;

    }

    /**
     * Create a new ChekcersGame
     *
     * @param redPlayer player to control the red pieces
     * @param whitePlayer player to control the white pieces
     *
     * @return id of created game
     */
    public Integer createGame(Player redPlayer, Player whitePlayer){
        synchronized (games) {
            Integer gameID = lastGameID;
            CheckersGame game = new CheckersGame(redPlayer,whitePlayer);
            games.put(gameID,game);
            lastGameID++;
            return gameID;
        }
    }

    /**
     * Gets a game with the given id
     *
     * @param gameID id of game being looked for
     *
     * @return CheckersGame with the given id
     */
    public CheckersGame getGameByID(Integer gameID){
        synchronized (games){
            return games.get(gameID);
        }
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
        synchronized (games) {
            HashMap<Integer, CheckersGame> currentGames = new HashMap<>();

            for (Integer key: games.keySet()){
                CheckersGame game = games.get(key);
                if (!game.isGameOver()){
                    currentGames.put(key, game);
                }

            }
            return currentGames;
        }
    }
}
