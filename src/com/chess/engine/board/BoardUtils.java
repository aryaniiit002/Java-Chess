package com.chess.engine.board;

public class BoardUtils {
    public static final boolean FIRST_COLUMN = null;
    public static final boolean SECOND_COLUMN = null;
    public static final boolean SEVENTH_COLUMN = null;
    public static final boolean EIGHT_COLUMN = null;

    private BoardUtils() {
        throw new RuntimeException("You can't instantiate me!");
    }
    public static final int INT = 64;

    public static boolean isValidTileCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate < INT;
    }
}
