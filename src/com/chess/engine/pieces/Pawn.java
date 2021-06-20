package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;
import com.google.common.collect.ImmutableList;

public class Pawn extends Piece{

    /*
     * Legal move calculation for a Pawn.
     * Details to focus on :
     *      1) In initial position of a chessBoard a pawn can either move forward one tile or they
     *       can jump 2 tiles(once).
     *      2) Pawn moves in different direction when its attacking and when it's mot.
     *      3) "En passant" a special move for pawn.
     *      4) When pawn advance all the way into the enemy's camp then can be promoted to any other
     *       piece except to king.
     *      5) Pawn have directionality.
     */
    private static final int[] CANDIDATE_MOVE_COORDINATES = {8, 16, 7, 9};

    public Pawn(final Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.PAWN, piecePosition, pieceAlliance, true);
    }

    public Pawn(final Alliance pieceAlliance, final int piecePossition, final boolean isFirstMove) {
        super(PieceType.PAWN, piecePossition, pieceAlliance, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationCoordinate =
                this.getPiecePosition() + this.getPieceAlliance().getDirection() * currentCandidateOffset;
            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            // This if handles the non-attacking pawn moves
            if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            }
            // This if handles the pawn jump
            else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SEVENTH_RANK[this.getPiecePosition()] && this.getPieceAlliance().isBlack() ||
                        BoardUtils.SECOND_RANK[this.getPiecePosition()] && this.getPieceAlliance().isWhite())) {
                final int behindCandidateDestinationCoordinate =
                        this.getPiecePosition() + (this.getPieceAlliance().getDirection() << 3);
                if(!board.getTile(candidateDestinationCoordinate).isTileOccupied()
                    && !board.getTile(behindCandidateDestinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new Move.PawnJump(board, this, candidateDestinationCoordinate));
                }
            }
            // This if handles the attacking pawn moves(diagonal moves) and edge cases
            else if (currentCandidateOffset == 7 && /*egde cases --->*/
                !(BoardUtils.EIGHTH_COLUMN[this.getPiecePosition()] && this.getPieceAlliance().isWhite() ||
                BoardUtils.FIRST_COLUMN[this.getPiecePosition()] && this.getPieceAlliance().isBlack())) {
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.getPieceAlliance() != pieceOnCandidate.getPieceAlliance()) {
                        legalMoves.add(new Move.PawnAttackMove(board, this,
                            candidateDestinationCoordinate, pieceOnCandidate));
                    }
                }
            }
            else if (currentCandidateOffset == 9 &&
                !(BoardUtils.EIGHTH_COLUMN[this.getPiecePosition()] && this.getPieceAlliance().isBlack() ||
                BoardUtils.FIRST_COLUMN[this.getPiecePosition()] && this.getPieceAlliance().isWhite())) {
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.getPieceAlliance() != pieceOnCandidate.getPieceAlliance()) {
                        legalMoves.add(new Move.PawnAttackMove(board, this,
                            candidateDestinationCoordinate, pieceOnCandidate));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Pawn movePiece(Move move) {
        // this will create a new Pawn in the new location.
        return new Pawn(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }
}
