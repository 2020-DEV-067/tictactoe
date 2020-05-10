package com.game.tictactoe.service.exception;

import static com.game.tictactoe.util.GameConstant.POSITION_IS_TAKEN;

public class PositionIsAlreadyOccupiedException extends RuntimeException {
    public PositionIsAlreadyOccupiedException() {
        super(POSITION_IS_TAKEN);
    }
}
