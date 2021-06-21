package com.chess.engine.player.ai;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.player.MoveTransition;

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

        final long startTime = System.currentTimeMillis();
        Move bestMove = Move.MoveFactory.getNullMove();
        int highestSeenValue = Integer.MIN_VALUE;
        int lowestSeenValue = Integer.MAX_VALUE;
        int currentValue;

        System.out.println(board.currentPlayer() + " THINKING with depth = " + depth);

        final int numMoves = board.currentPlayer().getLegalMoves().size();

        for (final Move move : board.currentPlayer().getLegalMoves()) {
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);

            if (moveTransition.getMoveStatus().isDone()) {
                currentValue = board.currentPlayer().getAlliance().isWhite() ?
                    min(moveTransition.getTransitionBoard(), depth - 1) :
                    max(moveTransition.getTransitionBoard(), depth - 1);

                if (board.currentPlayer().getAlliance().isWhite() &&
                    currentValue >= highestSeenValue) {
                    highestSeenValue = currentValue;
                    bestMove = move;
                } else if (board.currentPlayer().getAlliance().isBlack() &&
                    currentValue <= lowestSeenValue) {
                    lowestSeenValue = currentValue;
                    bestMove = move;
                }
            }

            final long executionTime = System.currentTimeMillis() - startTime;

            return bestMove;
        }
    }

    /** min calls max and max calls min.
     * These methods are CO recursive.
     */
    private int min(final Board board,
                    final int depth) {
        if(depth == 0) {
            return this.evaluator.evaluate(board, depth);
        }
        /*if() {
            return this.evaluator.evaluate(board, depth);
        }*/
        int lowestSeenValue = Integer.MAX_VALUE;

        for (final Move move : board.currentPlayer().getLegalMoves()) {
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);

            if (moveTransition.getMoveStatus().isDone()) {
                final int currentValue = max(moveTransition.getTransitionBoard(), depth - 1);

                if (currentValue <= lowestSeenValue) {
                    lowestSeenValue = currentValue;
                }
            }
        }

        return lowestSeenValue;
    }

    private int max(final Board board,
                    final int depth) {
        if(depth == 0) {
            return this.evaluator.evaluate(board, depth);
        }
        /*if(isEndGameScenario(board)) {
            return this.evaluator.evaluate(board, depth);
        }*/
        int highestSeenValue = Integer.MIN_VALUE;
        for (final Move move : board.currentPlayer().getLegalMoves()) {
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if (moveTransition.getMoveStatus().isDone()) {
                final int currentValue = min(moveTransition.getTransitionBoard(), depth - 1);
                if (currentValue >= highestSeenValue) {
                    highestSeenValue = currentValue;
                }
            }
        }
        return highestSeenValue;
    }
}
