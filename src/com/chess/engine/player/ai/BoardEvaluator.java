package com.chess.engine.player.ai;

import com.chess.engine.board.Board;

public interface BoardEvaluator {

    /**
     * Board evaluator and it returns an integer and by convention the more positive the number is that means
     * the white is winning, the more the number is that means the black is winning.
     * (We are playing the zero sum game (chess))
     *
     * @param board chessBoard
     * @param depth dept for minimax algorithm
     * @return evaluated integer.
     */
    int evaluate(Board board, int depth);
}
