package com.parsonf.chessification.pieces;

import java.util.HashSet;
import java.util.Set;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Move;

public class Knight extends Piece {
	
	// Constructors --------------------------------------------------------
	public Knight(boolean color) {
		super(color);
	}
	
	
	// Methods -------------------------------------------------------------
	@Override
	public Set<Move> getAvailableMoves(Board board, Coord pos) {
		Set<Move> moves = new HashSet<Move>();
		Coord[] possibleMoves = new Coord[] {
			new Coord(2, 1),
			new Coord(2, -1),
			new Coord(1, 2),
			new Coord(1, -2),
			new Coord(-1, 2),
			new Coord(-1, -2),
			new Coord(-2, 1),
			new Coord(-2, -1)
		};
		for (Coord possibleMove: possibleMoves) {
			Coord moveToCoord = pos.add(possibleMove);
			if (canMakeMove(board, pos, moveToCoord)) {
				moves.add(new Move(pos, possibleMove));
			}
		}
		return moves;
	}


	@Override
	public Piece copy() {
		Knight knight = new Knight(color);
		if (this.hasMoved()) {
			knight.setHasMoved();
		}
		return knight;
	}

	// Getters/Setters -----------------------------------------------------
	
	
}
