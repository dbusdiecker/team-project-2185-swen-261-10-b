package com.webcheckers.model;

/**
 * Object for a single player
 */
public class Player {

    private String name;

    /**
     * Create a new player
     *
     * @param name Name of player
     */
    public Player(String name){
        this.name = name;
    }

    /**
     *
     * @return this.name
     */
    public String getName() {
        return name;
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
