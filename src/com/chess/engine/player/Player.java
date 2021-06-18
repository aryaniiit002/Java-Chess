package com.chess.engine.player;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    protected final boolean isInCheck;

    Player(final Board board,
           final Collection<Move> legalMoves,
           final Collection<Move> opponentMoves) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
        this.isInCheck = !Player.calculateAttacksOnTile(this.PlayerKing().getPiecePosition(), opponentMoves).isEmpty();
    }
/* We are not defining the isInCheck in constructor bcoz we further need to investigate all other player moves
 for which we use "hasEscapeMoves()" method.
*/

    private static Collection<Move> calculateAttacksOnTile(final int piecePosition,
                                                   final Collection<Move> moves) {
        return moves.stream()
                    .filter(move -> move.getDestinationCoordinate() == piecePosition)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    /**
     * this method will ensure that there is a king for the player on the board.
     */
    protected final King establishKing() {
        for(final Piece piece : getActivePieces()) {
            if(piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Should not reach here! Not a valid board!!");
    }

    public boolean isMoveLegal(final Move move) {
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck() {
        return this.isInCheck && !hasEscapeMoves();
    }

    /**
     * Player is not in check currently and doesn't have any escape moves
     * (means any move we make may lead to checking position).
     */
    public boolean isInStaleMate() {
        return !isInCheck && !hasEscapeMoves();
    }

    /**
     * In order to calculate whether king can escape, we will go through each of the player legal moves
     * and we are going to make those moves on imaginary board, then we look at the board and
     * if we can make the move we return true otherwise (if it's a illegal move) we return false.
     *
     * @return whether king can escape or not.
     */
    private boolean hasEscapeMoves() {
        return this.legalMoves.stream()
                              .anyMatch(move -> makeMove(move)
                              .getMoveStatus().isDone());
    }

    public boolean isInCheckMate() {
        return false;
    }

    public boolean isCastled() {
        return false;
    }

    public MoveTransition makeMove(final Move move) {
        return null;
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
    protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals,
                                                             Collection<Move> opponentLegals);
}
