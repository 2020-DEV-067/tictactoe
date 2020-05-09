package com.game.tictactoe.service;

import com.game.tictactoe.Domain.Symbol;
import com.game.tictactoe.service.exception.InvalidCoordinateException;

public class GameService {
    private static final int BOARD_DIMENSION = 9;
    private Symbol[][] board = new Symbol[BOARD_DIMENSION][BOARD_DIMENSION];


    public void placeSymbol(Symbol symbol, int x, int y) {
        if (x >= BOARD_DIMENSION || y >= BOARD_DIMENSION) {
            throw new InvalidCoordinateException();
        }
        board[x][y] = symbol;
    }

    public Symbol[][] getBoard() {
        return board;
    }
}
