/*
 * We have defined a class that represents a chess tile.
 * It takes as its as a prameter a tile coordinate which it establishes and the key methods on there are whether the
 * tile is occupied or not and to retreive the piece on the tile.
 * We have two subclasses of this tile {empty tile, occupied tile} which define those behaviours.
 */

package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
/**
 * We make this class abstract and we can't instantiate this class but we can instantiate concrete
 * subclasses.
 */
abstract class Tile {
    public static final int INT = 64;
    private final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for(int i = 0; i < INT; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        // This ImmutableMap is a part of guava library, which is 3rd party lib provided by google.
        // We can also use Collectins.unmodifiableMap().
        // Guava is less error-prone.
        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordinate, Piece piece) {
        if (piece != null) {
            return new OccupiedTile(tileCoordinate, piece);
        }
        return EMPTY_TILES_CACHE.get(tileCoordinate);
    }
    private Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

        public int getTileCoordinate() {
        return tileCoordinate;
    }

    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile {
        EmptyTile(final int coordinate) {
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile {
        private Piece pieceOnTile;

        private OccupiedTile(int tileCoordinate , Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        public Piece getPieceOnTile() {
            return pieceOnTile;
        }

        public void setPieceOnTile(Piece pieceOnTile) {
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return pieceOnTile;
        }
    }
}
