package com.parsonf.chessification.pieces;

import java.util.HashSet;
import java.util.Set;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Move;

public class Pawn extends Piece {
	private boolean hasMoved;

	// Constructors --------------------------------------------------------
	public Pawn(boolean color) {
		super(color);
		hasMoved = false;
	}
	

	// Methods -------------------------------------------------------------

	@Override
	public Set<Move> getAvailableMoves(Board board, Coord pos) {
		Set<Move> moves = new HashSet<Move>();
		Coord[] possibleMoves = new Coord[] {
			new Coord(0,1),
			new Coord(0,2),
			new Coord(1,1),
			new Coord(-1,1)
		};
		for (Coord possibleMove: possibleMoves) {
			if (canMakeMove(board, pos, possibleMove)) {
				moves.add(new Move(pos, possibleMove));
			}
		}
		return moves;
	}
	
	@Override
	public boolean canMakeMove(Board board, Coord pos, Coord coordMovingTo) {
		final boolean NOT_A_VALID_SPACE = false;
		final boolean CANT_MOVE_TWO_ALREADY_MOVED = false;
		final boolean CANT_MOVE_TWO_BLOCKED_BY_PIECE = false;
		final boolean CANT_MOVE_ONE_BLOCKED = false;
		final boolean NOTHING_LEFT_TO_CAPTURE = false;
		final boolean NOTHING_RIGHT_TO_CAPTURE = false;
		final boolean NOT_A_VALID_PAWN_MOVE = false;
		final boolean CAN_MOVE_ONE = true;
		final boolean CAN_MOVE_TWO = true;
		final boolean CAN_CAPTURE_LEFT = true;
		final boolean CAN_CAPTURE_RIGHT = true;
		
		if (board.isValidCoord(coordMovingTo)) {
			if (coordMovingTo.subtract(pos).equals(new Coord(2,0))) {
				if (hasMoved) {
					return CANT_MOVE_TWO_ALREADY_MOVED;
				} else {
					if (!board.getSpace(pos.add(new Coord(1,0))).isOccupied()
					 && !board.getSpace(coordMovingTo).isOccupied()) {
						return CAN_MOVE_TWO;
					} else {
						return CANT_MOVE_TWO_BLOCKED_BY_PIECE;
					}
				}
			} else if (coordMovingTo.subtract(pos).equals(new Coord(1,0))) {
				if (board.getSpace(coordMovingTo).isOccupied()) {
					return CANT_MOVE_ONE_BLOCKED;
				} else {
					return CAN_MOVE_ONE;
				}
			} else if (coordMovingTo.subtract(pos).equals(new Coord(1,-1))) {
				if (board.getSpace(coordMovingTo).isOccupied()
				 && board.getSpace(coordMovingTo).getPiece().color != color) {
					return CAN_CAPTURE_LEFT;
				} else {
					return NOTHING_LEFT_TO_CAPTURE;
				}
			} else if (coordMovingTo.subtract(pos).equals(new Coord(1,1))) {
				if (board.getSpace(coordMovingTo).isOccupied()
				 && board.getSpace(coordMovingTo).getPiece().color != color) {
					return CAN_CAPTURE_RIGHT;
				} else {
					return NOTHING_RIGHT_TO_CAPTURE;
				}
			} else {
				return NOT_A_VALID_PAWN_MOVE;
			}
		} else {
			return NOT_A_VALID_SPACE;
		}
	}


	// Getters/Setters -----------------------------------------------------
	public boolean hasMoved() {
		return hasMoved;
	}

	public void moved() {
		this.hasMoved = true;
	}
}
