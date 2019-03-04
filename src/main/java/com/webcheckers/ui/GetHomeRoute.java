package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

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
  private final PlayerLobby playerLobby;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final PlayerLobby playerLobby, final TemplateEngine templateEngine) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");

    Map<String, Object> vm = new HashMap<>();

    final Session httpSession = request.session();
    final Player currentUser = httpSession.attribute(PLAYER_ATTR);
    if( currentUser != null){
      vm.put(PLAYER_ATTR, currentUser);
        if(currentUser.getCurrent_game() != null){
            String opponent;
            if(!(currentUser.getCurrent_game().getRedPlayer() == currentUser)){
                opponent = currentUser.getCurrent_game().getRedPlayer().getName();
            }
            else{
                opponent = currentUser.getCurrent_game().getWhitePlayer().getName();
            }
            String URL = WebServer.GAME_URL +"?player=" + opponent;
            response.redirect(URL);
        }
      vm.put("player_list", playerLobby.getOnlinePlayers()); // display online players to challenge
    } else {
      vm.put("num_online", playerLobby.getNumOnlinePlayers());
    }



    vm.put("title", "Welcome!");

    // display a user message in the Home page
    vm.put("message", WELCOME_MSG);


    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}
