package com.parsonf.chessification.pieces;

import java.util.HashSet;
import java.util.Set;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Move;

public class Queen extends Piece {

	// Constructors --------------------------------------------------------
	public Queen(boolean color) {
		super(color);
	}


	// Methods -------------------------------------------------------------
	
	@Override
	public Set<Move> getAvailableMoves(Board board, Coord pos) {
		Set<Move> moves = new HashSet<Move>();
		moves.addAll(lineMovement(board, pos, new Coord(0, 1)));
		moves.addAll(lineMovement(board, pos, new Coord(0, -1)));
		moves.addAll(lineMovement(board, pos, new Coord(-1, 0)));
		moves.addAll(lineMovement(board, pos, new Coord(1, 1)));
		moves.addAll(lineMovement(board, pos, new Coord(1, -1)));
		moves.addAll(lineMovement(board, pos, new Coord(-1, 1)));
		moves.addAll(lineMovement(board, pos, new Coord(-1, -1)));
		return moves;
	}

	@Override
	public Piece copy() {
		Queen queen = new Queen(color);
		if (this.hasMoved()) {
			queen.setHasMoved();
		}
		return queen;
	}

}
