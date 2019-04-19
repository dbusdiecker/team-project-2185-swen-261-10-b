package com.webcheckers.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class PlayerLobbyTest {
    final PlayerLobby CuT = new PlayerLobby();
    final String invalidNameBlank = "";
    final String invalidNameWhitespace = " ";
    final String invalidNameSpecialChar = "Gary_Checkers";
    final String validNameAlphabetical = "Gary";
    final String validNameSpace = "Gary Checkers";
    final String validNameNumber = "G4RY";
    final String validNameSpaceAlphabeticalAndNumber = "Gary Checkers 261";

    final String falseUsername = "Not Gary";

    @Test
    public void test_add_get_player_name_in_use() {
        Player dummyPlayer = new Player("Gary");
        String playerName = dummyPlayer.getName();
        CuT.addPlayer(dummyPlayer);

        assertEquals(dummyPlayer, CuT.getPlayerByUsername(dummyPlayer.getName()),
                "Added player was not returned when getPlayerByUsername was called.");
        assertTrue(CuT.usernameAlreadyInUse(playerName),
                "Added player's name was not registered as in use.");
        assertFalse(CuT.usernameAlreadyInUse(falseUsername),
                "Unused name was registered as in use.");
    }

    @Test
    public void test_username_validity() {
        assertFalse(CuT.nameIsValid(invalidNameBlank),
                "Blank name was accepted as valid when it should not have been.");
        assertFalse(CuT.nameIsValid(invalidNameWhitespace),
                "Name containing only whitespace was accepted as valid when it should not have been.");
        assertFalse(CuT.nameIsValid(invalidNameSpecialChar),
                "Name containing a special character was accepted as valid when it should not have been.");
        assertTrue(CuT.nameIsValid(validNameAlphabetical),
                "Name containing only letters was not accepted as valid when it should have been.");
        assertTrue(CuT.nameIsValid(validNameSpace),
                "Name containing a space was not accepted as valid when it should have been.");
        assertTrue(CuT.nameIsValid(validNameNumber),
                "Name containing a number was not accepted as valid when it should have been.");
        assertTrue(CuT.nameIsValid(validNameSpaceAlphabeticalAndNumber),
                "Name a space, letters and numbers was not accepted as valid when it should have been.");
    }

    @Test
    public void test_player_manipulation(){
        Player dummyPlayer = new Player("Gary");
        Player dummyPlayer2 = new Player("Garrie");
        CuT.addPlayer(dummyPlayer);
        CuT.addPlayer(dummyPlayer2);
        assertEquals(CuT.getPlayerByUsername(dummyPlayer2.getName()), dummyPlayer2);
        assertEquals(CuT.getNumOnlinePlayers(),2);
        CuT.removePlayer(dummyPlayer2);
        assertNull(CuT.getPlayerByUsername(dummyPlayer2.getName()));
        assertEquals(CuT.getNumOnlinePlayers(),1);

        CuT.removePlayer(dummyPlayer);


        Player mockPlayer = mock(Player.class);
        Player mockPlayer2 = mock(Player.class);
        Player mockPlayer3 = mock(Player.class);

        CuT.addPlayer(mockPlayer);
        CuT.addPlayer(mockPlayer2);
        CuT.addPlayer(mockPlayer3);

        when(mockPlayer.getWinRate()).thenReturn(70.0);
        when(mockPlayer2.getWinRate()).thenReturn(85.0);
        when(mockPlayer3.getWinRate()).thenReturn(0.0);


        ArrayList<Player> result = new ArrayList<>();
        result.add(mockPlayer3);
        result.add(mockPlayer);
        result.add(mockPlayer2);

        assertIterableEquals(result,CuT.sortPlayersByWinRate());
    }


}
