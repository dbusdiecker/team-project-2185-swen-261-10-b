package com.webcheckers.application;

import com.webcheckers.model.Player;

import java.util.HashSet;
import java.util.Set;

public class PlayerLobby {
    private Set<Player> onlinePlayers;

    public PlayerLobby(){
        onlinePlayers = new HashSet<>();
    }

    public boolean usernameAlreadyInUse(Player player){
        return onlinePlayers.contains(player);
    }

    public void addPlayer(Player player){
        onlinePlayers.add(player);
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
