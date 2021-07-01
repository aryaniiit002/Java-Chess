package junit.test.com.chess;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({PiecesTest.class,
                     BoardTest.class,
                     StaleMateTest.class,
                     PlayerTest.class,
                     CheckmateTest.class,
                     MiniMaxTest.class,
                     AlphaBetaTest.class,
                     CastlingTest.class,
                     PawnStructureTest.class,
                     FENParserTest.class,
                     junit.test.com.chess.EngineTest.class
                     /*TestPGNParser.class*/})
public class ChessTestSuite {
}
