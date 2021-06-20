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

public final class Knight extends Piece {

    /**
     * We are looping through these candidate offSets. We the offSet to current possition and if that
     * possition is valid (or it's corresponds to valid tile on board) we then want to check if the tile is
     * occupied or not.
     * If it's not occupied we will add a non attacking move and if it is occupied by an enemy piece
     * we will add a new attacking move.
     */
    private static final int[] CANDIDATE_MOVE_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17 };

    public Knight(final Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.KNIGHT, piecePosition, pieceAlliance, true);
    }

    public Knight(final Alliance pieceAlliance, final int piecePossition, final boolean isFirstMove) {
        super(PieceType.KNIGHT, piecePossition, pieceAlliance, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            final int candidateDestinationCoordinate = this.getPiecePosition() + currentCandidateOffset;
            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

                if(isFirstColumnExclusion(this.getPiecePosition(), currentCandidateOffset) ||
                    isSecondColumnExclusion(this.getPiecePosition(), currentCandidateOffset) ||
                    isSeventhColumnExclusion(this.getPiecePosition(), currentCandidateOffset) ||
                    isEighthColumnExclusion(this.getPiecePosition(), currentCandidateOffset)) {
                    continue;
                }
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                 if(!candidateDestinationTile.isTileOccupied()) {
                     legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                 }
                 else {
                     final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                     final Alliance pieceAtDestinationAllegiance = pieceAtDestination.getPieceAlliance();

                     // If this is true then we'd know that piece is enemy piece.
                     if(this.getPieceAlliance() != pieceAtDestinationAllegiance) {
                         legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate,
                             pieceAtDestination));
                     }
                 }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Knight movePiece(Move move) {
        // this will create a new Knight in the new location.
        return new Knight(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }

    // Here all the offSets written are for when our rule breaks down if current possition is in 1st column.
    private static boolean isFirstColumnExclusion(final int currentPosition,
                                                  final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 ||
            candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);
    }
    private static boolean isSecondColumnExclusion(final int currentPosition,
                                                  final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == 6 ||
            candidateOffset == -10);
    }
    private static boolean isSeventhColumnExclusion(final int currentPosition,
                                                  final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == 6 ||
            candidateOffset == -10);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition,
                                                  final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 17 ||
            candidateOffset == 10 || candidateOffset == -6 || candidateOffset == -15);
    }

}
