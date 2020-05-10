package com.game.tictactoe.service;

import com.game.tictactoe.Domain.Position;
import com.game.tictactoe.Domain.Symbol;
import com.game.tictactoe.service.exception.FieldIsAlreadyOccupiedException;
import com.game.tictactoe.service.exception.InvalidCoordinateException;
import org.apache.commons.lang3.ArrayUtils;

import static com.game.tictactoe.util.GameConstant.BOARD_DIMENSION;

public class GameService {
    private Symbol[][] board = new Symbol[BOARD_DIMENSION][BOARD_DIMENSION];


    public void placeSymbol(Symbol symbol, Position position) {
        if (isPositionInvalid(position)) {
            throw new InvalidCoordinateException();
        }
        if (isFieldOccupied(position)) {
            throw new FieldIsAlreadyOccupiedException();
        }
        board[position.getX()][position.getY()] = symbol;
    }

    private boolean isPositionInvalid(Position position) {
        return position.getX() > BOARD_DIMENSION - 1 || position.getY() > BOARD_DIMENSION - 1;
    }

    private boolean isFieldOccupied(Position position) {
        return board[position.getX()][position.getY()] != null;
    }

    public boolean isBoardFull() {
        for (Symbol[] rows : board) {
            if (ArrayUtils.contains(rows, null)) {
                return false;
            }
        }
        return true;
    }

    public Symbol[][] getBoard() {
        return board;
    }

    public boolean hasSymbolWon(Symbol symbol, Position lastPosition) {
        int horizontalCounter = 0, verticalCounter = 0, diagonalCounter = 0, antiDiagonalCounter = 0;

        for (int i = 0; i < BOARD_DIMENSION; i++) {
            //horizontal check
            if (board[i][lastPosition.getY()] == symbol) horizontalCounter++;
            //vertical check
            if (board[lastPosition.getX()][i] == symbol) verticalCounter++;
            //diagonal check
            if (board[i][i] == symbol) diagonalCounter++;
            //anti diagonal check
            if (board[i][BOARD_DIMENSION - i - 1] == symbol) antiDiagonalCounter++;
        }
        return horizontalCounter == BOARD_DIMENSION
                || verticalCounter == BOARD_DIMENSION
                || diagonalCounter == BOARD_DIMENSION
                || antiDiagonalCounter == BOARD_DIMENSION;
    }
}
