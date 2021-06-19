package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;
import com.google.common.collect.ImmutableList;

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board, final Collection<Move> whiteStandardMoves,
                       final Collection<Move> blackStandardMoves) {
        super(board, whiteStandardMoves, blackStandardMoves);
    }
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals) {
        if (!hasCastleOpportunities()) {
            return Collections.emptyList();
        }

        final List<Move> kingCastles = new ArrayList<>();

        if (this.playerKing.isFirstMove() && this.playerKing.getPiecePosition() == 60 && !this.isInCheck()) {

            //whites king side castle
            if (!this.board.getTile(61).isTileOccupied()
                && !this.board.getTile(62).isTileOccupied()) {

                final Tile rookTile = this.board.getTile(63);

                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {

                    if (Player.calculateAttacksOnTile(61, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(62, opponentLegals).isEmpty() &&
                        rookTile.getPiece().getPieceType().isRook()) {
                        /*if(!BoardUtils.isKingPawnTrap(this.board, this.playerKing, 52)) {
                            kingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 62, (Rook) kingSideRook, kingSideRook.getPiecePosition(), 61));
                        }*/
                        kingCastles.add(null);
                    }
                }
            }

            //whites queen side castle
            if (!this.board.getTile(59).isTileOccupied() &&
                !this.board.getTile(58).isTileOccupied() &&
                !this.board.getTile(57).isTileOccupied()) {

                final Tile queenSideRook = this.board.getTile(56);
                if (queenSideRook.isTileOccupied() && queenSideRook.getPiece().isFirstMove()) {

                    kingCastles.add(null);
                    /*if(Player.calculateAttacksOnTile(58, opponentLegals).isEmpty() &&
                       Player.calculateAttacksOnTile(59, opponentLegals).isEmpty() &&
                        queenSideRook.getPieceType() == ROOK) {

                        if(!BoardUtils.isKingPawnTrap(this.board, this.playerKing, 52)) {
                            kingCastles.add(new Move.QueenSideCastleMove(this.board, this.playerKing, 58,
                                (Rook) queenSideRook, queenSideRook.getPiecePosition(), 59));
                        }
                     }*/
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }
}
