package junit.test.com.chess;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({junit.test.com.chess.TestPiecesTest.class,
                     BoardTest.class,
                     junit.test.com.chess.TestStaleMateTest.class,
                     junit.test.com.chess.TestPlayerTest.class,
                     CheckmateTest.class,
                     MiniMaxTest.class,
                     AlphaBetaTest.class,
                     CastlingTest.class,
                     junit.test.com.chess.TestPawnStructureTest.class,
                     FENParserTest.class,
                     junit.test.com.chess.EngineTest.class
                     /*TestPGNParser.class*/})
public class ChessTestSuite {
}
