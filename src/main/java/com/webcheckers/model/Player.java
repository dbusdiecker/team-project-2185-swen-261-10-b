package com.webcheckers.model;

public class Player {
    private String name;
    private boolean inGame;

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isInGame() {return inGame;}

    public void startGame(){inGame = true;}

    public void endGame(){inGame = false;}

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

    @Override
    public int hashCode(){
        return this.name.hashCode() + 787;
    }
}
