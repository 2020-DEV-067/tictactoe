package com.game.tictactoe.service.exception;

public class InvalidCoordinateException extends RuntimeException {
    public InvalidCoordinateException() {
        super("The provided coordinate is not valid!");
    }
}
