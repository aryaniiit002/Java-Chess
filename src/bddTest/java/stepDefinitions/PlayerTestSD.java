package stepDefinitions;

import static com.chess.engine.classic.board.Board.*;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import com.chess.engine.classic.Alliance;
import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.board.BoardUtils;
import com.chess.engine.classic.board.Move;
import com.chess.engine.classic.board.MoveTransition;
import com.chess.engine.classic.pieces.Bishop;
import com.chess.engine.classic.pieces.King;
import com.chess.engine.classic.pieces.Rook;
import com.chess.engine.classic.player.ai.StandardBoardEvaluator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class PlayerTestSD {

    private Board board;
    private final Builder builder = new Builder();
    private MoveTransition moveTransition;

    @Given("^Given ChessBoard using board.createStandardBoard$")
    public void makeBoard() {
        board = createStandardBoard();
    }

    @When("^player make the move evalute it with MoveTransition$")
    public void findAndProcessMoveTransition() {
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(Move.MoveFactory.createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e2"),
                                BoardUtils.INSTANCE.getCoordinateAtPosition("e4")));
        assertTrue(t1.getMoveStatus().isDone());
        moveTransition = t1.getToBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t1.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition("e7"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("e5")));
        assertTrue(moveTransition.getMoveStatus().isDone());
    }

    @Then("^check move status and evaluate board using StandardBoardEvaluator$")
    public void testSimpleEvaluation() {
        Assert.assertEquals(StandardBoardEvaluator.get().evaluate(moveTransition.getToBoard(), 0), 0);
    }

    @Given("^Given ChessBoard using board.builder$")
    public void createBoard() {
        board = createStandardBoard();
    }

    @When("^player make the move and set pieces on board$")
    public void setBoardPieces() {
        // Black Layout
        builder.setPiece(new King(Alliance.BLACK, 4, false, false));
        builder.setPiece(new Rook(Alliance.BLACK, 24));
        // White Layout
        builder.setPiece(new Bishop(Alliance.WHITE, 44));
        builder.setPiece(new Rook(Alliance.WHITE, 52));
        builder.setPiece(new King(Alliance.WHITE, 58, false, false));
    }

    @Then("^Check player moves status")
    public void testDiscoveredCheck() {
        // Set the current player
        builder.setMoveMaker(Alliance.WHITE);
        board = builder.build();
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(Move.MoveFactory.createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e3"),
                                BoardUtils.INSTANCE.getCoordinateAtPosition("b6")));
        assertTrue(t1.getMoveStatus().isDone());
        assertTrue(t1.getToBoard().currentPlayer().isInCheck());
        final MoveTransition t2 = t1.getToBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t1.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition("a5"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("b5")));
        assertFalse(t2.getMoveStatus().isDone());
        final MoveTransition t3 = t1.getToBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t1.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition("a5"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("e5")));
        assertTrue(t3.getMoveStatus().isDone());
    }
}
