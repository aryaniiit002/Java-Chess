package com.chess.engine.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Bool;

public final class BoardUtils {

    /**
     * Intialize and keep track of a particular column in our chessBoard and we this for example in knight class
     * to calculate the column exclusions.
     */
    public static final Boolean[] FIRST_COLUMN = initColumn(0);
    public static final Boolean[] SECOND_COLUMN = initColumn(1);
    public static final Boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final Boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final Boolean[] EIGHTH_RANK = initRow(0);
    public static final Boolean[] SEVENTH_RANK = initRow(8);
    public static final Boolean[] SIXTH_RANK = initRow(16);
    public static final Boolean[] FIFTH_RANK = initRow(24);
    public static final Boolean[] FOURTH_RANK = initRow(32);
    public static final Boolean[] THIRD_RANK = initRow(40);
    public static final Boolean[] SECOND_RANK = initRow(48);
    public static final Boolean[] FIRST_RANK = initRow(56);

    public static final int START_TILE_INDEX = 0;
    public static final int NUM_TILES_PER_ROW = 8;
    public static final int NUM_TILES = 64;

    private BoardUtils() {
        throw new RuntimeException("You can't instantiate me!");
    }

    private static Boolean[] initColumn(int columnNumber) {
        final Boolean[] column = new Boolean[NUM_TILES];
        for(int i = 0; i < column.length; i++) {
            column[i] = false;
        }
        do {
            column[columnNumber] = true;
            columnNumber += NUM_TILES_PER_ROW;
        } while(columnNumber < NUM_TILES);
        return column;
    }

    private static Boolean[] initRow(int rowNumber) {
        final Boolean[] row = new Boolean[NUM_TILES];
        for(int i = 0; i < row.length; i++) {
            row[i] = false;
        }
        do {
            row[rowNumber] = true;
            rowNumber++;
        } while(rowNumber % NUM_TILES_PER_ROW != 0);
        return row;
    }

    public static boolean isValidTileCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < NUM_TILES;
    }
}
