package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

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
    public void testAddGetPlayerNameInUse() {
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
    public void testUsernameValidity() {
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
}
