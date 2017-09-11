package com.parsonf.chessification;

import java.util.HashSet;
import java.util.Set;

import com.parsonf.chessification.pieces.Bishop;
import com.parsonf.chessification.pieces.King;
import com.parsonf.chessification.pieces.Knight;
import com.parsonf.chessification.pieces.Pawn;
import com.parsonf.chessification.pieces.Piece;
import com.parsonf.chessification.pieces.Queen;
import com.parsonf.chessification.pieces.Rook;
import com.parsonf.chessification.players.Player;

public class Board {

	private Space[][] board;
	private int score;

	// Constructors --------------------------------------------------------
	public Board() {
		board = new Space[8][8];
		for (int col = Coord.COL_MIN; col <= Coord.COL_MAX; col++) {
			for (int row = Coord.ROW_MIN; row <= Coord.ROW_MAX; row++) {
				board[col - 1][row - 1] = new Space();
			}
		}
		reset();
	}

	// Methods -------------------------------------------------------------
	public Space getSpace(Coord coord) {
		if (!isValidCoord(coord)) {
			return null;
		}
		return getSpace(coord.col, coord.row);
	}
	
	public Board copy() {
		Board clone = new Board();

		for (int col = Coord.COL_MIN; col <= Coord.COL_MAX; col++) {
			for (int row = Coord.ROW_MIN; row <= Coord.ROW_MAX; row++) {
				if (getSpace(col, row).isOccupied()) {
					clone.board[col - 1][row - 1] = getSpace(col, row).copy();
				} else {
					clone.board[col - 1][row - 1] = new Space();
				}
			}
		}
		return clone;
	}
	
	public void setDownPiece(Piece piece, Coord coord) {
		if (isValidCoord(coord)) {
			getSpace(coord).setPiece(piece);
		} else {
			throw new RuntimeException("Cannot set piece down because the provided coord is invalid.");
		}
	}

	/**
	 * Clears and sets the board up for a standard game of chess.
	 */
	public void reset() {
		// clear the board.
		clear();

		// set pieces on board.
		for (int col = Coord.COL_MIN; col <= Coord.COL_MAX; col++) {
			Piece blackPawn = new Pawn(Color.BLACK);
			getSpace(col, Coord.ROW_7).setPiece(blackPawn);
			Piece whitePawn = new Pawn(Color.WHITE);
			getSpace(col, Coord.ROW_2).setPiece(whitePawn);
			switch (col) {
			case Coord.COL_A:
			case Coord.COL_H:
				getSpace(col, Coord.ROW_1).setPiece(new Rook(Color.WHITE));
				getSpace(col, Coord.ROW_8).setPiece(new Rook(Color.BLACK));
				break;
			case Coord.COL_B:
			case Coord.COL_G:
				getSpace(col, Coord.ROW_1).setPiece(new Knight(Color.WHITE));
				getSpace(col, Coord.ROW_8).setPiece(new Knight(Color.BLACK));
				break;
			case Coord.COL_C:
			case Coord.COL_F:
				getSpace(col, Coord.ROW_1).setPiece(new Bishop(Color.WHITE));
				getSpace(col, Coord.ROW_8).setPiece(new Bishop(Color.BLACK));
				break;
			case Coord.COL_D:
				getSpace(col, Coord.ROW_1).setPiece(new Queen(Color.WHITE));
				getSpace(col, Coord.ROW_8).setPiece(new Queen(Color.BLACK));
				break;
			case Coord.COL_E:
				getSpace(col, Coord.ROW_1).setPiece(new King(Color.WHITE));
				getSpace(col, Coord.ROW_8).setPiece(new King(Color.BLACK));
				break;
			default:
				throw new RuntimeException("Board.reset(): col is invalid.");
			}
		}
	}

	/**
	 * Removes all the pieces off the board.
	 */
	public void clear() {
		for (int col = Coord.COL_MIN; col <= Coord.COL_MAX; col++) {
			for (int row = Coord.ROW_MIN; row <= Coord.ROW_MAX; row++) {
				Space space = getSpace(col, row);
				if (space.isOccupied()) {
					space.pickUpPiece();
				}
			}
		}
	}

	/**
	 * To minimize the amount of times needing to zero-adjust.
	 * 
	 * @param col
	 * @param row
	 * @return
	 */
	private Space getSpace(int col, int row) {
		return board[col - 1][row - 1];
	}

	// Getters/Setters -----------------------------------------------------
	/**
	 * Determines if the provided coord is a legal coord on a chess board.
	 * 
	 * @param coord
	 * @return
	 */
	public static boolean isValidCoord(Coord coord) {
		if (coord.getRow() < Coord.ROW_MIN || coord.getRow() > Coord.ROW_MAX) {
			return false;
		}
		if (coord.getCol() < Coord.COL_MIN || coord.getCol() > Coord.COL_MAX) {
			return false;
		}
		return true;
	}

