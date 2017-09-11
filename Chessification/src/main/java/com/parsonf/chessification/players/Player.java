package com.parsonf.chessification.players;

import java.util.HashSet;
import java.util.Set;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Game;
import com.parsonf.chessification.Move;

public class Player {
	public static final boolean CHECK_CHECK = true;
	public static final boolean IGNORE_CHECK = false;
	public static final boolean CHECK_CASTLE = true;
	public static final boolean IGNORE_CASTLE = false;
	public static final boolean BLACK = false;
	public static final boolean WHITE = true;
	protected Game game;
	protected Player opponent;
	protected boolean color;
	
	// Constructors --------------------------------------------------------
	public Player(Game game, boolean color) {
		this.game = game;
		this.color = color;
	}
	// Methods -------------------------------------------------------------
	/**
	 * This function gets all the legal moves for the player.
	 * Also, we will want to know if we should check for check and castle.
	 * Why wouldn't we want to? Checking for those involves checking for future moves,
	 * which would lead to an infinite loop.
	 * 
	 * @param board
	 * @param checkCheck
	 * @param checkCastle
	 * @return
	 */
	public Set<Move> getAllLegalMoves(Board board, boolean checkCheck, boolean checkCastle) {
		Set<Move> moves = new HashSet<Move>();
		
		for (int col=Coord.COL_MIN; col<=Coord.COL_MAX; col++) {
			for(int row=Coord.ROW_MIN; row<=Coord.ROW_MAX; row++) {
				Coord coord = new Coord(col, row);
				// we are looping through each coord, looking for a piece.
				if (!board.getSpace(coord).isOccupied()) {
					// this is just a space. next.
					continue;
				} else {
					// we found a piece...
					if (board.getSpace(coord).getPiece().getColor() == color) {
						// if the piece we found is the color we want...
						moves.addAll(getLegalMoves(board, coord));
					}
				}
			}
		}
		if (checkCastle) {
			moves.addAll(getCastleMoves(board, this));
		}
		if (checkCheck) {
			moves.removeAll(findCheckedMoves(board, moves));
		}
		
		return moves;
	}
	

	/**
	 * See if a move puts you in check. If so, filter out the move.
	 * 
	 * @param color
	 * @return
	 */
	private Set<Move> findCheckedMoves(Board board, Set<Move> moves) {
		Board thinkBoard;
		Set<Move> movesToRemove = new HashSet<Move>();
		for (Move move: moves) {
			thinkBoard = board.copy();
			thinkBoard.move(move, Move.ACTUAL);
			if (thinkBoard.isPlayerInCheck(this)) {
				movesToRemove.add(move);
			}
		}
		//System.out.println("movesToRemove: "  + movesToRemove);
		return movesToRemove;
	}


