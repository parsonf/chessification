package com.parsonf.chessification.pieces;

import java.util.HashSet;
import java.util.Set;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Move;
import com.parsonf.chessification.Space;

public abstract class Piece {
	protected boolean color;
	protected boolean hasMoved;

	// Constructors --------------------------------------------------------
	public Piece(boolean color) {
		this.color = color;
		this.hasMoved = false;
	}
	
	// Methods -------------------------------------------------------------
	/**
	 * Given a board, assumes piece at pos can move in the direction
	 * of vector, and looks in that direction until it finds a friendly,
	 * an enemy, or the edge of the board.
	 * @param board
	 * @param pos
	 * @param vector
	 * @return
	 */	
	public Set<Move> lineMovement(Board board, Coord pos, Coord vector) {
		if (!Board.isValidCoord(pos)) {
			throw new IllegalArgumentException("Given position is not on the board! pos: " + pos.toString());
		}
		final boolean vectorColIsValid = Math.abs(vector.getCol()) < 2;
		final boolean vectorRowIsValid = Math.abs(vector.getRow()) < 2;
		final boolean vectorIsValid = !(vector.getCol() == 0 && vector.getRow() == 0);
		if (!vectorColIsValid || !vectorRowIsValid) {
			throw new IllegalArgumentException("Vector does not need magnitude, just direction. vector: " + vector.toString());
		} else if (!vectorIsValid) {
			throw new IllegalArgumentException("Vector needs a direction! Cannot be (0,0).");
		}
		Set<Move> moves = new HashSet<Move>();
		Coord moveToPos = pos.add(vector);
		// if the position to move to is not valid, return empty moves.
		if (!Board.isValidCoord(moveToPos)) {
			return moves;
		}
		// if there was no piece at this space, then it is a valid move for line movement.
		// therefore, add it, and continue looking in that direction for more moves.
		if (!board.getSpace(moveToPos).isOccupied()) {
			moves.add(new Move(pos, moveToPos));
			moves.addAll(lineMovement(board, moveToPos, vector));
			return moves;
		} else {
			// if there is a friendly piece at this space, then you cannot move there.
			// but if it is NOT friendly color, then it is ENEMY color, and you CAN move there, but no further.
			if (board.getSpace(moveToPos).getPiece().getColor() != color) {
				moves.add(new Move(pos, moveToPos));
			} 
		}
		return moves;
	}
	
	public boolean canMakeMove(Board board, Coord pos, Coord coordMovingTo) {
		final boolean NOT_A_VALID_SPACE = false;
		final boolean SPACE_OCCUPIED_BY_OWN_COLOR = false;
		final boolean SPACE_IS_VACANT = true;
		final boolean CAN_CAPTURE_ENEMY = true;
		
		if (Board.isValidCoord(coordMovingTo)) {
			Space space = board.getSpace(coordMovingTo);
			if (space.isOccupied()) {
				if (space.getPiece().getColor() != color) {
					return CAN_CAPTURE_ENEMY;
				} else {
					return SPACE_OCCUPIED_BY_OWN_COLOR;
				}
			} else {
				return SPACE_IS_VACANT;
			}
		} else {
			return NOT_A_VALID_SPACE;
		}
	}
	
	public abstract Set<Move> getAvailableMoves(Board board, Coord pos);

	// Getters/Setters -----------------------------------------------------
	public boolean getColor() {
		return color;
	}
	
	public void setColor(boolean color) {
		this.color = color;
	}

	public void setHasMoved() {
		hasMoved = true;
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}

}