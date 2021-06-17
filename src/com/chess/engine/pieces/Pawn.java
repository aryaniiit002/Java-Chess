package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

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

    Pawn(final int piecePossition, final Alliance pieceAlliance) {
        super(piecePossition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationCoordinate =
                this.piecePosition + this.pieceAlliance.getDirection() * currentCandidateOffset;
            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate);
            }
            else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                    ((BoardUtils.SECOND_ROW[this.piecePosition] && this.pieceAlliance.isBlack()) ||
                     (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.pieceAlliance.isWhite()))) {
                final int behindCandidateDestinationCoordinate =
                        this.piecePosition + this.pieceAlliance.getDirection() * 8;
                if(!board.getTile(candidateDestinationCoordinate).isTileOccupied()
                    && !board.getTile(behindCandidateDestinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate);
                }
            }
        }
        return null;
    }
}
