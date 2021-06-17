package com.chess.engine.pieces;

import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance; // Alliance is an Enum.
    protected final boolean isFirstMove;

    Piece(final int piecePossition,  final Alliance pieceAlliance, final boolean isFirstMove) {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePossition;
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
    // All of the pieces (bishop, knight, queen, pawn, etc.. ) are going to override this list and
    // have their own behavior defined.
    public abstract Collection<Move> calculateLegalMoves(final Board board);

    //protected abstract boolean isFirstMove();
}
