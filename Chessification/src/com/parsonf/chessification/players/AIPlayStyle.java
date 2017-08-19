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

public class AIPlayStyle implements IPlayStyle {
	// TODO external settings file, that gets imported instead, wouldn't that be cool??
	private final int PAWN_VALUE = 100;
	private final int ROOK_VALUE = 500;
	private final int KNIGHT_VALUE = 300;
	private final int BISHOP_VALUE = 300;
	private final int QUEEN_VALUE = 1000;
	private final int KING_VALUE = 900;
	
	//# ; I see no value in encouraging or discouraging number of pawn moves.
	private final int PAWN_MOVE_VALUE = 0;
	
	//# ; rooks are very useful when they control entire columns. number of moves is very valuable.
	private final int ROOK_MOVE_VALUE = 50;
	
	//# ; knights are most useful when they have moves. forks are awesome. knights are very mobile in tight spots. yes yes.
	private final int KNIGHT_MOVE_VALUE = 60;
	
	//# ; bishops are most useful when they control diagonals.
	private final int BISHOP_MOVE_VALUE = 50;
	
	//# ; queens are rooks plus bishops... but... don't want to be encouraging risking the queen too much.
	private final int QUEEN_MOVE_VALUE = 20;
	
	//# ; kings... they do best in hiding, but when being checked, best to have available moves.
	//# ; so, neither encourage nor discourage, i guess.
	private final int KING_MOVE_VALUE = 0;
	
	private final int[][] PAWN_POSITION_VALUE = new int[][] {
		{  90,  90,  90,  90,  90,  90,  90,  90 },
		{  50,  50,  50,  50,  50,  50,  50,  50 },
		{  10,  10,  20,  30,  30,  20,  10,  10 },
		{   5,   5,  10,  25,  25,  10,   5,   5 },
		{   0,   0,   0,  20,  20,   0,   0,   0 },
		{   5,  -5, -10,   0,   0, -10,  -5,   5 },
		{   5,  10,  10, -20, -20,  10,  10,   5 },
		{   0,   0,   0,   0,   0,   0,   0,   0 }
	};
	
	private final int[][] ROOK_POSITION_VALUE = new int[][] {
		{   0,   0,   0,   0,   0,   0,   0,   0 },
		{   5,  10,  10,  10,  10,  10,  10,   5 },
		{  -5,   0,   0,   0,   0,   0,   0,  -5 },
		{  -5,   0,   0,   0,   0,   0,   0,  -5 },
		{  -5,   0,   0,   0,   0,   0,   0,  -5 },
		{  -5,   0,   0,   0,   0,   0,   0,  -5 },
		{  -5,   0,   0,   0,   0,   0,   0,  -5 },
		{   0,   0,   0,   5,   5,   0,   0,   0 }
	};
	
	private final int[][] KNIGHT_POSITION_VALUE = new int[][] {
		{ -50, -40, -30, -30, -30, -30, -40, -50 },
		{ -40, -20,   0,   0,   0,   0, -20, -40 },
		{ -30,   0,  10,  15,  15,  10,   0, -30 },
		{ -30,   5,  15,  20,  20,  15,   5, -30 },
		{ -30,   0,  15,  20,  20,  15,   0, -30 },
		{ -30,   5,  10,  15,  15,  10,   5, -30 },
		{ -40, -20,   0,   5,   5,   0, -20, -40 },
		{ -50, -40, -30, -30, -30, -30, -40, -50 }
	};
	
	private final int[][] BISHOP_POSITION_VALUE = new int[][] {
		{ -20, -10, -10, -10, -10, -10, -10, -20 },
		{ -10,   0,   0,   0,   0,   0,   0, -10 },
		{ -10,   0,   5,  10,  10,   5,   0, -10 },
		{ -10,   5,   5,  10,  10,   5,   5, -10 },
		{ -10,   0,  10,  10,  10,  10,   0, -10 },
		{ -10,  10,  10,  10,  10,  10,  10, -10 },
		{ -10,   5,   0,   0,   0,   0,   5, -10 },
		{ -20, -10, -10, -10, -10, -10, -10, -20 }
	};
	
	private final int[][] QUEEN_POSITION_VALUE = new int[][] {
		{ -20, -10, -10,  -5,  -5, -10, -10, -20 },
		{ -10,   0,   0,   0,   0,   0,   0, -10 },
		{ -10,   0,   5,   5,   5,   5,   0, -10 },
		{  -5,   0,   5,   5,   5,   5,   0,  -5 },
		{   0,   0,   5,   5,   5,   5,   0, -10 },
		{ -10,   0,   5,   5,   5,   5,   0, -10 },
		{ -10,   0,   5,   0,   0,   0,   0, -10 },
		{ -20, -10, -10,  -5,  -5, -10, -10, -20 }
	};
	
	private final int[][] KING_POSITION_VALUE = new int[][] {
		{ -30, -40, -40, -50, -50, -40, -40, -30 },
		{ -30, -40, -40, -50, -50, -40, -40, -30 },
		{ -30, -40, -40, -50, -50, -40, -40, -30 },
		{ -30, -40, -40, -50, -50, -40, -40, -30 },
		{ -20, -30, -30, -40, -40, -30, -30, -20 },
		{ -10, -20, -20, -20, -20, -20, -20, -10 },
		{  20,  20,   0,   0,   0,   0,  20,  20 },
		{  20,  30,  10,   0,   0,  10,  30,  20 }
	};
	
	private Player player;
	
	private final int PAWN = 0;
	private final int ROOK = 1;
	private final int KNIGHT = 2;
	private final int BISHOP = 3;
	private final int QUEEN = 4;
	private final int KING = 5;
	
