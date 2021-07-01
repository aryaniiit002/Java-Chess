package junit.test.com.chess;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import com.google.common.io.Resources;
import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.board.Move;
import com.chess.engine.classic.board.MoveTransition;
import com.chess.pgn.MySqlGamePersistence;
import com.chess.pgn.PGNUtilities;
import com.chess.pgn.ParsePGNException;

public class PGNParserTest {

    @Test
    public void test1() throws IOException {
        doTest("junit/test/com/chess/pgn/t1.pgn");
    }

    @Test
    public void test2() throws IOException {
        doTest("junit/test/com/chess/pgn/t2.pgn");
    }

    @Test
    public void test3() throws IOException {
        doTest("junit/test/com/chess/pgn/t3.pgn");
    }

    @Test
    public void test4() throws IOException {
        doTest("junit/test/com/chess/pgn/t4.pgn");
    }

    @Test
    public void test5() throws IOException {
        doTest("junit/test/com/chess/pgn/smallerTest.pgn");
    }

    @Test
    public void test6() throws IOException {
        doTest("junit/test/com/chess/pgn/t6.pgn");
    }

    @Test
    public void test8() throws IOException {
        doTest("junit/test/com/chess/pgn/t8.pgn");
    }

    @Test
    public void test9() throws IOException {
        doTest("junit/test/com/chess/pgn/t9.pgn");
    }

    @Test
    public void testPawnPromotion() throws IOException {
        doTest("junit/test/com/chess/pgn/queenPromotion.pgn");
    }

    @Test
    public void test10() throws IOException {
        doTest("junit/test/com/chess/pgn/t10.pgn");
    }

    @Test
    public void testMax() throws IOException {
        int maxId = MySqlGamePersistence.get().getMaxGameRow();
        System.out.println("max id = " +maxId);
    }

    @Test
    public void testParens() throws ParsePGNException {

        final String gameText = "(+)-(-) (+)-(-) 1. e4 e6";
        final List<String> moves = PGNUtilities.processMoveText(gameText);
        assert moves.size() == 2;

    }

    @Test
    public void testWithErol() throws IOException {
        final Board board = Board.createStandardBoard();
        final Move move = MySqlGamePersistence.get().getNextBestMove(board, board.currentPlayer(), "");
        final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
        final Move move2 = MySqlGamePersistence.get()
                .getNextBestMove(moveTransition.getToBoard(),
                        moveTransition.getToBoard().currentPlayer(), "e4");
        System.out.println("move 2 = " +move2);
    }

    private static void doTest(final String testFilePath) throws IOException {
        final URL url = Resources.getResource(testFilePath);
        final File testPGNFile = new File(url.getFile());
        PGNUtilities.persistPGNFile(testPGNFile);
    }
}
