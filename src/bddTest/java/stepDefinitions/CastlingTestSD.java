package stepDefinitions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.board.BoardUtils;
import com.chess.engine.classic.board.Move;
import com.chess.engine.classic.board.MoveTransition;
import com.chess.pgn.FenUtilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CastlingTestSD {

    private Board board;
    private String fenString;
    private MoveTransition moveTransitiont;

    @Given("^Chess Board is given with white pieces$")
    public void testWhiteSideCastle() {
        board = Board.createStandardBoard();
    }

    @When("^Player make move$")
	 public void processMoveTransition() {
         final MoveTransition t1 = board.currentPlayer()
                .makeMove(Move.MoveFactory.createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e2"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("e4")));
        assertTrue(t1.getMoveStatus().isDone());
        final MoveTransition t2 = t1.getToBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t1.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition("e7"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("e5")));
        assertTrue(t2.getMoveStatus().isDone());
        final MoveTransition t3 = t2.getToBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t2.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition("g1"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("f3")));
        assertTrue(t3.getMoveStatus().isDone());
        final MoveTransition t4 = t3.getToBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t3.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition("d7"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("d6")));
        assertTrue(t4.getMoveStatus().isDone());
        final MoveTransition t5 = t4.getToBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t4.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition("f1"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("e2")));
        assertTrue(t5.getMoveStatus().isDone());
        final MoveTransition t6 = t5.getToBoard()
                .currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t5.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition("d6"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("d5")));
        assertTrue(t6.getMoveStatus().isDone());
        final Move wm1 = Move.MoveFactory
                .createMove(t6.getToBoard(), BoardUtils.INSTANCE.getCoordinateAtPosition(
                        "e1"), BoardUtils.INSTANCE.getCoordinateAtPosition("g1"));
        assertTrue(t6.getToBoard().currentPlayer().getLegalMoves().contains(wm1));
        moveTransitiont = t6.getToBoard().currentPlayer().makeMove(wm1);
	 }

	 @Then("^that move status now to be checked if successful or not$")
	 public void confirmMT() {
        assertTrue(moveTransitiont.getMoveStatus().isDone());
	 }

	 @Then("^check white player castling$")
	 public void testPlayerCastling() {
        assertTrue(moveTransitiont.getToBoard().whitePlayer().isCastled());
     }

     @And("^check white player's king side castling$")
	 public void testKingSideCastling() {
        assertFalse(moveTransitiont.getToBoard().whitePlayer().isKingSideCastleCapable());
     }

    @And("^check white player's queen side castling$")
    public void testQueenSideCastling() {
        assertFalse(moveTransitiont.getToBoard().whitePlayer().isQueenSideCastleCapable());
    }

    @Given("^Given FEN Chess Board$")
    public void testNoCastlingOutOfCheck() {
        board = FenUtilities.createGameFromFEN("r3k2r/1pN1nppp/p3p3/3p4/8/8/PPPK1PPP/R6R b kq - 1 18");
    }

    @When("^Calculate illegal castle moves and moveTransition$")
    public void processMovetransition() {
        final Move illegalCastleMove = Move.MoveFactory
                .createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e8"), BoardUtils.INSTANCE.getCoordinateAtPosition("c8"));
        moveTransitiont = board.currentPlayer()
                .makeMove(illegalCastleMove);
    }

	 @Then("^apply assertTrue on calculated moveTransition$")
	 public void testConfirmMT() {
        assertFalse(moveTransitiont.getMoveStatus().isDone());
	 }

}
