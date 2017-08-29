package com.parsonf.chessification;

import java.util.Set;

import com.parsonf.chessification.pieces.Bishop;
import com.parsonf.chessification.pieces.King;
import com.parsonf.chessification.pieces.Knight;
import com.parsonf.chessification.pieces.Pawn;
import com.parsonf.chessification.pieces.Piece;
import com.parsonf.chessification.pieces.Queen;
import com.parsonf.chessification.pieces.Rook;
import com.parsonf.chessification.players.Player;

public class Board {

	private Space[][] board;

	// Constructors --------------------------------------------------------
	public Board() {
		board = new Space[8][8];
		for (int col = Coord.COL_MIN; col <= Coord.COL_MAX; col++) {
			for (int row = Coord.ROW_MIN; row <= Coord.ROW_MAX; row++) {
				board[col - 1][row - 1] = new Space();
			}
		}
		reset();
	}

	// Methods -------------------------------------------------------------
	public Space getSpace(Coord coord) {
		if (!isValidCoord(coord)) {
			return null;
		}
		return getSpace(coord.col, coord.row);
	}
	
	public Board copy() {
		Board clone = new Board();

		for (int col = Coord.COL_MIN; col <= Coord.COL_MAX; col++) {
			for (int row = Coord.ROW_MIN; row <= Coord.ROW_MAX; row++) {
				if (getSpace(col, row).isOccupied()) {
					clone.board[col - 1][row - 1] = new Space(getSpace(col, row).getPiece().copy());
				} else {
					clone.board[col - 1][row - 1] = new Space();
				}
			}
		}

		return clone;
	}

	/**
	 * Sets the board up for a standard game of chess.
	 */
	public void reset() {
		// clear the board.
		clear();

		// set pieces on board.
		for (int col = Coord.COL_MIN; col <= Coord.COL_MAX; col++) {
			Piece blackPawn = new Pawn(Color.BLACK);
			getSpace(col, Coord.ROW_7).setPiece(blackPawn);
			Piece whitePawn = new Pawn(Color.WHITE);
			getSpace(col, Coord.ROW_2).setPiece(whitePawn);
			switch (col) {
			case Coord.COL_A:
			case Coord.COL_H:
				getSpace(col, Coord.ROW_1).setPiece(new Rook(Color.WHITE));
				getSpace(col, Coord.ROW_8).setPiece(new Rook(Color.BLACK));
				break;
			case Coord.COL_B:
			case Coord.COL_G:
				getSpace(col, Coord.ROW_1).setPiece(new Knight(Color.WHITE));
				getSpace(col, Coord.ROW_8).setPiece(new Knight(Color.BLACK));
				break;
			case Coord.COL_C:
			case Coord.COL_F:
				getSpace(col, Coord.ROW_1).setPiece(new Bishop(Color.WHITE));
				getSpace(col, Coord.ROW_8).setPiece(new Bishop(Color.BLACK));
				break;
			case Coord.COL_D:
				getSpace(col, Coord.ROW_1).setPiece(new Queen(Color.WHITE));
				getSpace(col, Coord.ROW_8).setPiece(new Queen(Color.BLACK));
				break;
			case Coord.COL_E:
				getSpace(col, Coord.ROW_1).setPiece(new King(Color.WHITE));
				getSpace(col, Coord.ROW_8).setPiece(new King(Color.BLACK));
				break;
			default:
				throw new RuntimeException("Board.reset(): col is invalid.");
			}
		}
	}

	/**
	 * Removes all the pieces off the board.
	 */
	public void clear() {
		for (int col = Coord.COL_MIN; col <= Coord.COL_MAX; col++) {
			for (int row = Coord.ROW_MIN; row <= Coord.ROW_MAX; row++) {
				Space space = getSpace(col, row);
				if (space.isOccupied()) {
					space.pickUpPiece();
				}
			}
		}
	}

