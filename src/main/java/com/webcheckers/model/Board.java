package com.webcheckers.model;

public class Board {

    private ModelSpace spaces[][];

    public Board(){
        this.spaces = new ModelSpace[8][8];
        ModelSpace.spaceColor currentColor = ModelSpace.spaceColor.LIGHT;
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                spaces[row][col] = new ModelSpace(currentColor);
                if (currentColor == ModelSpace.spaceColor.LIGHT){
                    currentColor = ModelSpace.spaceColor.DARK;
                }
                else{
                    currentColor = ModelSpace.spaceColor.LIGHT;
                }
            }
        }
    }

    public ModelSpace[][] getSpaces(){
        return spaces;
    }
}
