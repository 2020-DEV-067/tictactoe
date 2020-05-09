package com.game.tictactoe.service.exception;

public class FieldIsAlreadyOccupiedException extends RuntimeException {
    public FieldIsAlreadyOccupiedException() {
        super("Selected field is already taken!");
    }
}
