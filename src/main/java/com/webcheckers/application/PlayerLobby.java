package com.webcheckers.application;

import com.webcheckers.model.Player;

import java.util.*;

/**
 * The object for the online players
 */
public class PlayerLobby {

    private Set<Player> onlinePlayers;

    /**
     * Create a new player lobby
     */
    public PlayerLobby() {
        onlinePlayers = new HashSet<>();
    }

    /**
     * Checks if username is in use.
     *
     * @param username Username to check.
     * @return True if username is being used, false otherwise.
     */
    public boolean usernameAlreadyInUse(String username) {
        Player dummyPlayer = new Player(username);
        return onlinePlayers.contains(dummyPlayer);
    }

    /**
     * Add new player to lobby
     */
    public void addPlayer(Player player) {
        onlinePlayers.add(player);
    }

    /**
     * Finds a player by their username.
     *
     * @param username Username of player to find.
     * @return Player object with the given username, null if the player does not exist.
     */
    public Player getPlayerByUsername(String username) {
        Player dummyPlayer = new Player(username);
        for (Player player : onlinePlayers) {
            if (player.equals(dummyPlayer))
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
    public boolean nameIsValid(String username) {
        if ((username == null) || username.length() == 0)
            return false;
        String trimmedUsername = username.trim();
        if (!username.equals(trimmedUsername) || trimmedUsername.length() == 0) { //Username has leading or trailing backspaces
            return false;
        }

        return username.matches("[a-zA-Z0-9 ]*");
    }

    /**
     * Gets the total number of online players.
     *
     * @return Integer value of online players.
     */
    public int getNumOnlinePlayers() {
        return onlinePlayers.size();
    }

    /**
     * @return this.onlinePlayers as Iterable
     */
    public Iterable<Player> getOnlinePlayers() {
        return onlinePlayers;
    }

    public void removePlayer(Player player) {
        onlinePlayers.remove(player);
    }


    public ArrayList<Player> sortPlayersByWinRate() {
        ArrayList<Player> sortedPlayers = new ArrayList<Player>();
        sortedPlayers.addAll(onlinePlayers);

        sortedPlayers.sort(new sortByWinRate());
        return sortedPlayers;
    }
}
    class sortByWinRate implements Comparator<Player>{

        @Override
        public int compare(Player o1, Player o2) {
            return ((int)o1.getWinRate()-(int)o2.getWinRate());
        }
    }

