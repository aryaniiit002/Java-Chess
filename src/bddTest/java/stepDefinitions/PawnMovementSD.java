package stepDefinitions;

import static org.junit.Assert.assertEquals;

import com.chess.engine.classic.Alliance;
import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.pieces.King;
import com.chess.engine.classic.pieces.Pawn;
import com.chess.engine.classic.player.ai.PawnStructureAnalyzer;
import com.chess.pgn.FenUtilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PawnMovementSD {

    private Board board;
    private Board.Builder builder = new Board.Builder();

    @Given("^create a particular board position from FEN$")
    public void testIsolatedPawnByExample1(){
        board = Board.createStandardBoard();
    }

	 @Then("^compare the analysed board for white player$")
	 public void compareW(){
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.whitePlayer()), 0);
	 }

	 @Then("^compare the analysed board for black player$")
	 public void compareB(){
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.blackPlayer()), 0);
	 }


	 @Given("^create a particular board position$")
    public void testIsolatedPawnByExample2(){
        board = Board.createStandardBoard();
    }

    @When("^setting up board with white pieces$")
	 public void createWhiteBoard(){
        builder.setPiece(new Pawn(Alliance.WHITE, 52));
        builder.setPiece(new King(Alliance.WHITE, 60, false, false));
        builder.setMoveMaker(Alliance.WHITE);
	 }

	 @And("^setting up board with black pieces$")
	 public void createBlackBoard(){
        builder.setPiece(new King(Alliance.BLACK, 4, false, false));
        builder.setPiece(new Pawn(Alliance.BLACK, 12));
        builder.setPiece(new Pawn(Alliance.BLACK, 20));
        builder.setPiece(new Pawn(Alliance.BLACK, 28));
        builder.setPiece(new Pawn(Alliance.BLACK, 8));
        builder.setPiece(new Pawn(Alliance.BLACK, 16));
	 }

	 @Then("^build board$")
	 public void buildBoard(){
        board = builder.build();
        System.out.println(FenUtilities.createFENFromGame(board));
	 }

	 @Then("^compare the analysed board for White Player$")
	 public void compareWhite(){
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.whitePlayer()),
            PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY);
	 }

	 @Then("^compare the analysed board for Black Player$")
	 public void compareBlack(){
        assertEquals(PawnStructureAnalyzer.get().isolatedPawnPenalty(board.blackPlayer()),
            PawnStructureAnalyzer.ISOLATED_PAWN_PENALTY * 5);
	 }
}
