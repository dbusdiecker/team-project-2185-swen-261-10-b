package com.webcheckers.ui;

import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

@Tag("UI-Tier")
public class BoardViewTest {

    Player mockP1;
    Player mockP2;
    Board board;
    BoardView CuT;

    @BeforeEach
    public void setup(){
        mockP1 = mock(Player.class);
        mockP2 = mock(Player.class);
        board = new Board(mockP1, mockP2);
    }

    @Test
    public void ctor_redPlayer(){
        CuT = new BoardView(board, mockP1);

    }

}
