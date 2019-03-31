package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-tier")
public class GetGameRouteTest {

    private GetGameRoute CuT;

    private Request request;
    private Session session;
    private TemplateEngine engine;
    private GameCenter gameCenter;
    private Gson gson;
    private Response response;

    private Player playerOne;
    private Player playerTwo;
    private Player playerThree;


    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        engine = mock(TemplateEngine.class);

        gameCenter = new GameCenter();

        response = mock(Response.class);
        gson = mock(Gson.class);

        //Player is a trusted class
        playerOne = new Player("PLAYER1");
        playerTwo = new Player("PLAYER2");
        playerThree = new Player("PLAYER3");

        when(request.session()).thenReturn(session);

        CuT = new GetGameRoute(gameCenter, gson, engine);
    }

    @Test
    public void getGameSuccess(){

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(session.attribute(GetHomeRoute.PLAYER_ATTR)).thenReturn(playerOne);
        when(request.queryParams("gameID")).thenReturn("0");

        Integer gameID = gameCenter.createGame(playerOne, playerTwo);
        CheckersGame game = gameCenter.getGameByID(gameID);

        CuT.handle(request,response);

        // Ensure getting the game is loaded properly for playerOne
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute("redPlayer", playerOne);
        testHelper.assertViewModelAttribute("whitePlayer", playerTwo);
        testHelper.assertViewModelAttribute("activeColor",CheckersGame.activeColor.RED);
        testHelper.assertViewModelAttribute("board", game.getBoard().getBoardView(playerOne));
        testHelper.assertViewModelAttribute("viewMode", GetGameRoute.viewMode.PLAY);
        testHelper.assertViewModelAttribute("title", "Let's Play Checkers!");


        when(session.attribute(GetHomeRoute.PLAYER_ATTR)).thenReturn(playerTwo);
        CuT.handle(request,response);

        // Ensure the view was successfully flipped to show playerTwo's perspective
        // All other assertions are the same
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute("redPlayer", playerOne);
        testHelper.assertViewModelAttribute("whitePlayer", playerTwo);
        testHelper.assertViewModelAttribute("activeColor",CheckersGame.activeColor.RED);
        testHelper.assertViewModelAttribute("board", game.getBoard().getBoardView(playerTwo));
        testHelper.assertViewModelAttribute("viewMode", GetGameRoute.viewMode.PLAY);
        testHelper.assertViewModelAttribute("title", "Let's Play Checkers!");
    }

    @Test
    public void gameIDIsNull(){
        when(request.queryParams("gameID")).thenReturn(null);
        assertNull(CuT.handle(request, response));
    }

    @Test
    public void gameDoesntExist(){
        when(request.queryParams("gameID")).thenReturn("0");
        assertNull(CuT.handle(request, response));
    }

    @Test
    public void playerNotLoggedIn(){
        when(request.queryParams("gameID")).thenReturn("0");
        when(session.attribute(GetHomeRoute.PLAYER_ATTR)).thenReturn(null);

        gameCenter.createGame(playerOne, playerTwo);

        assertNull(CuT.handle(request, response));
    }

    @Test
    public void playerNotPartOfGame(){
        when(request.queryParams("gameID")).thenReturn("0");
        when(session.attribute(GetHomeRoute.PLAYER_ATTR)).thenReturn(playerThree);

        gameCenter.createGame(playerOne, playerTwo);

        assertNull(CuT.handle(request, response));
    }
}
