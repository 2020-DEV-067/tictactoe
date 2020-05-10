package com.game.tictactoe.controller;

import com.game.tictactoe.Domain.GameState;
import com.game.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/board")
@Controller
public class BoardController {

    @Autowired
    private GameService gameService;

    @GET
    @Path("/state")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameState() {
        GameState currentState = gameService.getCurrentState();
        return Response.status(Response.Status.OK).entity(currentState).build();
    }
}