	private final int[] PIECE_VALUE = new int[] {
		PAWN_VALUE, ROOK_VALUE, KNIGHT_VALUE, BISHOP_VALUE, QUEEN_VALUE, KING_VALUE
	};
	private final int[] MOVE_VALUE = new int[] {
		PAWN_MOVE_VALUE, ROOK_MOVE_VALUE, KNIGHT_MOVE_VALUE, BISHOP_MOVE_VALUE, QUEEN_MOVE_VALUE, KING_MOVE_VALUE
	};
	private final int[][][] POSITION_VALUE = new int[][][] {
		PAWN_POSITION_VALUE, ROOK_POSITION_VALUE, KNIGHT_POSITION_VALUE,
		BISHOP_POSITION_VALUE, QUEEN_POSITION_VALUE, KING_POSITION_VALUE
	};

	// Constructors --------------------------------------------------------

	public AIPlayStyle(Player player) {
		this.player = player;
	}

	// Methods -------------------------------------------------------------
	@Override
	public int evaluateGameState(Board board, Player whoJustMoved) {
		// TODO aiplaystyle evaluategamestate
		int totalValue = 0;
		int pieceValue = 0;
		boolean isMine = false;
		Piece piece = null;
		boolean pieceColor = false;
		boolean isThreatened = false;
		
		// encourage checkmate, discourage stalemate
		if (player.getAllLegalMoves(board, whoJustMoved.getOpponent(), Player.CHECK_CHECK, Player.CHECK_CASTLE).isEmpty()) {
			// if opponent is in check, then it is checkmate! value REALLY HIGH
			// however, if not, then it is stalemate! value REALLY LOW
			if (board.isInCheck(!whoJustMoved.getColor())) {
				totalValue += 90000; // DO IT !!!!!
			} else {
				totalValue -= 90000; // DONT DO IT !!!!!
			}
		}
		for (int col = Coord.COL_MIN; col <= Coord.COL_MAX; col++) {
			for (int row = Coord.ROW_MIN; row <= Coord.ROW_MAX; row++) {
				Coord coord = new Coord(col, row);
				// if this is a piece
				if (board.getSpace(coord).isOccupied()) {
					piece = board.getSpace(new Coord(col, row)).getPiece();
					pieceColor = piece.getColor();
					// is this piece mine or theirs?
					isMine = (whoJustMoved.getColor() == pieceColor);
					// evaluate now
					// take into consideration whether that piece is threatened.
					isThreatened = board.coordThreatened(coord, whoJustMoved.getColor());
					int pieceIndex = getPieceIndex(piece);
					// lets add the value of simply having that piece on the board.
					pieceValue = (isThreatened) ? PIECE_VALUE[pieceIndex]/2 : PIECE_VALUE[pieceIndex];
					// add to that the value of that particular piece at that particular spot.
					pieceValue += getValueAtCoord(POSITION_VALUE[pieceIndex], coord);
					// add to that the worth of the number of moves this piece has.
					pieceValue += (MOVE_VALUE[pieceIndex] * piece.getAvailableMoves(board, coord).size());
					// if this is an opponent piece, that's bad, not good.
					totalValue += (isMine) ? pieceValue : -pieceValue;
				}
			}
		}
		// negate the result if black is scoring.
		return (whoJustMoved.getColor() == Color.WHITE) ? totalValue : -totalValue;
	}
	
	private int getValueAtCoord(int[][] grid, Coord pos) {
		return grid[Coord.ROW_MAX - pos.getRow()][pos.getCol() - 1];
	}
	
	private int getPieceIndex(Piece piece) {
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
			return -1;
		}
	}
	
	// Getters/Setters -----------------------------------------------------
	@Override
	public int getPawnValue() {
		return PAWN_VALUE;
	}
	@Override
	public int getRookValue() {
		return ROOK_VALUE;
	}
	@Override
	public int getKnightValue() {
		return KNIGHT_VALUE;
	}
	@Override
	public int getBishopValue() {
		return BISHOP_VALUE;
	}
	@Override
	public int getQueenValue() {
		return QUEEN_VALUE;
	}
	@Override
	public int getKingValue() {
		return KING_VALUE;
	}
	@Override
	public int getPawnMoveValue() {
		return PAWN_MOVE_VALUE;
	}
	@Override
	public int getRookMoveValue() {
		return ROOK_MOVE_VALUE;
	}
	@Override
	public int getKnightMoveValue() {
		return KNIGHT_MOVE_VALUE;
	}
	@Override
	public int getBishopMoveValue() {
		return BISHOP_MOVE_VALUE;
	}
	@Override
	public int getQueenMoveValue() {
		return QUEEN_MOVE_VALUE;
	}
	@Override
	public int getKingMoveValue() {
		return KING_MOVE_VALUE;
	}
	@Override
	public int getPawnPositionValue(Coord pos) {
		return getValueAtCoord(PAWN_POSITION_VALUE, pos);
	}
	@Override
	public int getRookPositionValue(Coord pos) {
		return getValueAtCoord(ROOK_POSITION_VALUE, pos);
	}
	@Override
	public int getKnightPositionValue(Coord pos) {
		return getValueAtCoord(KNIGHT_POSITION_VALUE, pos);
	}
	@Override
	public int getBishopPositionValue(Coord pos) {
		return getValueAtCoord(BISHOP_POSITION_VALUE, pos);
	}
	@Override
	public int getQueenPositionValue(Coord pos) {
		return getValueAtCoord(QUEEN_POSITION_VALUE, pos);
	}
	@Override
	public int getKingPositionValue(Coord pos) {
		return getValueAtCoord(KING_POSITION_VALUE, pos);
	}
	


}
