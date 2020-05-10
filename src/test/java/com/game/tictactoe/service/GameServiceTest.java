package com.game.tictactoe.service;

import com.game.tictactoe.Domain.Symbol;
import com.game.tictactoe.service.exception.FieldIsAlreadyOccupiedException;
import com.game.tictactoe.service.exception.InvalidCoordinateException;
import org.junit.jupiter.api.Test;

import static com.game.tictactoe.util.GameConstant.BOARD_DIMENSION;
import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {
    @Test
    public void shouldPlaceSymbolXOnBoard() {
        GameService gameService = new GameService();
        gameService.placeSymbol(Symbol.X, 1, 1);

        Symbol[][] result = gameService.getBoard();
        assertEquals(Symbol.X, result[1][1]);
    }

    @Test
    public void shouldPlaceSymbolOOnBoard() {
        GameService gameService = new GameService();
        gameService.placeSymbol(Symbol.X, 2, 2);

        Symbol[][] result = gameService.getBoard();
        assertEquals(Symbol.X, result[2][2]);
    }

    @Test
    public void invalidXCoordinateShouldFail() {
        assertThrows(InvalidCoordinateException.class, () -> {
            GameService gameService = new GameService();
            gameService.placeSymbol(Symbol.X, 13, 1);
        });
    }

    @Test
    public void invalidYCoordinateShouldFail() {
        assertThrows(InvalidCoordinateException.class, () -> {
            GameService gameService = new GameService();
            gameService.placeSymbol(Symbol.X, 0, 9);
        });
    }

    @Test
    public void shouldNotPlaceSymbolOnAnotherOne() {
        assertThrows(FieldIsAlreadyOccupiedException.class, () -> {
            GameService gameService = new GameService();
            gameService.placeSymbol(Symbol.X, 1, 1);
            gameService.placeSymbol(Symbol.X, 1, 1);
        });
    }

    @Test
    public void boardShouldNotBeFull() {
        GameService gameService = new GameService();

        boolean result = gameService.isBoardFull();
        assertFalse(result);
    }

    @Test
    public void boardShouldBeFull() {
        GameService gameService = new GameService();
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            for (int j = 0; j < BOARD_DIMENSION; j++) {
                gameService.placeSymbol(Symbol.X, i, j);
            }
        }

        boolean result = gameService.isBoardFull();
        assertTrue(result);
    }

    @Test
    public void shouldDetectWinnerHorizontally() {
        //initial state
        GameService gameService = new GameService();
        gameService.placeSymbol(Symbol.X, 0, 0);
        gameService.placeSymbol(Symbol.X, 1, 0);
        //last added position
        gameService.placeSymbol(Symbol.X, 2, 0);

        boolean result = gameService.hasSymbolWon(Symbol.X, 2, 0);
        assertTrue(result);
    }

    @Test
    public void shouldNotDetectWinnerHorizontally() {
        //initial state
        GameService gameService = new GameService();
        gameService.placeSymbol(Symbol.X, 0, 0);
        gameService.placeSymbol(Symbol.O, 1, 0);
        //last added position
        gameService.placeSymbol(Symbol.X, 2, 0);

        boolean result = gameService.hasSymbolWon(Symbol.X, 2, 0);
        assertFalse(result);
    }

    @Test
    public void shouldDetectWinnerVertically() {
        //initial state
        GameService gameService = new GameService();
        gameService.placeSymbol(Symbol.X, 1, 0);
        gameService.placeSymbol(Symbol.X, 1, 1);

        //last added position
        gameService.placeSymbol(Symbol.X, 1, 2);

        boolean result = gameService.hasSymbolWon(Symbol.X, 1, 2);
        assertTrue(result);
    }

    @Test
    public void shouldNotDetectWinnerVertically() {
        //initial state
        GameService gameService = new GameService();
        gameService.placeSymbol(Symbol.X, 1, 0);
        gameService.placeSymbol(Symbol.X, 1, 1);

        //last added position
        gameService.placeSymbol(Symbol.O, 1, 2);

        boolean result = gameService.hasSymbolWon(Symbol.X, 1, 2);
        assertFalse(result);
    }

    @Test
    public void shouldDetectWinnerDiagonally() {
        //initial state
        GameService gameService = new GameService();
        gameService.placeSymbol(Symbol.X, 0, 0);
        gameService.placeSymbol(Symbol.X, 1, 1);

        //last added position
        gameService.placeSymbol(Symbol.X, 2, 2);

        boolean result = gameService.hasSymbolWon(Symbol.X, 2, 2);
        assertTrue(result);
    }

    @Test
    public void shouldNotDetectWinnerDiagonally() {
        //initial state
        GameService gameService = new GameService();
        gameService.placeSymbol(Symbol.X, 0, 0);

        //last added position
        gameService.placeSymbol(Symbol.X, 2, 2);

        boolean result = gameService.hasSymbolWon(Symbol.X, 2, 2);
        assertFalse(result);
    }

    @Test
    public void shouldDetectWinnerAntiDiagonally() {
        //initial state
        GameService gameService = new GameService();
        gameService.placeSymbol(Symbol.X, 0, 2);
        gameService.placeSymbol(Symbol.X, 1, 1);

        //last added position
        gameService.placeSymbol(Symbol.X, 2, 0);

        boolean result = gameService.hasSymbolWon(Symbol.X, 2, 0);
        assertTrue(result);
    }

    @Test
    public void shouldNotDetectWinnerAntiDiagonally() {
        //initial state
        GameService gameService = new GameService();
        gameService.placeSymbol(Symbol.X, 0, 2);

        //last added position
        gameService.placeSymbol(Symbol.X, 1, 1);

        boolean result = gameService.hasSymbolWon(Symbol.X, 1, 1);
        assertFalse(result);
    }
}
