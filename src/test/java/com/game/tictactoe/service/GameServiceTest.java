package com.game.tictactoe.service;

import com.game.tictactoe.Domain.Symbol;
import com.game.tictactoe.service.exception.InvalidCoordinateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameServiceTest {
    @Test
    public void shouldPlaceSymbolXOnBoard() {
        GameService gameService = new GameService();
        gameService.placeSymbol(Symbol.X, 5, 5);

        Symbol[][] result = gameService.getBoard();
        assertEquals(Symbol.X, result[5][5]);
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
            gameService.placeSymbol(Symbol.X, 13, 5);
        });
    }

    @Test
    public void invalidYCoordinateShouldFail() {
        assertThrows(InvalidCoordinateException.class, () -> {
            GameService gameService = new GameService();
            gameService.placeSymbol(Symbol.X, 3, 9);
        });
    }
}
