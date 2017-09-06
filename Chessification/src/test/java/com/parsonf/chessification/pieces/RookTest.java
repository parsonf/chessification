package com.parsonf.chessification.pieces;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Chessification;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;

public class RookTest {
	private Chessification chess;
	private Board board;
	
	@Before
	public void setUp() {
		chess = new Chessification(Chessification.NO_GUI);
		board = chess.getGame().getBoard();
	}

	@Test
	public void getAvailableMoves_LoneD4Rook_ReturnsMoves() {
		board.clear();
		Piece rook = new Rook(Color.WHITE);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(rook, pos);
		// Get all the available moves for this bishop.
		Set<Coord> destinations = new HashSet<Coord>();
		rook.getAvailableMoves(board, pos).forEach(move -> destinations.add(move.getTo()));
		// Let's test and see if we got the expected moves back.
		// A rook at D4 should be able to move to:
		// D1, D2, D3, D5, D6, D7, D8, A4, B4, C4, E4, F4, G4, H4
		// That is fourteen moves.
		assertTrue("There should be exactly fourteen moves that this rook can make.", destinations.size() == 14);
		// Now we will verify each one of those moves.
		assertTrue("Lone D4 Rook can move to D1.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_1)));
		assertTrue("Lone D4 Rook can move to D2.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_2)));
		assertTrue("Lone D4 Rook can move to D3.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_3)));
		assertTrue("Lone D4 Rook can move to D5.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_5)));
		assertTrue("Lone D4 Rook can move to D6.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_6)));
		assertTrue("Lone D4 Rook can move to D7.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_7)));
		assertTrue("Lone D4 Rook can move to D8.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_8)));
		assertTrue("Lone D4 Rook can move to A4.", destinations.contains(new Coord(Coord.COL_A, Coord.ROW_4)));
		assertTrue("Lone D4 Rook can move to B4.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_4)));
		assertTrue("Lone D4 Rook can move to C4.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_4)));
		assertTrue("Lone D4 Rook can move to E4.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_4)));
		assertTrue("Lone D4 Rook can move to F4.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_4)));
		assertTrue("Lone D4 Rook can move to G4.", destinations.contains(new Coord(Coord.COL_G, Coord.ROW_4)));
		assertTrue("Lone D4 Rook can move to H4.", destinations.contains(new Coord(Coord.COL_H, Coord.ROW_4)));
	}

}
