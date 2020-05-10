package com.game.tictactoe.service;

import com.game.tictactoe.Domain.Board;
import com.game.tictactoe.Domain.GameState;
import com.game.tictactoe.Domain.Position;
import com.game.tictactoe.Domain.Symbol;
import com.game.tictactoe.service.exception.FieldIsAlreadyOccupiedException;
import com.game.tictactoe.service.exception.InvalidCoordinateException;
import lombok.Getter;
import lombok.Setter;

import static com.game.tictactoe.util.GameConstant.BOARD_DIMENSION;

public class GameService {
    @Getter
    @Setter
    private Board board = new Board();
    private GameState currentState = new GameState();
    private Symbol currentSymbol = Symbol.X;


    public void playTurn(Position position) {
        placeSymbol(currentSymbol, position);
        currentSymbol = getNextSymbol();
    }

    public void placeSymbol(Symbol symbol, Position position) {
        if (isPositionInvalid(position)) {
            throw new InvalidCoordinateException();
        }
        if (isFieldOccupied(position)) {
            throw new FieldIsAlreadyOccupiedException();
        }
        board.addSymbol(symbol, position);
    }

    private boolean isPositionInvalid(Position position) {
        return position.getX() > BOARD_DIMENSION - 1 || position.getY() > BOARD_DIMENSION - 1;
    }

    private boolean isFieldOccupied(Position position) {
        return board.getSymbol(position) != null;
    }

    private Symbol getNextSymbol() {
        return currentSymbol == Symbol.X ? Symbol.O : Symbol.X;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            for (int j = 0; j < BOARD_DIMENSION; j++) {
                if (board.getSymbol(new Position(i, j)) == null) return false;
            }
        }
        return true;
    }

    public boolean hasSymbolWon(Symbol symbol, Position lastPosition) {
        int horizontalCounter = 0, verticalCounter = 0, diagonalCounter = 0, antiDiagonalCounter = 0;

        for (int i = 0; i < BOARD_DIMENSION; i++) {
            //horizontal check
            if (board.getSymbol(new Position(i, lastPosition.getY())) == symbol) horizontalCounter++;
            //vertical check
            if (board.getSymbol(new Position(lastPosition.getX(), i)) == symbol) verticalCounter++;
            //diagonal check
            if (board.getSymbol(new Position(i, i)) == symbol) diagonalCounter++;
            //anti diagonal check
            if (board.getSymbol(new Position(i, BOARD_DIMENSION - i - 1)) == symbol) antiDiagonalCounter++;
        }
        return horizontalCounter == BOARD_DIMENSION
                || verticalCounter == BOARD_DIMENSION
                || diagonalCounter == BOARD_DIMENSION
                || antiDiagonalCounter == BOARD_DIMENSION;
    }

    public GameState getCurrentState() {
        return currentState;
    }
}
