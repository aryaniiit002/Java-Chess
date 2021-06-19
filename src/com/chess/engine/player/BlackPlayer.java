package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.QueenSideCastleMove;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;
import com.google.common.collect.ImmutableList;

public class BlackPlayer extends Player{
    public BlackPlayer(final Board board, final Collection<Move> whiteStandardMoves,
                       final Collection<Move> blackStandardMoves) {
        super(board, blackStandardMoves, whiteStandardMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentLegals) {
        if (!hasCastleOpportunities()) {
            return Collections.emptyList();
        }
        final List<Move> kingCastles = new ArrayList<>();

        if (this.playerKing.isFirstMove() && this.playerKing.getPiecePosition() == 4 && !this.isInCheck()) {

            //whites king side castle
            if (!this.board.getTile(5).isTileOccupied()
                && !this.board.getTile(6).isTileOccupied()) {
                final Tile kingSideRook = this.board.getTile(7);

                if (kingSideRook.isTileOccupied() && kingSideRook.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(5, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(6, opponentLegals).isEmpty() &&
                        kingSideRook.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new Move.KingSideCastleMove(this.board, this.playerKing,
                                                    6, (Rook) kingSideRook.getPiece(),
                                                    kingSideRook.getTileCoordinate(),
                                                    5));
                    }
                }
            }

            //whites queen side castle
            if (!this.board.getTile(1).isTileOccupied() &&
                !this.board.getTile(2).isTileOccupied() &&
                !this.board.getTile(3).isTileOccupied()) {

                final Tile queenSideRook = this.board.getTile(0);
                if (queenSideRook.isTileOccupied() && queenSideRook.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(3, opponentLegals).isEmpty() &&
                            Player.calculateAttacksOnTile(2, opponentLegals).isEmpty() &&
                            queenSideRook.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing,
                                                    2, (Rook) queenSideRook.getPiece(),
                                                    queenSideRook.getTileCoordinate(),
                                                    3));
                    }
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }
}
