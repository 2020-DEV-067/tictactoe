package com.game.tictactoe.domain;

import lombok.Data;

import static com.game.tictactoe.util.GameConstant.BOARD_DIMENSION;

@Data
public class Board {

    private PlayerSymbol[][] gameField;

    public Board() {
        gameField = new PlayerSymbol[BOARD_DIMENSION][BOARD_DIMENSION];
    }

    public void addPlayerSymbol(PlayerSymbol symbol, Position position) {
        gameField[position.getX()][position.getY()] = symbol;
    }

    public PlayerSymbol getPlayerSymbol(Position position) {
        return gameField[position.getX()][position.getY()];
    }
}
