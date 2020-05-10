package com.game.tictactoe.Domain;

import lombok.Data;

import static com.game.tictactoe.util.GameConstant.BOARD_DIMENSION;

/**
 * Represents the tic tac toe board
 */
@Data
public class Board {

    private PlayerSymbol[][] gameField;

    public Board() {
        gameField = new PlayerSymbol[BOARD_DIMENSION][BOARD_DIMENSION];
    }

    /**
     * Adds a new player symbol to the board at a provided position
     *
     * @param playerSymbol
     * @param position
     */
    public void addPlayerSymbol(PlayerSymbol symbol, Position position) {
        gameField[position.getX()][position.getY()] = symbol;
    }

    /**
     * Retrieves a player symbol from the board
     *
     * @param position
     * @return playerSymbol
     */
    public PlayerSymbol getPlayerSymbol(Position position) {
        return gameField[position.getX()][position.getY()];
    }
}
