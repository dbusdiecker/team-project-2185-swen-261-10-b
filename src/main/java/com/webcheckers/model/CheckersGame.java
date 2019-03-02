package com.webcheckers.model;

import com.webcheckers.ui.BoardView;
import com.webcheckers.util.Message;

import java.util.Map;

public class CheckersGame{

    private enum viewMode{
        PLAY,
        SPECTATOR,
        REPLAY
    }
    private enum ActiveColor{
        RED,
        WHITE
    }
    private Player redPlayer;
    private Player whitePlayer;
    private Player currentUser;
    private Map<String,Object> modeOptionsAsJSON;
    BoardView board;
    private Message message;



}
