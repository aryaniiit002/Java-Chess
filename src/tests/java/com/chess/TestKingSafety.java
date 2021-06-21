package com.chess.tests;

import org.junit.Test;

import main.java.com.chess.engine.classic.Alliance;
import main.java.com.chess.engine.classic.board.Board;
import main.java.com.chess.engine.classic.board.Board.Builder;
import main.java.com.chess.engine.classic.pieces.King;
import main.java.com.chess.engine.classic.pieces.Pawn;

public class TestKingSafety {

    @Test
    public void test1() {
        final Builder builder = new Builder();
        // Black Layout
        builder.setPiece(new King(Alliance.BLACK, 4, false, false));
        builder.setPiece(new Pawn(Alliance.BLACK, 12));
        // White Layout
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new King(Alliance.WHITE, 60, false, false));
        builder.setMoveMaker(Alliance.WHITE);
        // Set the current player
        final Board board = builder.build();

        //assertEquals(KingSafetyAnalyzer.get().calculateKingTropism(board.whitePlayer()).tropismScore(), 40);
    }

}