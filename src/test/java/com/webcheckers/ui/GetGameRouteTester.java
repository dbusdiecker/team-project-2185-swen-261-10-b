package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Tag("UI-tier")
public class GetGameRouteTester {

    private GetGameRoute CuT;

    private Request request;
    private Session session;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private Response response;

    private Player playerServices1;
    private Player playerServices2;


    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        engine = mock(TemplateEngine.class);
        playerLobby = new PlayerLobby();
        response = mock(Response.class);

        //Player is a trusted class
        playerServices1 = new Player("PLAYER1");
        playerServices2 = new Player("PLAYER2");


        when(request.session()).thenReturn(session);

        when(session.attribute(GetHomeRoute.PLAYER_ATTR)).thenReturn(playerServices1);

        when(request.queryParams(eq("player"))).thenReturn(playerServices2.getName());


        CuT = new GetGameRoute(playerLobby,engine);
    }

    @Test
    public void create_new_game_success(){

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        playerLobby.addPlayer(playerServices1);
        playerLobby.addPlayer(playerServices2);

        CuT.handle(request,response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute("redPlayer",playerServices1);
        testHelper.assertViewModelAttribute("whitePlayer", playerServices2);
        testHelper.assertViewModelAttribute("activeColor",CheckersGame.activeColor.RED);
        testHelper.assertViewModelAttribute("board", playerServices1.getCurrent_game().getBoard().getBoardView(playerServices1));
        testHelper.assertViewModelAttribute("viewMode", GetGameRoute.viewMode.PLAY);

    }











}
