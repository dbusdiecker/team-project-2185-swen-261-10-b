package com.webcheckers.model;

public class Player {
    private String name;
    private boolean inGame;
    private CheckersGame current_game;

    public Player(String name){
        this.name = name;
        this.current_game = null;
        boolean inGame = false;
    }

    public String getName() {
        return name;
    }

    public CheckersGame getCurrent_game;

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
