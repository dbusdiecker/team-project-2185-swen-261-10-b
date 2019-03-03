package com.webcheckers.model;

public class Board {

    private ModelSpace spaces[][];

    private Player redPlayer;

    private Player whitePlayer;

    public Board(Player red, Player white){
        this.redPlayer = red;
        this.whitePlayer = white;
        this.spaces = new ModelSpace[8][8];
        ModelSpace.spaceColor currentColor = ModelSpace.spaceColor.LIGHT;
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                spaces[row][col] = new ModelSpace(currentColor);
                if (row < 3 && currentColor == ModelSpace.spaceColor.DARK){
                    spaces[row][col].addPiece(new ModelPiece(this, redPlayer));
                }
                else if (row > 4 && currentColor == ModelSpace.spaceColor.DARK){
                    spaces[row][col].addPiece(new ModelPiece(this, whitePlayer));
                }
                if (currentColor == ModelSpace.spaceColor.LIGHT){
                    currentColor = ModelSpace.spaceColor.DARK;
                }
                else{
                    currentColor = ModelSpace.spaceColor.LIGHT;
                }
            }
        }
    }
}
