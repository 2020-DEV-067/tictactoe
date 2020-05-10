package com.game.tictactoe.util;

public final class GameConstant {
    public static final int BOARD_DIMENSION = 3;
    public static final String START_MESSAGE = "Play the game!";
    public static final String NEXT_MESSAGE = "Player %s is the next!";
    public static final String WIN_MESSAGE = "Player %s won the game!";
    public static final String DRAW_MESSAGE = "Draw!";

    public static final String PATH_STATE = "/board/state";
    public static final String PATH_PLAY_TURN = "/board/playTurn";

    public static final String INVALID_POSITION = "The provided position value is not valid!";
    public static final String POSITION_IS_TAKEN = "Selected position is already occupied!";
}
