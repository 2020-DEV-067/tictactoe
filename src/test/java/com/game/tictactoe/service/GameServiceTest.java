package com.game.tictactoe.service;

import com.game.tictactoe.Domain.Symbol;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
