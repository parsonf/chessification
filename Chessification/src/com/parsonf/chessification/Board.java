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
				board[row][col] = new Space();
			}
		}
	}

	// Methods -------------------------------------------------------------
	public Space getSpace(Coord coord) {
		if (!isValidCoord(coord)) {
			return null;
		}
		return board[coord.col][coord.row];
	}
	
	@Override
	public Object clone() {
		Board clone = new Board();
		// TODO finish board clone.
		return clone;
	}

	// Getters/Setters -----------------------------------------------------
	
	public boolean isValidCoord(Coord coord) {
		if (coord.getRow() < Coord.ROW_1 || coord.getRow() > Coord.ROW_8) {
			try {
				throw new Exception("Board.getPiece(): row out of bounds! row=" + coord.getRow() + ". ");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		if (coord.getCol() < Coord.COL_A || coord.getCol() > Coord.COL_H) {
			try {
				throw new Exception("Board.getPiece(): col out of bounds! col=" + coord.getCol() + ". ");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}

	public void move(Move move, boolean isActualMove) {
		// TODO handle en passant somehow. may need to redo some of this implementation.
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
		// TODO if castling, move the rook too.
		// TODO improve pawn promotion to choice.
		if (pieceToMove instanceof Pawn && move.getTo().getRow() == Coord.ROW_8) {
			getSpace(move.getTo()).pickUpPiece();
			Piece promotedPawn = new Queen(pieceToMove.getColor());
			getSpace(move.getTo()).setPiece(promotedPawn);
		}
	}

	public boolean isInCheck(boolean colorToCheckIfInCheck) {
		// TODO impl isInCheck
		return false;
	}

	public boolean coordThreatened(Coord coord, boolean colorToCheckIfUnderThreat) {
		// TODO Auto-generated method stub
		return false;
	}

}
