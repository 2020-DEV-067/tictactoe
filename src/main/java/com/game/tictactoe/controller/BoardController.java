package com.game.tictactoe.controller;

import com.game.tictactoe.Domain.GameState;
import com.game.tictactoe.Domain.Position;
import com.game.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
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

    @POST
    @Path("/playTurn")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response playTurn(Position nextPosition) {
        try {
            gameService.playTurn(nextPosition);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
