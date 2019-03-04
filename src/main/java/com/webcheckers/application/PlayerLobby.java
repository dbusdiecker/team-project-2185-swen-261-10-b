package com.webcheckers.application;

import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerLobby {
    private Set<Player> onlinePlayers;

    public PlayerLobby(){
        onlinePlayers = new HashSet<>();
    }

    /**
     * Checks if a player is free to play a game.
     *
     * @param username Username of player being checked.
     * @return True if player is available for a game, false if they don't exist or are in a game.
     */
    public boolean playerAvailable(String username){
        if (!usernameAlreadyInUse(username)) {
            return false;
        }
        Player thePlayer = getPlayerByUsername(username);
        return (!thePlayer.isInGame());
    }

    /**
     * Checks if username is in use.
     *
     * @param username Username to check.
     * @return True if username is being used, false otherwise.
     */
    public boolean usernameAlreadyInUse(String username){
        Player dummyPlayer = new Player(username);
        return onlinePlayers.contains(dummyPlayer);
    }

    public void addPlayer(Player player){
        onlinePlayers.add(player);
    }

    /**
     * Finds a player by their username.
     *
     * @param username Username of player to find.
     * @return Player object with the given username, null if the player does not exist.
     */
    public Player getPlayerByUsername(String username){
        Player dummyPlayer = new Player(username);
        for( Player player: onlinePlayers){
            if( player.equals(dummyPlayer))
                return player;
        }
        return null;
    }

    /**
     * Checks if name is valid.
     *
     * @param username Username to be checked.
     * @return True if name contains only alphanumeric characters and spaces, false if it contains special characters or
     * if name is already in use.
     */
    public boolean nameIsValid(String username){
        if ( (username == null) || username.length() == 0)
            return false;
        return username.matches("[a-zA-Z0-9 ]*");
    }

    /**
     * Gets the total number of online players.
     *
     * @return Integer value of online players.
     */
    public int getNumOnlinePlayers(){
        return onlinePlayers.size();
    }

    /**
     *
     * @return this.onlinePlayers as Iterable
     */
    public Iterable<Player> getOnlinePlayers() {
        return onlinePlayers;
    }
}
