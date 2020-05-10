package com.game.tictactoe.service;

import com.game.tictactoe.domain.Board;
import com.game.tictactoe.domain.GameState;
import com.game.tictactoe.domain.PlayerSymbol;
import com.game.tictactoe.domain.Position;
import com.game.tictactoe.service.exception.PositionIsAlreadyOccupiedException;
import com.game.tictactoe.service.exception.InvalidCoordinateException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import static com.game.tictactoe.util.GameConstant.*;

@Service
public class GameService {
    @Getter
    @Setter
    private Board board = new Board();
    private GameState currentState = new GameState();
    private PlayerSymbol currentPlayerSymbol = PlayerSymbol.X;
    private boolean isGameActive = true;


    public void playTurn(Position position) {
        if(isGameActive) {
            addSymbolToBoard(currentPlayerSymbol, position);
            evaluateNewState(currentPlayerSymbol, position);
        }
    }

    private void evaluateNewState(PlayerSymbol playerSymbol, Position position) {
        String message;
        if (hasCurrentPlayerWon(playerSymbol, position)) {
            isGameActive = false;
            message = String.format(WIN_MESSAGE, playerSymbol);
        } else if (isBoardFull()) {
            isGameActive = false;
            message = DRAW_MESSAGE;
        } else {
            currentPlayerSymbol = getNextPlayerSymbol();
            message = String.format(NEXT_MESSAGE, currentPlayerSymbol);
        }
        currentState = new GameState(board, message);
    }

    public void addSymbolToBoard(PlayerSymbol symbol, Position position) {
        if (isPositionInvalid(position)) {
            throw new InvalidCoordinateException();
        }
        if (isFieldOccupied(position)) {
            throw new PositionIsAlreadyOccupiedException();
        }
        board.addPlayerSymbol(symbol, position);
    }

    private boolean isPositionInvalid(Position position) {
        return position.getX() > BOARD_DIMENSION - 1 || position.getY() > BOARD_DIMENSION - 1;
    }

    private boolean isFieldOccupied(Position position) {
        return board.getPlayerSymbol(position) != null;
    }

    private PlayerSymbol getNextPlayerSymbol() {
        return currentPlayerSymbol == PlayerSymbol.X ? PlayerSymbol.O : PlayerSymbol.X;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            for (int j = 0; j < BOARD_DIMENSION; j++) {
                if (board.getPlayerSymbol(new Position(i, j)) == null) return false;
            }
        }
        return true;
    }

    public boolean hasCurrentPlayerWon(PlayerSymbol symbol, Position lastPosition) {
        int horizontalCounter = 0, verticalCounter = 0, diagonalCounter = 0, antiDiagonalCounter = 0;

        for (int i = 0; i < BOARD_DIMENSION; i++) {
            //horizontal check
            if (board.getPlayerSymbol(new Position(i, lastPosition.getY())) == symbol) horizontalCounter++;
            //vertical check
            if (board.getPlayerSymbol(new Position(lastPosition.getX(), i)) == symbol) verticalCounter++;
            //diagonal check
            if (board.getPlayerSymbol(new Position(i, i)) == symbol) diagonalCounter++;
            //anti diagonal check
            if (board.getPlayerSymbol(new Position(i, BOARD_DIMENSION - i - 1)) == symbol) antiDiagonalCounter++;
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
