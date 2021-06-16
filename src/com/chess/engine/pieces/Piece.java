package com.chess.engine.pieces;

import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece {
    protected final int piecePossition;
    // Alliance is an Enum.
    protected final Alliance pieceAlliance;

    Piece(final int piecePossition,  final Alliance pieceAlliance) {
        this.pieceAlliance = pieceAlliance;
        this.piecePossition = piecePossition;
    }

    // All of the pieces (bishop, knight, queen, pawn, etc.. ) are going to override this list and
    // have their own behavior defined.
    public abstract List<Move> calculateLegalMoves(final Board board);
}
