package stepDefinitions;

import static org.junit.Assert.assertEquals;
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

public class FENParserSD {

    private Board board;
    private String fenString;
    private MoveTransition moveTransitiont;

    @Given("^Given Chess Board$")
    public void testWriteFEN1(){
        System.out.println("Processing FEN1 on given chess board");
        board = Board.createStandardBoard();
    }

	 @When("^Calculate String fenString$")
	 public void fenString(){
        fenString = FenUtilities.createFENFromGame(board);
	 }

	 @Then("^Compare the fenString with the correct/user provided string$")
	 public void compare(){
        assertEquals(fenString, "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	 }

    @Given("^Chess Board is given$")
    public void testWriteFEN2(){
        System.out.println("Processing FEN2 on given chess board");
        board = Board.createStandardBoard();
    }

	 @Then("^Calculate MoveTransition$")
	 public void processMoveTransition(){
        moveTransitiont = board.currentPlayer()
                .makeMove(Move.MoveFactory.createMove(board, BoardUtils.INSTANCE.getCoordinateAtPosition("e2"),
                        BoardUtils.INSTANCE.getCoordinateAtPosition("e4")));
	 }

	 @And("^Apply assertTrue to calculated MoveTransition$")
     public void checkMT(){
        assertTrue(moveTransitiont.getMoveStatus().isDone());
	 }

	 @Then("^calculate String fenString$")
	 public void fenStringFEN2(){
        fenString = FenUtilities.createFENFromGame(moveTransitiont.getToBoard());
	 }

	 @Then("^compare the fenString$")
	 public void compareFEN2(){
        assertEquals(fenString, "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        board = Board.createStandardBoard();
	 }
}
