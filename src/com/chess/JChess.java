package com.chess;

import com.chess.engine.board.Board;
import com.chess.gui.Table;

// Driver Class
public class JChess {
    public static void main(String... args) {
        Board board = Board.createStandardBoardImpl();

        System.out.println(board);

        Table table = new Table();
    }
}
