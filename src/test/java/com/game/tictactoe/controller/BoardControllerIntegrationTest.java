package com.game.tictactoe.controller;

import com.game.tictactoe.Domain.Board;
import com.game.tictactoe.Domain.GameState;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.game.tictactoe.util.GameConstant.PATH_STATE;
import static com.game.tictactoe.util.GameConstant.START_MESSAGE;
import static org.junit.Assert.assertEquals;

public class BoardControllerIntegrationTest extends JerseyTest {

    @Override
    protected Application configure() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.game.tictactoe");
        context.refresh();
        return new ResourceConfig(BoardController.class).property("contextConfig", context);
    }

    @Test
    public void shouldReturnCurrentGameState() {
        Board board = new Board();
        String message = START_MESSAGE;
        GameState expectedState = new GameState(board, message);
        Response response = target(PATH_STATE).request(MediaType.APPLICATION_JSON)
                .get();

        GameState actualState = response.readEntity(GameState.class);
        assertEquals(expectedState, actualState);
    }
}
