package com.game.tictactoe.controller;

import com.game.tictactoe.domain.Board;
import com.game.tictactoe.domain.GameState;
import com.game.tictactoe.domain.PlayerSymbol;
import com.game.tictactoe.domain.Position;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.game.tictactoe.util.GameConstant.*;
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

    @Test
    public void shouldModifyGameState() {
        Board board = new Board();
        Position position = new Position(1, 1);
        board.addPlayerSymbol(PlayerSymbol.X, position);
        String message = String.format(NEXT_MESSAGE, PlayerSymbol.O);
        GameState expectedState = new GameState(board, message);

        target(PATH_PLAY_TURN).request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(position, MediaType.APPLICATION_JSON));
        Response response = target(PATH_STATE).request(MediaType.APPLICATION_JSON)
                .get();

        GameState actualState = response.readEntity(GameState.class);
        assertEquals(expectedState, actualState);
    }

    @Test
    public void shouldReturnInvalidPositionMessage() {
        String expectedMessage = INVALID_POSITION;
        Position position = new Position(5, 5);

        Response response = target(PATH_PLAY_TURN).request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(position, MediaType.APPLICATION_JSON));

        String actualMessage = response.readEntity(String.class);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldReturnPositionOccupiedMessage() {
        String expectedMessage = POSITION_IS_TAKEN;
        Position position = new Position(0, 0);

        target(PATH_PLAY_TURN).request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(position, MediaType.APPLICATION_JSON));
        //try with the same position again
        Response response = target(PATH_PLAY_TURN).request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(position, MediaType.APPLICATION_JSON));

        String actualMessage = response.readEntity(String.class);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldRestartGame() {
        GameState expectedState = new GameState(new Board(), START_MESSAGE);

        target(PATH_PLAY_TURN).request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(new Position(1, 1), MediaType.APPLICATION_JSON));
        target(PATH_RESTART).request().post(Entity.entity(null, MediaType.APPLICATION_JSON_TYPE));
        Response response = target(PATH_STATE).request(MediaType.APPLICATION_JSON)
                .get();

        GameState actualState = response.readEntity(GameState.class);
        assertEquals(expectedState, actualState);
    }
}
