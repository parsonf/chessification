package com.parsonf.chessification.pieces;

import java.util.HashSet;
import java.util.Set;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Move;

public class King extends Piece {

	// Constructors --------------------------------------------------------
	public King(boolean color) {
		super(color);
	}


	// Methods -------------------------------------------------------------

	/**
	 * Gets a list of all the legal moves, that is, without consideration
	 * of check.
	 */
	@Override
	public Set<Move> getAvailableMoves(Board board, Coord pos) {
		Set<Move> moves = new HashSet<Move>();
		Coord[] possibleMoves = new Coord[] {
				new Coord(-1, 1),  new Coord(0,1),  new Coord(1, 1),
				new Coord(-1, 0),					new Coord(1, 0),
				new Coord(-1,-1), new Coord(0,-1),  new Coord(1, -1)
		};
		boolean color = board.getSpace(pos).getPiece().getColor();
		for (Coord possibleMove: possibleMoves) {
			Coord moveToCoord = pos.add(possibleMove);
			if(board.isValidCoordNotOccupiedByFriendly(color, moveToCoord)) {
				moves.add(new Move(pos, moveToCoord));
			}
		}
		return moves;
	}


	@Override
	public Piece copy() {
		King king = new King(color);
		if (this.hasMoved()) {
			king.setHasMoved();
		}
		return king;
	}
}
