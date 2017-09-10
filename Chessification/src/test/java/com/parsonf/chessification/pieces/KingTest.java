package com.parsonf.chessification.pieces;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Chessification;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Move;

public class KingTest {
	private Chessification chess;
	private Board board;
	
	@Before
	public void setUp() {
		chess = new Chessification(Chessification.NO_GUI);
		board = chess.getGame().getBoard();
	}
	
	@Test
	public void getAvailableMoves_StaleMatedKing_ReturnsMovesIgnoresCheck() {
		final String testMessage = "getAvailableMoves returns moves with no regard for stalemate.";
		//   O   R   O   R
		//   R   O   O   O
		//   O   O   K   O
		//   R   O   O   O
		board.clear();
		King king = new King(Color.WHITE);
		Coord kingPos = new Coord(Coord.COL_E, Coord.ROW_4);
		board.setDownPiece(king, kingPos);
		Rook c3Rook = new Rook(Color.BLACK);
		Rook c5Rook = new Rook(Color.BLACK);
		Rook d6Rook = new Rook(Color.BLACK);
		Rook f6Rook = new Rook(Color.BLACK);
		board.setDownPiece(c3Rook, new Coord(Coord.COL_C, Coord.ROW_3));
		board.setDownPiece(c5Rook, new Coord(Coord.COL_C, Coord.ROW_5));
		board.setDownPiece(d6Rook, new Coord(Coord.COL_D, Coord.ROW_6));
		board.setDownPiece(f6Rook, new Coord(Coord.COL_F, Coord.ROW_6));
		Set<Move> kingMoves = king.getAvailableMoves(board, kingPos);
		assertTrue(testMessage, kingMoves.size() == 8);
	}
	
	@Test
	public void getAvailableMoves_CheckMatedKing_ReturnsMovesIgnoresCheck() {
		final String testMessage = "getAvailableMoves returns moves with no regard for checkmate.";
		//   O   R   R   R
		//   R   O   O   O
		//   R   O   K   O
		//   R   O   O   O
		board.clear();
		King king = new King(Color.WHITE);
		Coord kingPos = new Coord(Coord.COL_E, Coord.ROW_4);
		board.setDownPiece(king, kingPos);
		Rook c3Rook = new Rook(Color.BLACK);
		Rook c4Rook = new Rook(Color.BLACK);
		Rook c5Rook = new Rook(Color.BLACK);
		Rook d6Rook = new Rook(Color.BLACK);
		Rook e6Rook = new Rook(Color.BLACK);
		Rook f6Rook = new Rook(Color.BLACK);
		board.setDownPiece(c3Rook, new Coord(Coord.COL_C, Coord.ROW_3));
		board.setDownPiece(c4Rook, new Coord(Coord.COL_C, Coord.ROW_4));
		board.setDownPiece(c5Rook, new Coord(Coord.COL_C, Coord.ROW_5));
		board.setDownPiece(d6Rook, new Coord(Coord.COL_D, Coord.ROW_6));
		board.setDownPiece(e6Rook, new Coord(Coord.COL_E, Coord.ROW_6));
		board.setDownPiece(f6Rook, new Coord(Coord.COL_F, Coord.ROW_6));
		Set<Move> kingMoves = king.getAvailableMoves(board, kingPos);
		assertTrue(testMessage, kingMoves.size() == 8);
	}
	
	@Test
	public void getAvailableMoves_FriendlySurroundedKing_ReturnsNoMoves() {
		board.clear();
		King king = new King(Color.WHITE);
		Coord kingPos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(king, kingPos);
		Pawn c4 = new Pawn(Color.WHITE);
		board.setDownPiece(c4, new Coord(Coord.COL_C, Coord.ROW_4));
		Pawn c5 = new Pawn(Color.WHITE);
		board.setDownPiece(c5, new Coord(Coord.COL_C, Coord.ROW_5));
		Pawn d5 = new Pawn(Color.WHITE);
		board.setDownPiece(d5, new Coord(Coord.COL_D, Coord.ROW_5));
		Pawn e5 = new Pawn(Color.WHITE);
		board.setDownPiece(e5, new Coord(Coord.COL_E, Coord.ROW_5));
		Pawn e4 = new Pawn(Color.WHITE);
		board.setDownPiece(e4, new Coord(Coord.COL_E, Coord.ROW_4));
		Pawn e3 = new Pawn(Color.WHITE);
		board.setDownPiece(e3, new Coord(Coord.COL_E, Coord.ROW_3));
		Pawn d3 = new Pawn(Color.WHITE);
		board.setDownPiece(d3, new Coord(Coord.COL_D, Coord.ROW_3));
		Pawn c3 = new Pawn(Color.WHITE);
		board.setDownPiece(c3, new Coord(Coord.COL_C, Coord.ROW_3));
		Set<Move> kingMoves = king.getAvailableMoves(board, kingPos);
		assertTrue("king.getAvailableMoves returns no moves when king is surrounded by friendly.", kingMoves.isEmpty());
	}
	
	@Test
	public void getAvailableMoves_LoneKing_ReturnsMoves() {
		board.clear();
		King king = new King(Color.WHITE);
		Coord kingPos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(king, kingPos);
		Set<Coord> destinations = new HashSet<Coord>();
		king.getAvailableMoves(board, kingPos).forEach(move -> destinations.add(move.getTo()));
		assertTrue("Lone king should have 8 moves.", destinations.size() == 8);
		assertTrue("Lone d4 king can move to d3.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_3)));
		assertTrue("Lone d4 king can move to c3.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_3)));
		assertTrue("Lone d4 king can move to c4.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_4)));
		assertTrue("Lone d4 king can move to c5.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_5)));
		assertTrue("Lone d4 king can move to d5.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_5)));
		assertTrue("Lone d4 king can move to e5.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_5)));
		assertTrue("Lone d4 king can move to d4.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_4)));
		assertTrue("Lone d4 king can move to e3.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_3)));
	}
}
