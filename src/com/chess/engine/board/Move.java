package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move {
    private final Board board;
    private final int destinationCoordinate;
    private final Piece movedPiece;

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

    public abstract Board execute();

    public static final class MajorMove extends Move {

        public MajorMove(final Board board,
                               final Piece pieceMoved,
                               final int destinationCoordinate) {
            super(board, pieceMoved, destinationCoordinate);
        }

        @Override
        public Board execute() {
            return null;
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