	/**
	 * Performs the given move on the board.
	 * If the move is an actual move, then the piece is recorded to have moved.
	 * @param move
	 * @param isActualMove
	 */
	public void move(Move move, boolean isActualMove) {
		if (move == null || move.getFrom() == null || move.getTo() == null) {
			throw new IllegalArgumentException("Board.move arg 'move': " + move);
		}
		if (!getSpace(move.getFrom()).isOccupied()) {
			throw new RuntimeException("No piece at this space we are moving from. move: " + move + ", board: " + this);
		}
		// TODO improve: handle en passant somehow. may need to redo some of this
		// implementation.
		Space space = getSpace(move.getFrom());
		Piece pieceToMove = space.pickUpPiece();
		if (getSpace(move.getTo()).isOccupied()) {
			// captured piece.
			getSpace(move.getTo()).pickUpPiece(); 
		}
		getSpace(move.getTo()).setPiece(pieceToMove);
		pieceToMove.setHasMoved();
		// if castling, move the rook too.
		if (move.getFrom().equals(new Coord(Coord.COL_E, Coord.ROW_1))) {
			if (move.getTo().equals(new Coord(Coord.COL_C, Coord.ROW_1))) {
				move(new Move(new Coord(Coord.COL_A, Coord.ROW_1), new Coord(Coord.COL_D, Coord.ROW_1)), true);
			} else if (move.getTo().equals(new Coord(Coord.COL_G, Coord.ROW_1))) {
				move(new Move(new Coord(Coord.COL_H, Coord.ROW_1), new Coord(Coord.COL_F, Coord.ROW_1)), true);
			}
		} else if (move.getFrom().equals(new Coord(Coord.COL_E, Coord.ROW_8))) {
			if (move.getTo().equals(new Coord(Coord.COL_C, Coord.ROW_8))) {
				move(new Move(new Coord(Coord.COL_A, Coord.ROW_8), new Coord(Coord.COL_D, Coord.ROW_8)), true);
			} else if (move.getTo().equals(new Coord(Coord.COL_G, Coord.ROW_8))) {
				move(new Move(new Coord(Coord.COL_H, Coord.ROW_8), new Coord(Coord.COL_F, Coord.ROW_8)), true);
			}
		}
		// TODO improve: pawn promotion to choice.
		if (pieceToMove instanceof Pawn && move.getTo().getRow() == Coord.ROW_8) {
			getSpace(move.getTo()).pickUpPiece();
			Piece promotedPawn = new Queen(pieceToMove.getColor());
			getSpace(move.getTo()).setPiece(promotedPawn);
		}
	}
	
	@Override
	public String toString() {
		String s = "\n";
		for (int col=0; col<8; col++) {
			for (int row=7; row>=0; row--) {
				s += board[row][7-col] + "  ";
			}
			s += "\n";
		}
		return s;
	}

	public boolean isPlayerInCheck(Player player) {
		// if the destination of any move is a king of any color.
		// note: we ignore castle because castling cannot possibly capture opponent king,
		// which is fortunate, because checking for it would likely result in
		// another infinite loop.
		boolean isPlayerInCheck = false;
		Set<Move> opponentMoves = player.getOpponent().getAllLegalMoves(this, Player.IGNORE_CHECK, Player.IGNORE_CASTLE);
		for (Move move : opponentMoves) {
			Space space = getSpace(move.getTo());
			if (space.isOccupied() && space.getPiece() instanceof King) {
				isPlayerInCheck = true;
				break;
			}
		}
		return isPlayerInCheck;
	}
	
	public boolean isValidCoordNotOccupiedByFriendly(boolean color, Coord targetCoord) {
		final boolean NOT_A_VALID_SPACE = false;
		final boolean SPACE_OCCUPIED_BY_OWN_COLOR = false;
		final boolean SPACE_IS_VACANT = true;
		final boolean CAN_CAPTURE_ENEMY = true;
		
		if (Board.isValidCoord(targetCoord)) {
			Space targetSpace = getSpace(targetCoord);
			if (targetSpace.isOccupied()) {
				if (targetSpace.getPiece().getColor() != color) {
					return CAN_CAPTURE_ENEMY;
				} else {
					return SPACE_OCCUPIED_BY_OWN_COLOR;
				}
			} else {
				return SPACE_IS_VACANT;
			}
		} else {
			return NOT_A_VALID_SPACE;
		}
	}

	public boolean hasPieces(boolean color) {
		boolean hasPieces = false;
		for (int col = Coord.COL_MIN; col <= Coord.COL_MAX; col++) {
			for (int row = Coord.ROW_MIN; row <= Coord.ROW_MAX; row++) {
				Piece piece = getSpace(col, row).getPiece();
				if (piece != null && piece.getColor() == color) {
					hasPieces = true;
					break;
				}
			}
		}
		return hasPieces;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}

	public Set<Coord> getMovesForPieceAtCoord(Coord coord) {
		Set<Coord> destinations = new HashSet<Coord>();
		if (Board.isValidCoord(coord)
		 && getSpace(coord).isOccupied())
		{
			Piece piece = getSpace(coord).getPiece();
			Set<Move> moves = piece.getAvailableMoves(this, coord);
			for (Move move : moves) {
				destinations.add(move.getTo());
			}
		}
		return destinations;
	}

}
