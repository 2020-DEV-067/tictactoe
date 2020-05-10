package com.game.tictactoe.domain;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import static com.game.tictactoe.util.GameConstant.START_MESSAGE;

@Data
public class GameState {

    @JsonUnwrapped
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
