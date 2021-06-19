package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import static com.chess.engine.board.Move.*;

public class King extends Piece{

    private static final int[] CANDIDATE_MOVE_COORDINATES = { -9, -8, -7, -1, 1, 7, 8, 9 };
    private final boolean isCastled;
    private final boolean kingSideCastleCapable;
    private final boolean queenSideCastleCapable;

    /* Initial placement of the king.
     * Possible movements of the unhindered king piece.
     * The king's movement may be hindered by other pieces.
     * The king can make a special move "Castling".
     */
    public King(final Alliance alliance,
                final int piecePosition,
                final boolean kingSideCastleCapable,
                final boolean queenSideCastleCapable) {
        super(PieceType.KING, piecePosition, alliance, true);
        this.isCastled = false;
        this.kingSideCastleCapable = kingSideCastleCapable;
        this.queenSideCastleCapable = queenSideCastleCapable;
    }

    public King(final Alliance alliance,
                final int piecePosition,
                final boolean isFirstMove,
                final boolean isCastled,
                final boolean kingSideCastleCapable,
                final boolean queenSideCastleCapable) {
        super(PieceType.KING, piecePosition, alliance, isFirstMove);
        this.isCastled = isCastled;
        this.kingSideCastleCapable = kingSideCastleCapable;
        this.queenSideCastleCapable = queenSideCastleCapable;
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            if (isFirstColumnExclusion(this.getPiecePosition(), currentCandidateOffset) ||
                isEighthColumnExclusion(this.getPiecePosition(), currentCandidateOffset)) {
                continue;
            }
            final int candidateDestinationCoordinate = this.getPiecePosition() + currentCandidateOffset;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                 if(!candidateDestinationTile.isTileOccupied()) {
                     legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                 }
                 else {
                     final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                     final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();

                     // If this is true then we'd know that piece is enemy piece.
                     if(this.getPieceAlliance() != pieceAtDestinationAllegiance) {
                         legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate,
                             pieceAtDestination));
                     }
                 }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public King movePiece(final Move move) {
        // this will create a new King in the new location.
        return new King(this.pieceAlliance, move.getDestinationCoordinate(), false, move.isCastlingMove(), false, false);
    }

    public boolean isCastled() {
        return this.isCastled;
    }

    public boolean isKingSideCastleCapable() {
        return this.kingSideCastleCapable;
    }

    public boolean isQueenSideCastleCapable() {
        return this.queenSideCastleCapable;
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }

    // This exclision will be when king is on the 1st column and CANDIDATE_MOVE_COORDINATES is either
    // -9, -1 or 7.     (test cases will prove this).
    private static boolean isFirstColumnExclusion(final int currentCandidate,
                                                  final int candidateDestinationCoordinate) {
        return BoardUtils.FIRST_COLUMN[currentCandidate]
                && (candidateDestinationCoordinate == -9 || candidateDestinationCoordinate == -1 ||
            candidateDestinationCoordinate == 7);
    }
    // It's the inverse when king piece is on the 8th column
    private static boolean isEighthColumnExclusion(final int currentCandidate,
                                                   final int candidateDestinationCoordinate) {
        return BoardUtils.EIGHTH_COLUMN[currentCandidate]
                && candidateDestinationCoordinate == -7 || candidateDestinationCoordinate == 1 ||
            candidateDestinationCoordinate == 9;
    }
}
