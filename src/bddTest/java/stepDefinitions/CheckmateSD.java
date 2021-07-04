package stepDefinitions;

import static org.junit.Assert.assertTrue;

import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.board.BoardUtils;
import com.chess.engine.classic.board.Move;
import com.chess.engine.classic.board.MoveTransition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckmateSD {

    private Board board;
    private MoveTransition moveTransitiont;

    @Given("^Chess board is given$")
    public void testFoolsMate() {
        board = Board.createStandardBoard();
    }

    @When("^player make move$")
    public void findAndProcessMoveTransition() {
        final MoveTransition t1 = board.currentPlayer()
        .makeMove(Move.MoveFactory.createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("f2"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("f3")));

        assertTrue(t1.getMoveStatus().isDone());

        final MoveTransition t2 = t1.getToBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t1.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition("e7"),
                                BoardUtils.INSTANCE.getCoordinateAtPosition("e5")));

        assertTrue(t2.getMoveStatus().isDone());

        final MoveTransition t3 = t2.getToBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t2.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition("g2"),
                                BoardUtils.INSTANCE.getCoordinateAtPosition("g4")));

        assertTrue(t3.getMoveStatus().isDone());

        moveTransitiont = t3.getToBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t3.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition("d8"),
                                BoardUtils.INSTANCE.getCoordinateAtPosition("h4")));
    }

    @Then("^that move status now to be checked if done or not$")
    public void finalMTTest() {
        assertTrue(moveTransitiont.getMoveStatus().isDone());
    }

    @Then("^check checkmate situation$")
    public void testCheckMate() {
        assertTrue(moveTransitiont.getToBoard().currentPlayer().isInCheckMate());
    }
}
