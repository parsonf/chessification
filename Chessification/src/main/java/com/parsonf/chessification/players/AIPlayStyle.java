package com.parsonf.chessification.players;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.pieces.Bishop;
import com.parsonf.chessification.pieces.King;
import com.parsonf.chessification.pieces.Knight;
import com.parsonf.chessification.pieces.Pawn;
import com.parsonf.chessification.pieces.Piece;
import com.parsonf.chessification.pieces.Queen;
import com.parsonf.chessification.pieces.Rook;

public abstract class AIPlayStyle {

	// Methods -------------------------------------------------------------
	public abstract int evaluateGameState(Board board, Player whoJustMoved);
	
	protected static int getValueAtCoord(int[][] grid, Coord pos) {
		return grid[Coord.ROW_MAX - pos.getRow()][pos.getCol() - 1];
	}
	
	private static final int PAWN = 0;
	private static final int ROOK = 1;
	private static final int KNIGHT = 2;
	private static final int BISHOP = 3;
	private static final int QUEEN = 4;
	private static final int KING = 5;
	
	protected static int getPieceIndex(Piece piece) {
		if (piece instanceof Pawn) {
			return PAWN;
		} else if (piece instanceof Rook) {
			return ROOK;
		} else if (piece instanceof Knight) {
			return KNIGHT;
		} else if (piece instanceof Bishop) {
			return BISHOP;
		} else if (piece instanceof Queen) {
			return QUEEN;
		} else if (piece instanceof King) {
			return KING;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	protected final int[] PIECE_VALUE = new int[] {
		getPawnValue(), getRookValue(), getKnightValue(), getBishopValue(), getQueenValue(), getKingValue()
	};
	protected final int[] MOVE_VALUE = new int[] {
		getPawnMoveValue(), getRookMoveValue(), getKnightMoveValue(), 
		getBishopMoveValue(), getQueenMoveValue(), getKingMoveValue()
	};
	
	protected int[][] getPositionValueGrid(int pieceIndex) {
		switch (pieceIndex) {
		case PAWN:
			return getPawnPositionValueGrid();
		case ROOK:
			return getRookPositionValueGrid();
		case KNIGHT:
			return getKnightPositionValueGrid();
		case BISHOP:
			return getBishopPositionValueGrid();
		case QUEEN:
			return getQueenPositionValueGrid();
		case KING:
			return getKingPositionValueGrid();
		default:
			throw new IllegalArgumentException();
		}
	}
	

	// Getters/Setters -----------------------------------------------------
	// the value of simply having that piece present on the board.
	public abstract int getPawnValue();
	public abstract int getRookValue();
	public abstract int getKnightValue();
	public abstract int getBishopValue();
	public abstract int getQueenValue();
	public abstract int getKingValue();
	// the value of how many moves that piece can make.
	public abstract int getPawnMoveValue();
	public abstract int getRookMoveValue();
	public abstract int getKnightMoveValue();
	public abstract int getBishopMoveValue();
	public abstract int getQueenMoveValue();
	public abstract int getKingMoveValue();
	// the value of each piece on the board
	public abstract int[][] getPawnPositionValueGrid();
	public abstract int[][] getRookPositionValueGrid();
	public abstract int[][] getKnightPositionValueGrid();
	public abstract int[][] getBishopPositionValueGrid();
	public abstract int[][] getQueenPositionValueGrid();
	public abstract int[][] getKingPositionValueGrid();
	
}
