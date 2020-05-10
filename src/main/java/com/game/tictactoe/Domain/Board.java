package com.game.tictactoe.Domain;

import lombok.Data;

import static com.game.tictactoe.util.GameConstant.BOARD_DIMENSION;

/**
 * Represents the tic tac toe board
 */
@Data
public class Board {

    private Symbol[][] fields;

    public Board() {
        fields = new Symbol[BOARD_DIMENSION][BOARD_DIMENSION];
    }

    /**
     * Adds a new symbol to the board at a provided position
     *
     * @param symbol
     * @param position
     */
    public void addSymbol(Symbol symbol, Position position) {
        fields[position.getX()][position.getY()] = symbol;
    }

    /**
     * Retrieves a symbol from the board
     *
     * @param position
     * @return symbol
     */
    public Symbol getSymbol(Position position) {
        return fields[position.getX()][position.getY()];
    }
}
