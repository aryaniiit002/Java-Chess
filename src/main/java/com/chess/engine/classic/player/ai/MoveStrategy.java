package main.java.com.chess.engine.classic.player.ai;

import main.java.com.chess.engine.classic.board.Board;
import main.java.com.chess.engine.classic.board.Move;

public interface MoveStrategy {

    long getNumBoardsEvaluated();

    Move execute(Board board);

}
