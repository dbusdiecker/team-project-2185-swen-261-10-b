package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-Tier")
public class GetSpectatorGameRouteTest {

    private Request request;
    private Response response;
    private Session session;
    private GetSpectatorGameRoute CuT;
    private GameCenter gameCenter;
    private Gson gson;
    private TemplateEngine templateEngine;


    private Player playerOne;
    private Player playerTwo;
    private Player playerThree;

    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        templateEngine = mock(TemplateEngine.class);
        gameCenter = new GameCenter();
        response = mock(Response.class);
        gson = new Gson();
        playerOne = new Player("PLAYER1");
        playerTwo = new Player("PLAYER2");
        playerThree = new Player("PLAYER3");
        when(request.session()).thenReturn(session);
        CuT = new GetSpectatorGameRoute(gameCenter, gson, templateEngine);
    }

    @Test
    public void get_game_success(){

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(session.attribute(GetHomeRoute.PLAYER_ATTR)).thenReturn(playerOne);
        when(request.queryParams("gameID")).thenReturn("0");

        Integer gameID = gameCenter.createGame(playerOne, playerTwo);
        CheckersGame game = gameCenter.getGameByID(gameID);

        CuT.handle(request,response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute("redPlayer", playerOne);
        testHelper.assertViewModelAttribute("whitePlayer", playerTwo);
        testHelper.assertViewModelAttribute("activeColor",CheckersGame.activeColor.RED);
        testHelper.assertViewModelAttribute("board", game.getBoard().getBoardView(playerOne));
        testHelper.assertViewModelAttribute("viewMode", GetGameRoute.viewMode.SPECTATOR);
        testHelper.assertViewModelAttribute("title", "Let's Play Checkers!");
    }


    @Test
    public void game_ID_is_null(){
        when(request.queryParams("gameID")).thenReturn(null);
        assertNull(CuT.handle(request, response));
    }

    @Test
    public void game_doesnt_exist(){
        when(request.queryParams("gameID")).thenReturn("0");
        assertNull(CuT.handle(request, response));
    }

}
