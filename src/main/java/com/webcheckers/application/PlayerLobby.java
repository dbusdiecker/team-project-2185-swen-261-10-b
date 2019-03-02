package com.webcheckers.application;

import com.webcheckers.model.Player;

import java.util.HashSet;
import java.util.Set;

public class PlayerLobby {
    private Set<Player> onlinePlayers;

    public PlayerLobby(){
        onlinePlayers = new HashSet<>();
    }

    public boolean usernameAlreadyInUse(String username){
        Player dummyPlayer = new Player(username);
        return onlinePlayers.contains(dummyPlayer);
    }

    public void addPlayer(Player player){
        onlinePlayers.add(player);
    }

    public Player getPlayerByUsername(String username){
        Player dummyPlayer = new Player(username);
        for( Player player: onlinePlayers){
            if( player.equals(dummyPlayer))
                return player;
        }
        return null;
    }

    public boolean nameIsValid(String username){
        if ( (username == null) || username.length() == 0)
            return false;
        return username.matches("[a-zA-Z0-9 ]*");
    }

    public Iterable<Player> getOnlinePlayers() {
        return onlinePlayers;
    }
}
