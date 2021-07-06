package stepDefinitions;

import static junit.framework.TestCase.assertEquals;

import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.player.ai.MiniMax;
import com.chess.engine.classic.player.ai.MoveStrategy;
import com.chess.pgn.FenUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EngineTestSD {
    private Board board;
    private MoveStrategy minMax;

    @Given("^Given Chess Board using createGameFromFEN$")
    public void kiwiPeteDepth1() {
        board = FenUtilities.createGameFromFEN("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
    }

    @When("^Calculate minimax from move strategy$")
    public void findAndProcessMoveStrategy() {
        minMax = new MiniMax(1);
    }

    @Then("^execute minimax and evalute board$")
    public void finalMSTest() {
        minMax.execute(board);
        assertEquals(minMax.getNumBoardsEvaluated(), 48L);
    }

}
