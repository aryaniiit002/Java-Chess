package com.chess.engine;

import com.chess.engine.board.Board;

// Driver Class
public class JChess {
    public static void main(String... args) {
        Board board = Board.createStandardBoardImpl();

        System.out.println(board);
    }
}
