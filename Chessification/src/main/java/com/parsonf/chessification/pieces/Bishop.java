package com.parsonf.chessification.pieces;

import java.util.HashSet;
import java.util.Set;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Move;

public class Bishop extends Piece {

	// Constructors --------------------------------------------------------
	public Bishop(boolean color) {
		super(color);
	}


	// Methods -------------------------------------------------------------
	
	@Override
	public Set<Move> getAvailableMoves(Board board, Coord pos) {
		Set<Move> moves = new HashSet<Move>();
		moves.addAll(lineMovement(board, pos, new Coord(1, 1)));
		moves.addAll(lineMovement(board, pos, new Coord(1, -1)));
		moves.addAll(lineMovement(board, pos, new Coord(-1, 1)));
		moves.addAll(lineMovement(board, pos, new Coord(-1, -1)));
		return moves;
	}
}
