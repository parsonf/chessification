package com.parsonf.chessification.pieces;

import java.util.HashSet;
import java.util.Set;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Move;

public class Rook extends Piece {

	// Constructors --------------------------------------------------------
	public Rook(boolean color) {
		super(color);
	}


	// Methods -------------------------------------------------------------
	
	@Override
	public Set<Move> getAvailableMoves(Board board, Coord pos) {
		Set<Move> moves = new HashSet<Move>();
		moves.addAll(lineMovement(board, pos, new Coord(0, 1)));
		moves.addAll(lineMovement(board, pos, new Coord(0, -1)));
		moves.addAll(lineMovement(board, pos, new Coord(1, 0)));
		moves.addAll(lineMovement(board, pos, new Coord(-1, 0)));
		return moves;
	}

	@Override
	public Piece copy() {
		Rook rook = new Rook(color);
		if (this.hasMoved()) {
			rook.setHasMoved();
		}
		return rook;
	}
	
	@Override
	public String toString() {
		String worb = this.color ? "w" : "b";
		return worb + "r";
	}
}
