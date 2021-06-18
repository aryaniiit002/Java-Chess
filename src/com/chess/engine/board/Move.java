package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move {
    protected final Board board;
    private final int destinationCoordinate;
    protected final Piece movedPiece;

    private Move(final Board board,
                 final Piece pieceMoved,
                 final int destinationCoordinate) {
        this.board = board;
        this.destinationCoordinate = destinationCoordinate;
        this.movedPiece = pieceMoved;
    }

    private Move(final Board board,
                 final int destinationCoordinate) {
        this.board = board;
        this.destinationCoordinate = destinationCoordinate;
        this.movedPiece = null;
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public abstract Board execute();

    public static final class MajorMove extends Move {

        public MajorMove(final Board board,
                               final Piece pieceMoved,
                               final int destinationCoordinate) {
            super(board, pieceMoved, destinationCoordinate);
        }

        /**
         * We use the board builder it's gonna help us materialize a new board to return from
         * execute. we will traverse through all of the current player, through all the pieces and
         * for all the pieces that aren't the moved piece.
         * We want to place them on the new board without any change.
         *
         * @return new board
         */
        @Override
        public Board execute() {
            final Board.Builder builder = new Board.Builder();

            for(final Piece piece : this.board.currentPlayer().getActivePieces()) {
                // TODO hashcode and equals for pieces
                if(!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }

            // For enemy pieces
            for(final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }
            // Move the moved piece
            builder.setPiece(this.movedPiece.movePiece(this));
            // then we set the moveMaker to the opponent.
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());

            return builder.build();
        }
    }

    public static final class AttackMove extends Move {

        private final Piece attackedPiece;
        public AttackMove(final Board board,
                               final Piece pieceMoved,
                               final int destinationCoordinate,
                          final Piece attackedPiece) {
            super(board, pieceMoved, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }

        public Piece getAttackedPiece() {
            return attackedPiece;
        }

        @Override
        public Board execute() {
            return null;
        }
    }
}
