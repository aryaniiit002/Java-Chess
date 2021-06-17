package com.chess.engine.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableList;

public final class Board {

    private final List<Tile> gameBoard;

    private Board(final Builder builder) {
        this.gameBoard = createGameBoard(builder);
    }

    private List<Tile> createGameBoard(final Builder builder) {
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        /**
         * In this for loop {from our map} the the piece associated for tile id and create a tile from it.
         * Basic way to create a board.
         */
        for(int i=0; i < BoardUtils.NUM_TILES; i++) {
            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public Tile getTile(final int tileCoordinate) {
        return null;
    }

    public Piece getPiece(int candidateDestinationCoordinate) {
        return null;
    }

    // create the initial standard position of chessBoard.
    public static Board createStandardBoard() {
        return STANDARD_BOARD;
    }

    public static class Builder {

        /**
         * We make the tile id {Integer} of a chessBoard to a given piece {Piece} on that tile id.
         */
        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker; // To keep track of person to move (person whose turn it is to move on chessBoard)

        public Builder() {
            this.boardConfig = new HashMap<>(32, 1.0f);
        }

        public Builder setPiece(final Piece piece) {
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}
