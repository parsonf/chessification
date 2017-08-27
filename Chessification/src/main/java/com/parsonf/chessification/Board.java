package com.parsonf.chessification;

import com.parsonf.chessification.pieces.Pawn;
import com.parsonf.chessification.pieces.Piece;
import com.parsonf.chessification.pieces.Queen;

public class Board {

	private Space[][] board;

	// Constructors --------------------------------------------------------
	public Board() {
		board = new Space[8][8];
		for (int col = Coord.COL_A; col <= Coord.COL_H; col++) {
			for (int row = Coord.ROW_1; row <= Coord.ROW_8; row++) {
				board[col-1][row-1] = new Space();
			}
		}
	}

	// Methods -------------------------------------------------------------
	public Space getSpace(Coord coord) {
		if (!isValidCoord(coord)) {
			return null;
		}
		return board[coord.col-1][coord.row-1];
	}
	
	@Override
	public Object clone() {
		Board clone = new Board();
		
		for (int col = Coord.COL_A; col <= Coord.COL_H; col++) {
			for (int row = Coord.ROW_1; row <= Coord.ROW_8; row++) {
				board[col-1][row-1] = new Space();
				if (this.board[col][row].isOccupied()) {
					//Piece piece = this.board[col-1][row-1].getPiece();
					// TODO Piece newPiece = new Piece(piece.getColor());
					
				} else {
					
				}
			}
		}
		
		return clone;
	}

	// Getters/Setters -----------------------------------------------------
	
	public static boolean isValidCoord(Coord coord) {
		if (coord.getRow() < Coord.ROW_1 || coord.getRow() > Coord.ROW_8) {
			return false;
		}
		if (coord.getCol() < Coord.COL_A || coord.getCol() > Coord.COL_H) {
			return false;
		}
		return true;
	}

	public void move(Move move, boolean isActualMove) {
		// TODO improve: handle en passant somehow. may need to redo some of this implementation.
		Piece pieceToMove = getSpace(move.getFrom()).pickUpPiece();
		if (isActualMove) {
			pieceToMove.setHasMoved();
		}
		if (getSpace(move.getTo()).isOccupied()) {
			@SuppressWarnings("unused")
			Piece capturedPiece = getSpace(move.getTo()).pickUpPiece();
			capturedPiece = null;
			getSpace(move.getTo()).setPiece(pieceToMove);
		}
		// TODO impl: if castling, move the rook too.
		// TODO improve: pawn promotion to choice.
		if (pieceToMove instanceof Pawn && move.getTo().getRow() == Coord.ROW_8) {
			getSpace(move.getTo()).pickUpPiece();
			Piece promotedPawn = new Queen(pieceToMove.getColor());
			getSpace(move.getTo()).setPiece(promotedPawn);
		}
	}

	public boolean isInCheck(boolean colorToCheckIfInCheck) {
		// TODO impl: isInCheck
		return false;
	}

	public boolean coordThreatened(Coord coord, boolean colorToCheckIfUnderThreat) {
		// TODO impl: coordThreatened
		return false;
	}

}
