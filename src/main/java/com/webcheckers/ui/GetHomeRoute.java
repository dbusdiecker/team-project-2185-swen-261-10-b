package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
  static final String PLAYER_ATTR = "currentUser";

  private final TemplateEngine templateEngine;
  private final GameCenter gameCenter;
  private final PlayerLobby playerLobby;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine The HTML template rendering engine.
   */
  public GetHomeRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
    this.gameCenter = Objects.requireNonNull(gameCenter, "playerLobby is required");
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request The HTTP request.
   * @param response The HTTP response.
   *
   * @return The rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");

    Map<String, Object> vm = new HashMap<>();

    final Session httpSession = request.session();
    final Player currentUser = httpSession.attribute(PLAYER_ATTR);
    if( currentUser != null){
        //Integer gameId = gameCenter.getIDByPlayer(currentUser);
        /*
        if (gameId != null){
            String URL = String.format(WebServer.GAME_WITH_ID_URL, gameId);
            response.redirect(URL);
            return null;
        }
        */
        vm.put("opponent_list", currentUser.getCurrentOpponents());

        vm.put(PLAYER_ATTR, currentUser);
        vm.put("player_list", playerLobby.getOnlinePlayers()); // display online players to challenge
        vm.put("game_list", gameCenter.getCurrentGames());
    } else {
      vm.put("num_online", playerLobby.getNumOnlinePlayers());
    }



    vm.put("title", "Welcome!");

    // display a user message in the Home page
    if(httpSession.attribute("message") != null){
        vm.put("message", httpSession.attribute("message"));
        httpSession.removeAttribute("message");
    }
    else{
        vm.put("message", WELCOME_MSG);
    }


    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}
