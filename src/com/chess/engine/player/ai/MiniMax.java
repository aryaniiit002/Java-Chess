package com.chess.engine.player.ai;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public class MiniMax implements MoveStrategy {

    private final BoardEvaluator evaluator;

    public MiniMax(final int searchDepth) {
        this.evaluator = null;
    }

    @Override
    public String toString() {
        return "MiniMax";
    }

    /**
     * Our ai is going to invoke this method called execute on a board for a given dept and it's then going to use
     * the minimax algorithm in order to calculate the best move for the game or user strategy.
     *
     * @param board chessBoard
     * @param depth dept for minimax algorithm
     * @return the best move for user strategy.
     */
    @Override
    public Move execute(Board board, int depth) {
        return null;
    }
}
