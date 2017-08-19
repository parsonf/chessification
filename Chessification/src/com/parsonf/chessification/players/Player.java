package com.parsonf.chessification.players;

import java.util.HashSet;
import java.util.Set;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Game;
import com.parsonf.chessification.Move;
import com.parsonf.chessification.Space;

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

	//# ; this function gets all the legal moves for the player.
	//# ; also, we will want to know if we should check for check and castle.
	//# ; why wouldn't we want to? checking for those involves checking for future moves,
	//# ; which would lead to an infinite loop.
	public Set<Move> getAllLegalMoves(Board board, Player player, boolean checkCheck, boolean checkCastle) {
		Set<Move> moves = new HashSet<Move>();
		
		for (int col=Coord.COL_MIN; col<=Coord.COL_MAX; col++) {
			for(int row=Coord.ROW_MIN; row<=Coord.ROW_MAX; row++) {
				Coord coord = new Coord(col, row);
				// we are looping through each piece.
				if (board.getSpace(coord) == Space.VACANT) {
					// this is just a space. next.
					continue;
				} else {
					// we found a piece...
					try {
						if (board.getSpace(coord).getPiece().getColor() == player.color) {
							// if the piece we found is the color we want...
							moves.addAll(getLegalMoves(coord));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (checkCastle) {
			moves.addAll(getCastleMoves(player.color));
		}
		if (checkCheck) {
			moves.removeAll(findCheckedMoves(moves));
		}
		
		return moves;
	}
	

	/**
	 * See if a move puts you in check. If so, filter out the move.
	 * 
	 * @param color
	 * @return
	 */
	private Set<Move> findCheckedMoves(Set<Move> moves) {
		Board board;
		Set<Move> movesToRemove = new HashSet<Move>();
		for (Move move: moves) {
			board = (Board) game.getBoard().clone();
			board.isInCheck(color);
			if (board.isInCheck(color)) {
				movesToRemove.add(move);
			}
		}
		return movesToRemove;
	}


	/**
	 * Returns a set of moves available to perform castle.
	 * (The move represents the king's move only.)
	 * 
	 * @param color
	 * @return
	 */
	private Set<Move> getCastleMoves(boolean color) {
		// TODO Auto-generated method stub
		return null;
	}


	private Set<Move> getLegalMoves(Coord coord) {
		
		Board board = game.getBoard();
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
			opponent = game.getWhitePlayer();
		} else {
			opponent = game.getBlackPlayer();
		}
	}
}
