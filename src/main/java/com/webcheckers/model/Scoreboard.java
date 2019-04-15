package com.webcheckers.model;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;

import java.util.ArrayList;

public class Scoreboard {

    private GameCenter gameCenter;
    private PlayerLobby playerLobby;


    public Scoreboard(GameCenter gCenter, PlayerLobby players){
        gameCenter = gCenter;
        playerLobby = players;
    }

    public ArrayList<Player> sortPlayersByWinRate(){
        ArrayList<Player> sortedPlayers = new ArrayList<Player>();

        //use .sort with comparator?

        return sortedPlayers;
    }


}
