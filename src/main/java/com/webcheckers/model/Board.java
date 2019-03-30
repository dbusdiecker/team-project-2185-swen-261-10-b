package com.webcheckers.model;

import com.webcheckers.ui.BoardView;

/**
 * Object for the model of a checkers board
 */
public class Board {

    private final int BOARD_SIZE = 8;

    private ModelSpace spaces[][];

    private Player redPlayer;

    private Player whitePlayer;

    private BoardView redBoardView;

    private BoardView whiteBoardView;

    public Player getRedPlayer() {
        return redPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * Gets the board view for the player's side.
     *
     * @param player Player object to get color value from.
     * @return Red board view for red player, white for a white player.
     */
    public BoardView getBoardView(Player player) {
        if (player.equals(redPlayer)){
            return redBoardView;
        }
        else{
            return whiteBoardView;
        }
    }

    public Board(Board board){
        this.redPlayer = board.redPlayer;
        this.whitePlayer = board.whitePlayer;

        this.spaces = new ModelSpace[BOARD_SIZE][BOARD_SIZE];
        ModelSpace[][] otherSpaces = board.getSpaces();
        for (int row = 0; row < BOARD_SIZE; row++){
            for ( int col = 0; col < BOARD_SIZE; col++ ){
                this.spaces[row][col] = otherSpaces[row][col];
            }
        }

        this.redBoardView = new BoardView(this, redPlayer);
        this.whiteBoardView = new BoardView(this, whitePlayer);
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
        this.spaces = new ModelSpace[BOARD_SIZE][BOARD_SIZE];
        ModelSpace.spaceColor currentColor = ModelSpace.spaceColor.LIGHT;
        for (int row = 0; row < BOARD_SIZE; row++){
            for (int col = 0; col < BOARD_SIZE; col++){
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
        whiteBoardView = new BoardView(this, whitePlayer);
        redBoardView = new BoardView(this, redPlayer);
    }

    /**
     *
     * @return this.spaces
     */
    public ModelSpace[][] getSpaces(){
        return spaces;
    }

    /**
     * Moves a piece from one space to another
     *
     * @param startRow row of starting space
     * @param startCol column of starting space
     * @param endRow row of ending space
     * @param endCol column of ending space
     */
    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        ModelPiece piece = spaces[startRow][startCol].getPiece();
        spaces[endRow][endCol].addPiece(piece);
        spaces[startRow][startCol].removePiece();
        this.redBoardView = new BoardView(this, redPlayer);
        this.whiteBoardView = new BoardView(this, whitePlayer);
    }
}
