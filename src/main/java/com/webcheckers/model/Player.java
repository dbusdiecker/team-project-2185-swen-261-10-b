package com.webcheckers.model;

public class Player {
    private String name;

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

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
}
