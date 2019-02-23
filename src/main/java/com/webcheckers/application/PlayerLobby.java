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
}
