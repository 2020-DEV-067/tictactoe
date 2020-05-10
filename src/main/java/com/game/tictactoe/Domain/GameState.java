package com.game.tictactoe.Domain;

import lombok.Data;

import static com.game.tictactoe.util.GameConstant.START_MESSAGE;

@Data
public class GameState {

    private Board board;

    private String message;

    public GameState(Board board, String message) {
        this.board = board;
        this.message = message;
    }

    public GameState() {
        this.board = new Board();
        this.message = START_MESSAGE;
    }
}
