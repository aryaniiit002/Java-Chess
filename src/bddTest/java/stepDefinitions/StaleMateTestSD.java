package stepDefinitions;

import static com.chess.engine.classic.board.Board.*;
import static org.junit.Assert.assertFalse;

import org.junit.Assert;

import com.chess.engine.classic.Alliance;
import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.board.BoardUtils;
import com.chess.engine.classic.board.Move;
import com.chess.engine.classic.board.MoveTransition;
import com.chess.engine.classic.pieces.King;
import com.chess.engine.classic.pieces.Pawn;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StaleMateTestSD {

    private Board board;
    private final Builder builder = new Builder();
    private MoveTransition moveTransition;

    @Given("^Given ChessBoard using Board.builder$")
    public void makeBoard() {
        // Black Layout
        builder.setPiece(new King(Alliance.BLACK, 2, false, false));
        // White Layout
        builder.setPiece(new Pawn(Alliance.WHITE, 10));
        builder.setPiece(new King(Alliance.WHITE, 26, false, false));

        // Set the current player
        builder.setMoveMaker(Alliance.WHITE);
        board = builder.build();
    }

    @When("^player make move evalute it with MoveTransition$")
    public void findAndProcessMoveTransition() {
        assertFalse(board.currentPlayer().isInStaleMate());
        moveTransition = board.currentPlayer()
                .makeMove(Move.MoveFactory.createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("c5"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("c6")));
        Assert.assertTrue(moveTransition.getMoveStatus().isDone());
    }

    @Then("^evalute inCheck, isInStaleMate, inCheckMate position$")
    public void testAnonymousStaleMate() {
        Assert.assertTrue(moveTransition.getToBoard().currentPlayer().isInStaleMate());
        assertFalse(moveTransition.getToBoard().currentPlayer().isInCheck());
        assertFalse(moveTransition.getToBoard().currentPlayer().isInCheckMate());
    }
}
