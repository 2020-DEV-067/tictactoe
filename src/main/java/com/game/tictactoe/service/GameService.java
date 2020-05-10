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
        int horizontalCounter = 0, verticalCounter = 0, diagonalCounter = 0, antiDiagonalCounter = 0;

        for (int i = 0; i < BOARD_DIMENSION; i++) {
            //horizontal check
            if (board[i][lastYPosition] == symbol) horizontalCounter++;
            //vertical check
            if (board[lastXPosition][i] == symbol) verticalCounter++;
            //diagonal check
            if (board[i][i] == symbol) diagonalCounter++;
            //anti diagonal check
            if (board[i][BOARD_DIMENSION - i - 1] == symbol) antiDiagonalCounter++;
        }
        return horizontalCounter == 3 || verticalCounter == 3 || diagonalCounter == 3 || antiDiagonalCounter == 3;
    }
}
