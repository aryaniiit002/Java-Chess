package com.chess.engine.pieces;

import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece {
    private final int piecePosition;
    private final Alliance pieceAlliance; // Alliance is an Enum.
    private final boolean isFirstMove;

    protected Piece(final int piecePosition,  final Alliance pieceAlliance, final boolean isFirstMove) {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        this.isFirstMove = isFirstMove;
    }

    public int getPiecePosition() {
        return this.piecePosition;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public Alliance getPieceAllegiance() {
        return this.pieceAlliance;
    }

    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }

    // All of the pieces (bishop, knight, queen, pawn, etc.. ) are going to override this list and
    // have their own behavior defined.
    public abstract Collection<Move> calculateLegalMoves(final Board board);

    //protected abstract boolean isFirstMove();
}
