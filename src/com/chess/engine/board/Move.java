package com.chess.engine.board;

import com.chess.engine.board.Board.Builder;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

public abstract class Move {
    protected final Board board;
    protected final int destinationCoordinate;
    protected final Piece movedPiece;
    protected final boolean isFirstMove;

    private static final Move NULL_MOVE = new NullMove();

    private Move(final Board board,
                 final Piece pieceMoved,
                 final int destinationCoordinate) {
        this.board = board;
        this.destinationCoordinate = destinationCoordinate;
        this.movedPiece = pieceMoved;
        this.isFirstMove = pieceMoved.isFirstMove();
    }

    private Move(final Board board,
                 final int destinationCoordinate) {
        this.board = board;
        this.destinationCoordinate = destinationCoordinate;
        this.movedPiece = null;
        this.isFirstMove = false;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + this.destinationCoordinate;
        result = 31 * result + this.movedPiece.hashCode();
        result = 31 * result + this.movedPiece.getPiecePosition();
        result += isFirstMove ? 1 : 0;
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Move)) {
            return false;
        }
        final Move otherMove = (Move) other;
        return getCurrentCoordinate() == otherMove.getCurrentCoordinate() &&
               getDestinationCoordinate() == otherMove.getDestinationCoordinate() &&
               getMovedPiece().equals(otherMove.getMovedPiece());
    }

    public int getCurrentCoordinate() {
        return this.movedPiece.getPiecePosition();
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public boolean isCastlingMove() {
        return false;
    }

    public boolean isAttack() {
        return false;
    }

    public Piece getAttackedPiece() {
        return null;
    }

    /**
     * We use the board builder it's gonna help us materialize a new board to return from
     * execute. we will traverse through all of the current player, through all the pieces and
     * for all the pieces that aren't the moved piece.
     * We want to place them on the new board without any change.
     *
     * @return new board
     */
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

    public static final class MajorMove extends Move {

        public MajorMove(final Board board,
                               final Piece pieceMoved,
                               final int destinationCoordinate) {
            super(board, pieceMoved, destinationCoordinate);
        }

        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof MajorMove && super.equals(other);
        }

        @Override
        public String toString() {
            return movedPiece.getPieceType().toString() +
                   BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
        }
    }

    public static class AttackMove extends Move {

        private final Piece attackedPiece;
        public AttackMove(final Board board,
                               final Piece pieceMoved,
                               final int destinationCoordinate,
                          final Piece attackedPiece) {
            super(board, pieceMoved, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }

        @Override
        public int hashCode() {
            return this.attackedPiece.hashCode() + super.hashCode();
        }

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AttackMove)) {
                return false;
            }
            final AttackMove otherAttackMove = (AttackMove) other;
            return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
        }

        public Piece getAttackedPiece() {
            return this.attackedPiece;
        }

        public boolean isAttack() {
            return false;
        }

        public boolean isCastlingMove() {
            return false;
        }

        @Override
        public Board execute() {
            return null;
        }
    }

    public static class PawnMove extends Move {

        public PawnMove(final Board board,
                        final Piece pieceMoved,
                        final int destinationCoordinate) {
            super(board, pieceMoved, destinationCoordinate);
        }
    }

    public static class PawnAttackMove extends AttackMove {

        public PawnAttackMove(final Board board,
                              final Piece pieceMoved,
                              final int destinationCoordinate,
                              final Piece pieceAttacked) {
            super(board, pieceMoved, destinationCoordinate, pieceAttacked);
        }
    }

    public static class PawnEnPassantAttack extends PawnAttackMove {

        public PawnEnPassantAttack(final Board board,
                                   final Piece pieceMoved,
                                   final int destinationCoordinate,
                                   final Piece pieceAttacked) {
            super(board, pieceMoved, destinationCoordinate, pieceAttacked);
        }
/*
        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof PawnEnPassantAttack && super.equals(other);
        }

        @Override
        public Board execute() {
            final Board.Builder builder = new Builder();
            this.board.currentPlayer().getActivePieces().stream().filter(piece -> !this.movedPiece.equals(piece)).forEach(builder::setPiece);
            this.board.currentPlayer().getOpponent().getActivePieces().stream().filter(piece -> !piece.equals(this.getAttackedPiece())).forEach(builder::setPiece);
            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            builder.setMoveTransition(this);
            return builder.build();
        }

        @Override
        public Board undo() {
            final Board.Builder builder = new Builder();
            this.board.getAllPieces().forEach(builder::setPiece);
            builder.setEnPassantPawn((Pawn)this.getAttackedPiece());
            builder.setMoveMaker(this.board.currentPlayer().getAlliance());
            return builder.build();
        }*/
    }

    public static class PawnJump
            extends Move {

        public PawnJump(final Board board,
                        final Pawn pieceMoved,
                        final int destinationCoordinate) {
            super(board, pieceMoved, destinationCoordinate);
        }

        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof PawnJump && super.equals(other);
        }

        @Override
        public Board execute() {
            final Board.Builder builder = new Builder();
            this.board.currentPlayer().getActivePieces().stream().filter(piece -> !this.movedPiece.equals(piece)).forEach(builder::setPiece);
            this.board.currentPlayer().getOpponent().getActivePieces().forEach(builder::setPiece);
            final Pawn movedPawn = (Pawn)this.movedPiece.movePiece(this);
            builder.setPiece(movedPawn);
            builder.setEnPassantPawn(movedPawn);
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            //builder.setMoveTransition(this);
            return builder.build();
        }

        @Override
        public String toString() {
                return BoardUtils.getPositionAtCoordinate(this.destinationCoordinate);
        }

    }

    abstract static class CastleMove extends Move {

        protected final Rook castleRook;
        protected final int castleRookStart;
        protected final int castleRookDestination;

        CastleMove(final Board board,
                   final Piece pieceMoved,
                   final int destinationCoordinate,
                   final Rook castleRook,
                   final int castleRookStart,
                   final int castleRookDestination) {
            super(board, pieceMoved, destinationCoordinate);
            this.castleRook = castleRook;
            this.castleRookStart = castleRookStart;
            this.castleRookDestination = castleRookDestination;
        }

        Rook getCastleRook() {
            return this.castleRook;
        }

        @Override
        public boolean isCastlingMove() {
            return true;
        }

        @Override
        public Board execute() {

            final Board.Builder builder = new Builder();
            for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)) {
                    builder.setPiece(piece);
                }
            }

            // We move the king (by "movePiece" method) to its destination location and the rook.
            // and we have manually a new rook that's sitting on the castle side.
            for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }
            builder.setPiece(this.movedPiece.movePiece(this));
            // TODO look into the 1st move on normal pieces.
            builder.setPiece(new Rook(this.castleRook.getPieceAlliance(), this.castleRookDestination));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());

            return builder.build();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + this.castleRook.hashCode();
            result = prime * result + this.castleRookDestination;
            return result;
        }

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CastleMove)) {
                return false;
            }
            final CastleMove otherCastleMove = (CastleMove) other;
            return super.equals(otherCastleMove) && this.castleRook.equals(otherCastleMove.getCastleRook());
        }

    }

    public static class KingSideCastleMove
            extends CastleMove {

        public KingSideCastleMove(final Board board,
                                  final Piece pieceMoved,
                                  final int destinationCoordinate,
                                  final Rook castleRook,
                                  final int castleRookStart,
                                  final int castleRookDestination) {
            super(board, pieceMoved, destinationCoordinate, castleRook, castleRookStart,
                castleRookDestination);
        }

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KingSideCastleMove)) {
                return false;
            }
            final KingSideCastleMove otherKingSideCastleMove = (KingSideCastleMove) other;
            return super.equals(otherKingSideCastleMove) && this.castleRook.equals(otherKingSideCastleMove.getCastleRook());
        }

        /**
         * PGM convention for printing out a king side castle.
         *
         * @return string 0-0
         */
        @Override
        public String toString() {
            return "O-O";
        }
    }

     public static class QueenSideCastleMove
            extends CastleMove {

         public QueenSideCastleMove(final Board board,
                                    final Piece pieceMoved,
                                    final int destinationCoordinate,
                                    final Rook castleRook,
                                    final int castleRookStart,
                                    final int rookCastleDestination) {
             super(board, pieceMoved, destinationCoordinate, castleRook, castleRookStart,
                 rookCastleDestination);
         }

         @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof QueenSideCastleMove)) {
                return false;
            }
            final QueenSideCastleMove otherQueenSideCastleMove = (QueenSideCastleMove) other;
            return super.equals(otherQueenSideCastleMove) &&
                this.castleRook.equals(otherQueenSideCastleMove.getCastleRook());
        }

        /**
         * PGM convention for printing out a Queen side castle.
         * That's we have subclasses like this.
         *
         * @return string 0-0-0
         */
        @Override
        public String toString() {
            return "O-O-O";
        }
     }

     private static class NullMove
            extends Move {

        private NullMove() {
            super(null, -1);
        }

        @Override
        public int getCurrentCoordinate() {
            return -1;
        }

        @Override
        public int getDestinationCoordinate() {
            return -1;
        }

        @Override
        public Board execute() {
            throw new RuntimeException("cannot execute null move!");
        }

        @Override
        public String toString() {
            return "Null Move";
        }
    }

    public static class MoveFactory {

        private static final Move NULL_MOVE = new NullMove();

        private MoveFactory() {
            throw new RuntimeException("Not instantiatable!");
        }

        public static Move getNullMove() {
            return NULL_MOVE;
        }

        /**
         * Given a board and a "from" and "to" coordinate and with that we get the available move on the board.
         *
         * @param board available board
         * @param currentCoordinate "from" coordinate
         * @param destinationCoordinate "to" coordinate
         * @return available move on the board.
         */
        public static Move createMove(final Board board,
                                      final int currentCoordinate,
                                      final int destinationCoordinate) {
            for (final Move move : board.getAllLegalMoves()) {
                if (move.getCurrentCoordinate() == currentCoordinate &&
                    move.getDestinationCoordinate() == destinationCoordinate) {
                    return move;
                }
            }
            return NULL_MOVE;
        }
    }
}
