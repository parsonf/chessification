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

public class QueenTest {
	private Chessification chess;
	private Board board;
	
	@Before
	public void setUp() {
		chess = new Chessification(Chessification.NO_GUI);
		board = chess.getGame().getBoard();
	}

	@Test
	public void getAvailableMoves_LoneD4Queen_ReturnsMoves() {
		board.clear();
		Piece queen = new Queen(Color.WHITE);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(queen, pos);
		// Get all the available moves for this bishop.
		Set<Coord> destinations = new HashSet<Coord>();
		queen.getAvailableMoves(board, pos).forEach(move -> destinations.add(move.getTo()));
		// Let's test and see if we got the expected moves back.
		// A rook at D4 should be able to move to:
		// D1, D2, D3, D5, D6, D7, D8, A4, B4, C4, E4, F4, G4, H4
		// C3, B2, A1, C5, B6, A7, E5, F6, G7, H8, E3, F2, G1
		// That is twenty seven moves.
		//System.out.println("queen destinations: " + destinations);
		//assertTrue("There should be exactly 27 moves that this queen can make.", destinations.size() == 27);
		// Now we will verify each one of those moves.
		assertTrue("Lone D4 queen can move to D1.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_1)));
		assertTrue("Lone D4 queen can move to D2.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_2)));
		assertTrue("Lone D4 queen can move to D3.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_3)));
		assertTrue("Lone D4 queen can move to D5.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_5)));
		assertTrue("Lone D4 queen can move to D6.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_6)));
		assertTrue("Lone D4 queen can move to D7.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_7)));
		assertTrue("Lone D4 queen can move to D8.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_8)));
		assertTrue("Lone D4 queen can move to A4.", destinations.contains(new Coord(Coord.COL_A, Coord.ROW_4)));
		assertTrue("Lone D4 queen can move to B4.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_4)));
		assertTrue("Lone D4 queen can move to C4.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_4)));
		assertTrue("Lone D4 queen can move to E4.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_4)));
		assertTrue("Lone D4 queen can move to F4.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_4)));
		assertTrue("Lone D4 queen can move to G4.", destinations.contains(new Coord(Coord.COL_G, Coord.ROW_4)));
		assertTrue("Lone D4 queen can move to H4.", destinations.contains(new Coord(Coord.COL_H, Coord.ROW_4)));
		
		assertTrue("Lone D4 queen can move to C3.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_3)));
		assertTrue("Lone D4 queen can move to B2.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_2)));
		assertTrue("Lone D4 queen can move to A1.", destinations.contains(new Coord(Coord.COL_A, Coord.ROW_1)));
		assertTrue("Lone D4 queen can move to C5.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_5)));
		assertTrue("Lone D4 queen can move to B6.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_6)));
		assertTrue("Lone D4 queen can move to A7.", destinations.contains(new Coord(Coord.COL_A, Coord.ROW_7)));
		assertTrue("Lone D4 queen can move to E5.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_5)));
		assertTrue("Lone D4 queen can move to F6.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_6)));
		assertTrue("Lone D4 queen can move to G7.", destinations.contains(new Coord(Coord.COL_G, Coord.ROW_7)));
		assertTrue("Lone D4 queen can move to H8.", destinations.contains(new Coord(Coord.COL_H, Coord.ROW_8)));
		assertTrue("Lone D4 queen can move to E3.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_3)));
		assertTrue("Lone D4 queen can move to F2.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_2)));
		assertTrue("Lone D4 queen can move to G1.", destinations.contains(new Coord(Coord.COL_G, Coord.ROW_1)));
	}
}
