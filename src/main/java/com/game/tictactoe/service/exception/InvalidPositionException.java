package com.game.tictactoe.service.exception;

import static com.game.tictactoe.util.GameConstant.INVALID_POSITION;

public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException() {
        super(INVALID_POSITION);
    }
}
