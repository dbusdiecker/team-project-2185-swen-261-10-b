package com.webcheckers.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Object for a single player
 */
public class Player {

    private String name;
    private int activeGames;
    private double wins;
    private double totalGames;
    private double winRate;
    private List<Player> currentOpponents = new ArrayList<>();
    private static int MAX_GAMES = 5;

    /**
     * Create a new player
     *
     * @param name Name of player
     */
    public Player(String name){
        this.name = name;
        this.activeGames = 0;
        this.winRate = 0;
    }

    /**
     *
     * @return this.name
     */
    public String getName() {
        return name;
    }


    public double getWinRate(){
        return winRate;
    }

    public boolean canJoinNewGame(){
        return(activeGames < MAX_GAMES);
    }

    public List<Player> getCurrentOpponents(){
        return currentOpponents;
    }

    public void addOponent(Player opponent){
        currentOpponents.add(opponent);
    }

    public void removeOpponent(Player opponent){
        currentOpponents.remove(opponent);
    }

    /**
     * Puts the given opponent at the end of the current opponents list
     *
     * @param opponent opponent to be removed
     */
    public void changeOpponentPriority(Player opponent){
        currentOpponents.remove(opponent);
        currentOpponents.add(currentOpponents.size(), opponent);
    }

    /**
     * Set inGame to true
     */
    public void startGame(){
        activeGames++;
        totalGames++;
    }

    /**
     * Set inGame to false
     */
    public void endGame(boolean won){
        activeGames--;
        if(won){
            wins++;
        }
        winRate = ((wins/ (totalGames-activeGames) ) *100);
    }

    /**
     * Determine if two players are equal
     *
     * @param obj Second object for comparison
     * @return True if they are equal; false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if( obj == this) {
            return true;
        }
        if( obj instanceof Player){
            final Player that = (Player) obj;
            return this.name.equals(that.getName());
        }
        return false;
    }

    /**
     * Creates a hash code for the player
     *
     * @return Int to represent player
     */
    @Override
    public int hashCode(){
        return this.name.hashCode() + 787;
    }
}
