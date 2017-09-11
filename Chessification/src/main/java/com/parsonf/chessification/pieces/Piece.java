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
			throw new IllegalArgumentException("Given position is not on the board! pos: " + pos.getCol() + ", " + pos.getRow());
		}
		final boolean vectorIsValid = !(vector.getCol() == 0 && vector.getRow() == 0);
		if (!vectorIsValid) {
			throw new IllegalArgumentException("Vector needs a direction! Cannot be (0,0).");
		}
		Set<Move> moves = new HashSet<Move>();
		
		Coord moveToPos = pos.add(vector);
		while (Board.isValidCoord(moveToPos)) {
			// if there was no piece at this space, then it is a valid move for line movement.
			// therefore, add it, and continue looking in that direction for more moves.
			if (!board.getSpace(moveToPos).isOccupied()) {
				moves.add(new Move(pos, moveToPos));
			} else {
				// this space is occupied, so we'll need to break out of this loop no matter what.
				// however, is this piece occupying this space friendly or foe?
				// if it's an enemy, we can move here but no further.
				if (board.getSpace(moveToPos).getPiece().getColor() != color) {
					moves.add(new Move(pos, moveToPos));
				}
				break;
			}
			moveToPos = moveToPos.add(vector);
		}
		return moves;
	}
	
	public abstract Piece copy();
	
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
	
	@Override
	public abstract String toString();

}
