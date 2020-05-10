package com.game.tictactoe.service;

import com.game.tictactoe.Domain.Symbol;
import com.game.tictactoe.service.exception.FieldIsAlreadyOccupiedException;
import com.game.tictactoe.service.exception.InvalidCoordinateException;
import org.apache.commons.lang3.ArrayUtils;

import static com.game.tictactoe.util.GameConstant.BOARD_DIMENSION;

public class GameService {
    private Symbol[][] board = new Symbol[BOARD_DIMENSION][BOARD_DIMENSION];


    public void placeSymbol(Symbol symbol, int x, int y) {
        if (x >= BOARD_DIMENSION || y >= BOARD_DIMENSION) {
            throw new InvalidCoordinateException();
        }
        if (board[x][y] != null) {
            throw new FieldIsAlreadyOccupiedException();
        }
        board[x][y] = symbol;
    }

    public boolean isBoardFull() {
        for (Symbol[] array : board) {
            if (ArrayUtils.contains(array, null)) {
                return false;
            }
        }
        return true;
    }

    public Symbol[][] getBoard() {
        return board;
    }

    public boolean hasSymbolWon(Symbol symbol, int lastXPosition, int lastYPosition) {
        int counter = 0;
        //horizontal scan
        for (int x = 0; x < BOARD_DIMENSION; x++) {
            if (board[x][lastYPosition] == symbol) counter++;
        }
        if (counter == 3) return true;
        //reset counter
        counter = 0;
        //vertical scan
        for (int y = 0; y < BOARD_DIMENSION; y++) {
            if (board[lastXPosition][y] == symbol) counter++;
        }
        return counter == 3;
    }
}
