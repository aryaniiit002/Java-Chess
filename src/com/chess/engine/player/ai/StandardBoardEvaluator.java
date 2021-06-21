package com.chess.engine.player.ai;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;
import com.chess.engine.player.Player;
import com.google.common.annotations.VisibleForTesting;

public final class StandardBoardEvaluator implements BoardEvaluator {

    private static final int CHECK_MATE_BONUS = 10000;
    private static final int CHECK_BONUS = 45;
    private static final int CASTLE_BONUS = 25;
    private static final int MOBILITY_MULTIPLIER = 5;
    private static final int ATTACK_MULTIPLIER = 1;
    private static final int TWO_BISHOPS_BONUS = 25;

    private StandardBoardEvaluator() {
    }

    @Override
    public int evaluate(final Board board,
                        final int depth) {
        return score(board, board.whitePlayer(), depth) - score(board, board.blackPlayer(), depth);
    }

    @VisibleForTesting
    private static int score(final Board board, final Player player,
                             final int depth) {
        return pieceValue(player) + mobility(player) +
            check(player) + checkMate(player, depth)
            + castle(player);
    }

    /**
     * How many legal moves does the player has.
     *
     * @param player player whose number of legal moves to find
     * @return number of legal moves
     */
    private static int mobility(final Player player) {
        return MOBILITY_MULTIPLIER * mobilityRatio(player);
    }

    private static int mobilityRatio(final Player player) {
        return (int)(player.getLegalMoves().size() * 10.0f / player.getOpponent().getLegalMoves().size());
    }

    private static int pieceValue(final Player player) {
        int pieceValuationScore = 0;
        int numBishops = 0;
        for (final Piece piece : player.getActivePieces()) {
            pieceValuationScore += piece.getPieceValue();
        }
        return pieceValuationScore + (numBishops == 2 ? TWO_BISHOPS_BONUS : 0);
    }

    private static int check(final Player player) {
        return player.getOpponent().isInCheck() ? CHECK_BONUS : 0;
    }

    private static int checkMate(final Player player,
                                   final int depth) {
        return player.getOpponent().isInCheckMate() ? CHECK_MATE_BONUS  * depthBonus(depth) : check(player);
    }

    private static int depthBonus(final int depth) {
        return depth == 0 ? 1 : 100 * depth;
    }

    private static int castle(final Player player) {
        return player.isCastled() ? CASTLE_BONUS : 0;
    }

}
