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
        this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();
    }
/* We are not defining the isInCheck in constructor bcoz we further need to investigate all other player moves
 for which we use "hasEscapeMoves()" method.
*/

    public King getPlayerKing() {
        return this.playerKing;
    }

    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    protected static Collection<Move> calculateAttacksOnTile(final int piecePosition,
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

    public boolean isCastled() {
        return this.playerKing.isCastled();
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

    /**
     * We come here to ask if the move is illegal or not part of collection of legal moves that the player has then
     * do the move transition that your return does not take us to new board, it returns the same board and
     * the move status is illegal then next is
     * use the move to polymorphically execute the move and return us a new board that we transition to and we if
     * there any attacks on current player king, if there are then we shouldnt be able to make that move.
     *
     * @param move current player move
     * @return if those attacks are not empty agian return this board and a move status of legal player and
     *         check otherwise return the move transition board wrapped in a new move transition.
     */
    public MoveTransition makeMove(final Move move) {
        if (!this.legalMoves.contains(move)) {
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionedBoard = move.execute();

        final Collection<Move> kingAttacks = calculateAttacksOnTile(transitionedBoard.currentPlayer().
            getOpponent().playerKing.getPiecePosition(), transitionedBoard.currentPlayer().legalMoves);

        if(!kingAttacks.isEmpty()) {
            return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        return new MoveTransition(transitionedBoard, move, MoveStatus.DONE);
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
    protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals,
                                                             Collection<Move> opponentLegals);

    protected boolean hasCastleOpportunities() {
        return !this.isInCheck && !this.playerKing.isCastled() &&
                (this.playerKing.isKingSideCastleCapable() || this.playerKing.isQueenSideCastleCapable());
    }
}