	/**
	 * To minimize the amount of times needing to zero-adjust.
	 * 
	 * @param col
	 * @param row
	 * @return
	 */
	private Space getSpace(int col, int row) {
		return board[col - 1][row - 1];
	}

	// Getters/Setters -----------------------------------------------------

	public static boolean isValidCoord(Coord coord) {
		if (coord.getRow() < Coord.ROW_MIN || coord.getRow() > Coord.ROW_MAX) {
			return false;
		}
		if (coord.getCol() < Coord.COL_MIN || coord.getCol() > Coord.COL_MAX) {
			return false;
		}
		return true;
	}

	public void move(Move move, boolean isActualMove) {
		// TODO improve: handle en passant somehow. may need to redo some of this
		// implementation.
		Piece pieceToMove = getSpace(move.getFrom()).pickUpPiece();
		if (isActualMove) {
			pieceToMove.setHasMoved();
		}
		if (getSpace(move.getTo()).isOccupied()) {
			// captured piece.
			getSpace(move.getTo()).pickUpPiece(); 
			getSpace(move.getTo()).setPiece(pieceToMove);
		}
		// if castling, move the rook too.
		if (move.getFrom().equals(new Coord(Coord.COL_E, Coord.ROW_1))) {
			if (move.getTo().equals(new Coord(Coord.COL_C, Coord.ROW_1))) {
				move(new Move(new Coord(Coord.COL_A, Coord.ROW_1), new Coord(Coord.COL_D, Coord.ROW_1)), true);
			} else if (move.getTo().equals(new Coord(Coord.COL_G, Coord.ROW_1))) {
				move(new Move(new Coord(Coord.COL_H, Coord.ROW_1), new Coord(Coord.COL_F, Coord.ROW_1)), true);
			}
		} else if (move.getFrom().equals(new Coord(Coord.COL_E, Coord.ROW_8))) {
			if (move.getTo().equals(new Coord(Coord.COL_C, Coord.ROW_8))) {
				move(new Move(new Coord(Coord.COL_A, Coord.ROW_8), new Coord(Coord.COL_D, Coord.ROW_8)), true);
			} else if (move.getTo().equals(new Coord(Coord.COL_G, Coord.ROW_8))) {
				move(new Move(new Coord(Coord.COL_H, Coord.ROW_8), new Coord(Coord.COL_F, Coord.ROW_8)), true);
			}
		}
		// TODO improve: pawn promotion to choice.
		if (pieceToMove instanceof Pawn && move.getTo().getRow() == Coord.ROW_8) {
			getSpace(move.getTo()).pickUpPiece();
			Piece promotedPawn = new Queen(pieceToMove.getColor());
			getSpace(move.getTo()).setPiece(promotedPawn);
		}
	}

	public boolean isPlayerInCheck(Player player) {
		// if the destination of any move is a king of any color.
		// note: we ignore castle because castling cannot possibly capture opponent king,
		// which is fortunate, because checking for it would likely result in
		// another infinite loop.
		boolean isPlayerInCheck = false;
		Set<Move> opponentMoves = player.getAllLegalMoves(this, player.getOpponent(), Player.IGNORE_CHECK, Player.IGNORE_CASTLE);
		for (Move move : opponentMoves) {
			Space space = getSpace(move.getTo());
			if (space.isOccupied() && space.getPiece() instanceof King) {
				isPlayerInCheck = true;
				break;
			}
		}
		return isPlayerInCheck;
	}

	/**
	 * Checks if a space is threatened by an opponent.
	 * 
	 * @param coord
	 * @param colorToCheckIfUnderThreat
	 * @return
	 */
	public boolean isPlayerThreatenedAtCoord(Player player, Coord coord) {
		boolean isThreatened = false;
		Set<Move> moves = player.getAllLegalMoves(this, player, Player.IGNORE_CHECK, Player.IGNORE_CASTLE);
		for (Move move : moves) {
			if (move.getTo().equals(coord)) {
				isThreatened = true;
				break;
			}
		}
		return isThreatened;
	}

}
