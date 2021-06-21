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
        return pieceValue(player);
    }

    private static int pieceValue(final Player player) {
        int pieceValuationScore = 0;
        int numBishops = 0;
        for (final Piece piece : player.getActivePieces()) {
            pieceValuationScore += piece.getPieceValue() + piece.locationBonus();
            if(piece.getPieceType() == BISHOP) {
                numBishops++;
            }
        }
        return pieceValuationScore + (numBishops == 2 ? TWO_BISHOPS_BONUS : 0);
    }
}
