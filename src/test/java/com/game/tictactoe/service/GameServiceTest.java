package com.game.tictactoe.service;

import com.game.tictactoe.Domain.Position;
import com.game.tictactoe.Domain.Symbol;
import com.game.tictactoe.service.exception.FieldIsAlreadyOccupiedException;
import com.game.tictactoe.service.exception.InvalidCoordinateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.game.tictactoe.util.GameConstant.BOARD_DIMENSION;
import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    public void init() {
        gameService = new GameService();
    }

    @Test
    public void shouldPlaceSymbolXOnBoard() {
        gameService.placeSymbol(Symbol.X, new Position(1, 1));

        Symbol[][] result = gameService.getBoard();
        assertEquals(Symbol.X, result[1][1]);
    }

    @Test
    public void shouldPlaceSymbolOOnBoard() {
        gameService.placeSymbol(Symbol.X, new Position(2, 2));

        Symbol[][] result = gameService.getBoard();
        assertEquals(Symbol.X, result[2][2]);
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
}
