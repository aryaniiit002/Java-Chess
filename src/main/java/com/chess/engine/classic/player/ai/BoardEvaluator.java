package main.java.com.chess.engine.classic.player.ai;

import main.java.com.chess.engine.classic.board.Board;

public interface BoardEvaluator {

    int evaluate(Board board, int depth);

}
