package com.chess.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({com.chess.tests.TestPieces.class,
                     com.chess.tests.TestBoard.class,
                     com.chess.tests.TestStaleMate.class,
                     com.chess.tests.TestPlayer.class,
                     com.chess.tests.TestCheckmate.class,
                     com.chess.tests.TestMiniMax.class,
                     com.chess.tests.TestAlphaBeta.class,
                     com.chess.tests.TestCastling.class,
                     com.chess.tests.TestPawnStructure.class,
                     com.chess.tests.TestFENParser.class,
                     com.chess.tests.TestEngine.class
                     /*TestPGNParser.class*/})
public class ChessTestSuite {
}
