package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

public final class Knight extends Piece {

    private static final int[] CANDIDATE_MOVE_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17 };

    public Knight(final Alliance pieceAlliance, final int piecePosition) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();
        for(final int currentCandidate : CANDIDATE_MOVE_COORDINATES) {
            candidateDestinationCoordinate = this.piecePossition + currentCandidate;

            if(true /* isValidTileCoordinate*/) {
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                 if(!candidateDestinationTile.isTileOccupied()) {
                     legalMoves.add(new Move());
                 }
                 else {
                     final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                     final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                     // If this is true then we'd know that piece is enemy piece.
                     if(this.pieceAlliance != pieceAlliance) {
                         legalMoves.add(new Move());
                     }
                 }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
}
