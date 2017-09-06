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

	@Override
	public Set<Move> getAvailableMoves(Board board, Coord pos) {
		Set<Move> moves = new HashSet<Move>();
		Coord[] possibleMoves = new Coord[] {
				new Coord(-1, 1),  new Coord(0,1),  new Coord(1, 1),
				new Coord(-1, 0),					new Coord(1, 0),
				new Coord(-1,-1), new Coord(0,-1),  new Coord(1, -1)
		};
		for (Coord possibleMove: possibleMoves) {
			Coord moveToCoord = pos.add(possibleMove);
			if(canMakeMove(board, moveToCoord)) {
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