	/**
	 * Returns a set of moves available to perform castle.
	 * (The move represents the king's move only.)
	 * 
	 * @param color
	 * @return
	 */
	private Set<Move> getCastleMoves(Board board, Player player) {
		Set<Move> moves = new HashSet<Move>();
		// Conditions for castling to be available:
		// 1. King must not have moved.
		// 2. Rook must not have moved.
		// 3. Spaces inbetween must be clear.
		// 4. King, rook, and spaces inbetween must not be threatened.
		if (player.color == Color.WHITE) {
			Coord e1 = new Coord(Coord.COL_E, Coord.ROW_1);
			Coord h1 = new Coord(Coord.COL_H, Coord.ROW_1);
			if (board.getSpace(e1).isOccupied() && !game.getBoard().getSpace(e1).getPiece().hasMoved()
			 && board.getSpace(h1).isOccupied() && !game.getBoard().getSpace(h1).getPiece().hasMoved()) {
				Coord f1 = new Coord(Coord.COL_F, Coord.ROW_1);
				Coord g1 = new Coord(Coord.COL_G, Coord.ROW_1);
				if (!board.getSpace(f1).isOccupied()
				 && !board.getSpace(g1).isOccupied()) {
					if (!player.isThreatenedAtCoord(board, e1)
					 && !player.isThreatenedAtCoord(board, f1)
					 && !player.isThreatenedAtCoord(board, g1)
					 && !player.isThreatenedAtCoord(board, h1)) {
						moves.add(new Move(e1, g1));
					}
				}
			}
			Coord a1 = new Coord(Coord.COL_A, Coord.ROW_1);
			if (board.getSpace(e1).isOccupied() && !game.getBoard().getSpace(e1).getPiece().hasMoved()
			 && board.getSpace(a1).isOccupied() && !game.getBoard().getSpace(a1).getPiece().hasMoved()) {
				Coord b1 = new Coord(Coord.COL_B, Coord.ROW_1);
				Coord c1 = new Coord(Coord.COL_C, Coord.ROW_1);
				Coord d1 = new Coord(Coord.COL_D, Coord.ROW_1);
				if (!board.getSpace(b1).isOccupied()
				 && !board.getSpace(c1).isOccupied()
				 && !board.getSpace(d1).isOccupied()) {
					if (!player.isThreatenedAtCoord(board, a1)
					 && !player.isThreatenedAtCoord(board, b1)
					 && !player.isThreatenedAtCoord(board, c1)
					 && !player.isThreatenedAtCoord(board, d1)
					 && !player.isThreatenedAtCoord(board, e1)) {
						moves.add(new Move(e1, c1));
					}
				}
			}
		} else { // player.color is black
			Coord e8 = new Coord(Coord.COL_E, Coord.ROW_8);
			Coord h8 = new Coord(Coord.COL_H, Coord.ROW_8);
			if (board.getSpace(e8).isOccupied() && !game.getBoard().getSpace(e8).getPiece().hasMoved()
			 && board.getSpace(h8).isOccupied() && !game.getBoard().getSpace(h8).getPiece().hasMoved()) {
				Coord f8 = new Coord(Coord.COL_F, Coord.ROW_8);
				Coord g8 = new Coord(Coord.COL_G, Coord.ROW_8);
				if (!board.getSpace(f8).isOccupied()
				 && !board.getSpace(g8).isOccupied()) {
					if (!player.isThreatenedAtCoord(board, e8)
					 && !player.isThreatenedAtCoord(board, f8)
					 && !player.isThreatenedAtCoord(board, g8)
					 && !player.isThreatenedAtCoord(board, h8)) {
						moves.add(new Move(e8, g8));
					}
				}
			}
			Coord a8 = new Coord(Coord.COL_A, Coord.ROW_8);
			if (board.getSpace(e8).isOccupied() && !game.getBoard().getSpace(e8).getPiece().hasMoved()
			 && board.getSpace(a8).isOccupied() && !game.getBoard().getSpace(a8).getPiece().hasMoved()) {
				Coord b8 = new Coord(Coord.COL_B, Coord.ROW_8);
				Coord c8 = new Coord(Coord.COL_C, Coord.ROW_8);
				Coord d8 = new Coord(Coord.COL_D, Coord.ROW_8);
				if (!board.getSpace(b8).isOccupied()
				 && !board.getSpace(c8).isOccupied()
				 && !board.getSpace(d8).isOccupied()) {
					if (!player.isThreatenedAtCoord(board, a8)
					 && !player.isThreatenedAtCoord(board, b8)
					 && !player.isThreatenedAtCoord(board, c8)
					 && !player.isThreatenedAtCoord(board, d8)
					 && !player.isThreatenedAtCoord(board, e8)) {
						moves.add(new Move(e8, c8));
					}
				}
			}
		}
		return moves;
	}

	/**
	 * Checks if a space is threatened by an opponent.
	 * 
	 * @param coord
	 * @param colorToCheckIfUnderThreat
	 * @return
	 */
	public boolean isThreatenedAtCoord(Board board, Coord coord) {
		boolean isThreatened = false;
		Set<Move> moves = getAllLegalMoves(board, Player.IGNORE_CHECK, Player.IGNORE_CASTLE);
		for (Move move : moves) {
			if (move.getTo().equals(coord)) {
				isThreatened = true;
				break;
			}
		}
		return isThreatened;
	}


	private Set<Move> getLegalMoves(Board board, Coord coord) {
		if (coord == null) {
			throw new IllegalArgumentException("getLegalMoves: coord is null.");
		}
		if (!Board.isValidCoord(coord)) {
			throw new IllegalArgumentException("getLegalMoves: coord is not on board!: " + coord);
		}
		if (board.getSpace(coord).getPiece() == null) {
			throw new IllegalArgumentException("getLegalMoves: no piece at this coord! " + coord);
		}
		return board.getSpace(coord).getPiece().getAvailableMoves(board, coord);
	}

	// Getters/Setters -----------------------------------------------------

	public boolean getColor() {
		return color;
	}
	public Player getOpponent() {
		return opponent;
	}
	public void findOpponent() {
		if (color == Color.BLACK) {
			opponent = game.getPlayer(Color.WHITE);
		} else {
			opponent = game.getPlayer(Color.BLACK);
		}
	}
}
