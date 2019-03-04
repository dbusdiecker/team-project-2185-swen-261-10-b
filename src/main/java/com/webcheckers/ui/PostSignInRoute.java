package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * The UI Controller to POST the sign in route
 */
public class PostSignInRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private static final Message ERROR_MSG = Message.error("Username is invalid or already in use.");
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    /**
     * 
     * @param playerLobby PlayerLobby to hold player list.
     * @param templateEngine The HTML template rendering engine.
     */
    public PostSignInRoute(final PlayerLobby playerLobby, final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        //
        LOG.config("PostSignInRoute is initialized.");
    }

    /**
     * Accept sign-ins from players
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     *
     * @return the rendered HTML for the SignIn page
     */
    public Object handle(Request request, Response response) {
        LOG.finer("PostSignInRoute is invoked.");

        final Session httpSession = request.session();
        Map<String, Object> vm = new HashMap<>();

        vm.put("title", "Sign in!");
        String username = request.queryParams("name");

        if( playerLobby.nameIsValid( username ) ){
            if ( playerLobby.usernameAlreadyInUse(username)){
                vm.put("message", ERROR_MSG);
                return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
            } else { //Username not in use
                Player player = new Player(username);
                playerLobby.addPlayer(player);
                httpSession.attribute(GetHomeRoute.PLAYER_ATTR, player); //Add the player to the user's session
                response.redirect(WebServer.HOME_URL);
                return null;
            }
        } else {  //Name is not valid, show error
            vm.put("message", ERROR_MSG);
            return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
        }
    }
}
