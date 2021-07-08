package stepDefinitions;

import static com.chess.engine.classic.board.Board.*;
import static org.junit.Assert.assertFalse;

import org.junit.Assert;

import com.chess.engine.classic.Alliance;
import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.board.BoardUtils;
import com.chess.engine.classic.board.Move;
import com.chess.engine.classic.board.MoveTransition;
import com.chess.engine.classic.pieces.Bishop;
import com.chess.engine.classic.pieces.King;
import com.chess.engine.classic.pieces.Knight;
import com.chess.engine.classic.pieces.Pawn;
import com.chess.engine.classic.pieces.Queen;
import com.chess.engine.classic.pieces.Rook;
import com.chess.engine.classic.player.ai.IterativeDeepening;
import com.chess.engine.classic.player.ai.MoveStrategy;
import com.chess.engine.classic.player.ai.StockAlphaBeta;
import com.chess.pgn.FenUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AlphaBetaTestSD {
    private Board board;
    private final Builder builder = new Builder();
    private MoveStrategy alphaBeta;
    private MoveStrategy iterativeDeepening;

    @Given("^Given ChessBoard using Board.Builder$")
    public void makeBoard() {
        // Black Layout
        builder.setPiece(new Rook(Alliance.BLACK, 0));
        builder.setPiece(new Knight(Alliance.BLACK, 1));
        builder.setPiece(new Bishop(Alliance.BLACK, 2));
        builder.setPiece(new Queen(Alliance.BLACK, 3));
        builder.setPiece(new King(Alliance.BLACK, 4, false, false));
        builder.setPiece(new Bishop(Alliance.BLACK, 5));
        builder.setPiece(new Knight(Alliance.BLACK, 6));
        builder.setPiece(new Rook(Alliance.BLACK, 7));
        builder.setPiece(new Pawn(Alliance.BLACK, 8));
        builder.setPiece(new Pawn(Alliance.BLACK, 9));
        builder.setPiece(new Pawn(Alliance.BLACK, 10));
        builder.setPiece(new Pawn(Alliance.BLACK, 11));
        builder.setPiece(new Pawn(Alliance.BLACK, 12));
        builder.setPiece(new Pawn(Alliance.BLACK, 13));
        builder.setPiece(new Pawn(Alliance.BLACK, 14));
        builder.setPiece(new Pawn(Alliance.BLACK, 15));
        // White Layout
        builder.setPiece(new Pawn(Alliance.WHITE, 48));
        builder.setPiece(new Pawn(Alliance.WHITE, 49));
        builder.setPiece(new Pawn(Alliance.WHITE, 50));
        builder.setPiece(new Pawn(Alliance.WHITE, 51));
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new Pawn(Alliance.WHITE, 53));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        builder.setPiece(new Pawn(Alliance.WHITE, 55));
        builder.setPiece(new Rook(Alliance.WHITE, 56));
        builder.setPiece(new Knight(Alliance.WHITE, 57));
        builder.setPiece(new Bishop(Alliance.WHITE, 58));
        builder.setPiece(new Queen(Alliance.WHITE, 59));
        builder.setPiece(new King(Alliance.WHITE, 60, false, false));
        builder.setPiece(new Bishop(Alliance.WHITE, 61));
        builder.setPiece(new Knight(Alliance.WHITE, 62));
        builder.setPiece(new Rook(Alliance.WHITE, 63));

        // Set the current player
        builder.setMoveMaker(Alliance.BLACK);
        board = builder.build();
    }

    @When("^Evalute move with alphaBeta moveStrategy$")
    public void evaluteMove() {
        System.out.println(FenUtilities.createFENFromGame(board));
        alphaBeta = new StockAlphaBeta(4);
    }

    @Then("^Test best move with evaluated alphaBeta move$")
    public void testOpeningDepth4BlackMovesFirst() {
        final Move bestMove = alphaBeta.execute(board);
        Assert.assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e7"),
                    BoardUtils.INSTANCE.getCoordinateAtPosition("e5")));
    }

    @Given("^ChessBoard is given using Board.Builder$")
    public void makeBoard2() {
        // Black Layout
        builder.setPiece(new Rook(Alliance.BLACK, 11));
        builder.setPiece(new Pawn(Alliance.BLACK, 16));
        builder.setPiece(new Bishop(Alliance.BLACK, 27));
        builder.setPiece(new King(Alliance.BLACK, 29, false, false));
        // White Layout
        builder.setPiece(new Rook(Alliance.WHITE, 17));
        builder.setPiece(new Rook(Alliance.WHITE, 26));
        builder.setPiece(new Pawn(Alliance.WHITE, 35));
        builder.setPiece(new Pawn(Alliance.WHITE, 45));
        builder.setPiece(new Bishop(Alliance.WHITE, 51));
        builder.setPiece(new Pawn(Alliance.WHITE, 54));
        builder.setPiece(new Pawn(Alliance.WHITE, 55));
        builder.setPiece(new King(Alliance.WHITE, 63, false, false));

        builder.setMoveMaker(Alliance.WHITE);
        board = builder.build();
    }

    @When("^Evalute dept with iterativeDeepening moveStrategy$")
    public void evaluteIterativeDeepeningdeptAndMove() {
        final String fen = FenUtilities.createFENFromGame(board);
        System.out.println(fen);
        iterativeDeepening = new IterativeDeepening(4);
    }

    @Then("^Test best move with evaluated iterativeDeepening move$")
    public void testCheckmateHorizon() {
        final Move bestMove = iterativeDeepening.execute(board);
        Assert.assertEquals(bestMove, Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("g2"),
                    BoardUtils.INSTANCE.getCoordinateAtPosition("g4")));
    }
}
