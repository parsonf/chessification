package com.parsonf.chessification.pieces;

import java.util.HashSet;
import java.util.Set;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Move;

public class Pawn extends Piece {

	// Constructors --------------------------------------------------------
	public Pawn(boolean color) {
		super(color);
	}
	

	// Methods -------------------------------------------------------------

	@Override
	public Set<Move> getAvailableMoves(Board board, Coord pos) {
		Set<Move> moves = new HashSet<Move>();
		moves.addAll(getCaptureMoves(board, pos));
		moves.addAll(getStandardMoves(board, pos));
		return moves;
	}
	
	private Set<Move> getCaptureMoves(Board board, Coord pos) {
		Set<Move> moves = new HashSet<Move>();
		Coord captureLeft;
		Coord captureRight;
		if (color == Color.WHITE) {
			captureLeft = pos.add(new Coord(-1, 1));
			captureRight = pos.add(new Coord(1, 1));
		} else {
			captureLeft = pos.add(new Coord(-1, -1));
			captureRight = pos.add(new Coord(1, -1));
		}
		if (Board.isValidCoord(captureLeft)
		 && board.getSpace(captureLeft).isOccupied()
		 && board.getSpace(captureLeft).getPiece().getColor() != color) {
			moves.add(new Move(pos, captureLeft));
		}
		if (Board.isValidCoord(captureRight)
		 && board.getSpace(captureRight).isOccupied()
		 && board.getSpace(captureRight).getPiece().getColor() != color) {
			moves.add(new Move(pos, captureRight));
		}
		return moves;
	}
	
	private Set<Move> getStandardMoves(Board board, Coord pos) {
		Set<Move> moves = new HashSet<Move>();
		Coord moveOne;
		Coord moveTwo;
		if (color == Color.WHITE) {
			moveOne = pos.add(new Coord(0, 1));
			moveTwo = pos.add(new Coord(0, 2));
		} else {
			moveOne = pos.add(new Coord(0, -1));
			moveTwo = pos.add(new Coord(0, -2));
		}
		if (Board.isValidCoord(moveOne)
		 && !board.getSpace(moveOne).isOccupied()) {
			moves.add(new Move(pos, moveOne));
		}
		if (Board.isValidCoord(moveTwo)
		 && !hasMoved
		 && !board.getSpace(moveOne).isOccupied()
		 && !board.getSpace(moveTwo).isOccupied()) {
			moves.add(new Move(pos, moveTwo));
		}
		return moves;
	}

	@Override
	public Piece copy() {
		Pawn pawn = new Pawn(color);
		pawn.hasMoved = this.hasMoved;
		return pawn;
	}
	
	@Override
	public String toString() {
		String worb = this.color ? "w" : "b";
		return worb + "p";
	}
}
