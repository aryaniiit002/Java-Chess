package main.java.com.chess.pgn;

import main.java.com.chess.engine.classic.board.Board;
import main.java.com.chess.engine.classic.board.Move;
import main.java.com.chess.engine.classic.player.Player;

public interface PGNPersistence {

    void persistGame(Game game);

    Move getNextBestMove(Board board, Player player, String gameText);

}
