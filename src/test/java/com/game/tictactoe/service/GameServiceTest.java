package com.game.tictactoe.service;

import com.game.tictactoe.Domain.Board;
import com.game.tictactoe.Domain.GameState;
import com.game.tictactoe.Domain.Position;
import com.game.tictactoe.Domain.Symbol;
import com.game.tictactoe.service.exception.FieldIsAlreadyOccupiedException;
import com.game.tictactoe.service.exception.InvalidCoordinateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.game.tictactoe.util.GameConstant.BOARD_DIMENSION;
import static com.game.tictactoe.util.GameConstant.START_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    public void init() {
        gameService = new GameService();
    }

    @Test
    public void shouldPlaceSymbolXOnBoard() {
        Position dummyPosition = new Position(1, 1);
        gameService.placeSymbol(Symbol.X, dummyPosition);

        Board result = gameService.getBoard();
        assertEquals(Symbol.X, result.getSymbol(dummyPosition));
    }

    @Test
    public void shouldPlaceSymbolOOnBoard() {
        Position dummyPosition = new Position(2, 2);
        gameService.placeSymbol(Symbol.X, dummyPosition);

        Board result = gameService.getBoard();
        assertEquals(Symbol.X, result.getSymbol(dummyPosition));
    }

    @Test
    public void invalidXCoordinateShouldFail() {
        assertThrows(InvalidCoordinateException.class, () -> {
            gameService.placeSymbol(Symbol.X, new Position(13, 1));
        });
    }

    @Test
    public void invalidYCoordinateShouldFail() {
        assertThrows(InvalidCoordinateException.class, () -> {
            gameService.placeSymbol(Symbol.X, new Position(0, 9));
        });
    }

    @Test
    public void shouldNotPlaceSymbolOnAnotherOne() {
        assertThrows(FieldIsAlreadyOccupiedException.class, () -> {
            Position dummyPosition = new Position(1, 1);
            gameService.placeSymbol(Symbol.X, dummyPosition);
            gameService.placeSymbol(Symbol.X, dummyPosition);
        });
    }

    @Test
    public void boardShouldNotBeFull() {
        boolean result = gameService.isBoardFull();
        assertFalse(result);
    }

    @Test
    public void boardShouldBeFull() {
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            for (int j = 0; j < BOARD_DIMENSION; j++) {
                gameService.placeSymbol(Symbol.X, new Position(i, j));
            }
        }

        boolean result = gameService.isBoardFull();
        assertTrue(result);
    }

    @Test
    public void shouldDetectWinnerHorizontally() {
        //initial state
        gameService.placeSymbol(Symbol.X, new Position(0, 0));
        gameService.placeSymbol(Symbol.X, new Position(1, 0));
        //last added position
        Position lastPosition = new Position(2, 0);
        gameService.placeSymbol(Symbol.X, lastPosition);

        boolean result = gameService.hasSymbolWon(Symbol.X, lastPosition);
        assertTrue(result);
    }

    @Test
    public void shouldNotDetectWinnerHorizontally() {
        //initial state
        gameService.placeSymbol(Symbol.X, new Position(0, 0));
        gameService.placeSymbol(Symbol.O, new Position(1, 0));
        //last added position
        Position lastPosition = new Position(2, 0);
        gameService.placeSymbol(Symbol.X, lastPosition);

        boolean result = gameService.hasSymbolWon(Symbol.X, lastPosition);
        assertFalse(result);
    }

    @Test
    public void shouldDetectWinnerVertically() {
        //initial state
        gameService.placeSymbol(Symbol.X, new Position(1, 0));
        gameService.placeSymbol(Symbol.X, new Position(1, 1));

        //last added position
        Position lastPosition = new Position(1, 2);
        gameService.placeSymbol(Symbol.X, lastPosition);

        boolean result = gameService.hasSymbolWon(Symbol.X, lastPosition);
        assertTrue(result);
    }

    @Test
    public void shouldNotDetectWinnerVertically() {
        //initial state
        gameService.placeSymbol(Symbol.X, new Position(1, 0));
        gameService.placeSymbol(Symbol.X, new Position(1, 1));

        //last added position
        Position lastPosition = new Position(1, 2);
        gameService.placeSymbol(Symbol.O, lastPosition);

        boolean result = gameService.hasSymbolWon(Symbol.X, lastPosition);
        assertFalse(result);
    }

    @Test
    public void shouldDetectWinnerDiagonally() {
        //initial state
        gameService.placeSymbol(Symbol.X, new Position(0, 0));
        gameService.placeSymbol(Symbol.X, new Position(1, 1));

        //last added position
        Position lastPosition = new Position(2, 2);
        gameService.placeSymbol(Symbol.X, lastPosition);

        boolean result = gameService.hasSymbolWon(Symbol.X, lastPosition);
        assertTrue(result);
    }

    @Test
    public void shouldNotDetectWinnerDiagonally() {
        //initial state
        gameService.placeSymbol(Symbol.X, new Position(0, 0));

        //last added position
        Position lastPosition = new Position(2, 2);
        gameService.placeSymbol(Symbol.X, lastPosition);

        boolean result = gameService.hasSymbolWon(Symbol.X, lastPosition);
        assertFalse(result);
    }

    @Test
    public void shouldDetectWinnerAntiDiagonally() {
        //initial state
        gameService.placeSymbol(Symbol.X, new Position(0, 2));
        gameService.placeSymbol(Symbol.X, new Position(1, 1));

        //last added position
        Position lastPosition = new Position(2, 0);
        gameService.placeSymbol(Symbol.X, lastPosition);

        boolean result = gameService.hasSymbolWon(Symbol.X, lastPosition);
        assertTrue(result);
    }

    @Test
    public void shouldNotDetectWinnerAntiDiagonally() {
        //initial state
        gameService.placeSymbol(Symbol.X, new Position(0, 2));

        //last added position
        Position lastPosition = new Position(1, 1);
        gameService.placeSymbol(Symbol.X, lastPosition);

        boolean result = gameService.hasSymbolWon(Symbol.X, lastPosition);
        assertFalse(result);
    }

    @Test
    public void shouldReturnInitialState() {
        GameState expectedState = new GameState(new Board(), START_MESSAGE);
        GameState actualState = gameService.getCurrentState();

        assertEquals(expectedState, actualState);
    }
}
