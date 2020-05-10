package com.game.tictactoe.service.exception;

import static com.game.tictactoe.util.GameConstant.INVALID_POSITION;

public class InvalidCoordinateException extends RuntimeException {
    public InvalidCoordinateException() {
        super(INVALID_POSITION);
    }
}
