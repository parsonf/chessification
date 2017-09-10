package com.parsonf.chessification;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.parsonf.chessification.pieces.Bishop;
import com.parsonf.chessification.pieces.King;
import com.parsonf.chessification.pieces.Piece;
import com.parsonf.chessification.pieces.Rook;

public class BoardTest {
	private Chessification chess;
	private Board board;
	
	@Before
	public void setUp() {
		chess = new Chessification(Chessification.NO_GUI);
		board = chess.getGame().getBoard();
	}
	
	@Test
	public void isPlayerInCheck_CheckedKing_ReturnsTrue() {
		final String testMessage = "isPlayerInCheck returns true for checked king.";
		//   O   O   R   O
		//   O   O   O   O
		//   R   O   K   O
		//   O   O   O   O
		board.clear();
		King king = new King(Color.WHITE);
		Coord kingPos = new Coord(Coord.COL_E, Coord.ROW_4);
		board.setDownPiece(king, kingPos);
		Rook c4Rook = new Rook(Color.BLACK);
		Rook d6Rook = new Rook(Color.BLACK);
		board.setDownPiece(c4Rook, new Coord(Coord.COL_C, Coord.ROW_4));
		board.setDownPiece(d6Rook, new Coord(Coord.COL_D, Coord.ROW_6));
		assertTrue(testMessage, board.isPlayerInCheck(chess.getGame().getPlayer(Color.WHITE)));
	}
	
	@Test
	public void isPlayerInCheck_LoneKing_ReturnsFalse() {
		final String testMessage = "isPlayerInCheck returns false for lone king.";
		board.clear();
		King king = new King(Color.WHITE);
		Coord kingPos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(king, kingPos);
		assertFalse(testMessage, board.isPlayerInCheck(chess.getGame().getPlayer(Color.WHITE)));
	}
	
	@Test
	public void isValidCoordNotOccupiedByFriendly_TargetPosVacant_ReturnsTrue() {
		final String testMessage = "Should return true when target position is vacant.";
		board.clear();
		Piece rook = new Rook(Color.WHITE);
		board.setDownPiece(rook, new Coord(Coord.COL_H, Coord.ROW_8));
		assertTrue(testMessage, board.isValidCoordNotOccupiedByFriendly(Color.WHITE, new Coord(Coord.COL_B, Coord.ROW_2)));
	}
	
	@Test
	public void isValidCoordNotOccupiedByFriendly_TargetPosFriendly_ReturnsFalse() {
		final String testMessage = "Should return false because coord is occupied by friendly.";
		board.clear();
		Piece rook = new Rook(Color.BLACK);
		Coord rookPos = new Coord(Coord.COL_C, Coord.ROW_4);
		board.setDownPiece(rook, rookPos);
		assertFalse(testMessage, board.isValidCoordNotOccupiedByFriendly(Color.BLACK, rookPos));
	}
}
