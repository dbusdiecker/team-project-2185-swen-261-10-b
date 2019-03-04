package com.webcheckers.model;

import com.webcheckers.ui.BoardView;

/**
 * Object for the model of a checkers board
 */
public class Board {

    private ModelSpace spaces[][];

    private Player redPlayer;

    private Player whitePlayer;

    private BoardView boardView;

    /**
     *
     * @return this.boardView
     */
    public BoardView getBoardView() {
        return boardView;
    }

    /**
     * Creates a new checkers board with the given red and white players
     *
     * @param red The player for the red pieces
     * @param white The player for the white pieces
     */
    public Board(Player red, Player white){
        this.redPlayer = red;
        this.whitePlayer = white;
        this.spaces = new ModelSpace[8][8];
        ModelSpace.spaceColor currentColor = ModelSpace.spaceColor.LIGHT;
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                spaces[row][col] = new ModelSpace(currentColor);
                if (row > 4 && currentColor == ModelSpace.spaceColor.DARK){
                    spaces[row][col].addPiece(new ModelPiece(this, redPlayer, "red"));
                }
                else if (row < 3 && currentColor == ModelSpace.spaceColor.DARK){
                    spaces[row][col].addPiece(new ModelPiece(this, whitePlayer, "white"));
                }
                if (currentColor == ModelSpace.spaceColor.LIGHT){
                    currentColor = ModelSpace.spaceColor.DARK;
                }
                else{
                    currentColor = ModelSpace.spaceColor.LIGHT;
                }
            }
            if (currentColor == ModelSpace.spaceColor.LIGHT){
                currentColor = ModelSpace.spaceColor.DARK;
            }
            else{
                currentColor = ModelSpace.spaceColor.LIGHT;
            }
        }
        boardView = new BoardView(this);
    }

    /**
     *
     * @return this.spaces
     */
    public ModelSpace[][] getSpaces(){
        return spaces;
    }
}
