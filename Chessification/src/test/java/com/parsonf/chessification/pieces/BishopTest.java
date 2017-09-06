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

public class BishopTest {
	private Chessification chess;
	private Board board;
	
	@Before
	public void setUp() {
		chess = new Chessification(Chessification.NO_GUI);
		board = chess.getGame().getBoard();
	}
	
	
	@Test
	public void getAvailableMoves_LoneD4Bishop_ReturnsMoves() {
		board.clear();
		Piece bishop = new Bishop(Color.WHITE);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(bishop, pos);
		// Get all the available moves for this bishop.
		Set<Coord> destinations = new HashSet<Coord>();
		bishop.getAvailableMoves(board, pos).forEach(move -> destinations.add(move.getTo()));
		// Let's test and see if we got the expected moves back.
		// A bishop at D4 should be able to move to:
		// C3, B2, A1, C5, B6, A7, E5, F6, G7, H8, E3, F2, G1
		// That is thirteen moves.
		assertTrue("There should be exactly thirteen moves that this bishop can make.", destinations.size() == 13);
		// Now we will verify each one of those moves.
		assertTrue("Lone D4 Bishop can move to C3.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_3)));
		assertTrue("Lone D4 Bishop can move to B2.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_2)));
		assertTrue("Lone D4 Bishop can move to A1.", destinations.contains(new Coord(Coord.COL_A, Coord.ROW_1)));
		assertTrue("Lone D4 Bishop can move to C5.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_5)));
		assertTrue("Lone D4 Bishop can move to B6.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_6)));
		assertTrue("Lone D4 Bishop can move to A7.", destinations.contains(new Coord(Coord.COL_A, Coord.ROW_7)));
		assertTrue("Lone D4 Bishop can move to E5.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_5)));
		assertTrue("Lone D4 Bishop can move to F6.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_6)));
		assertTrue("Lone D4 Bishop can move to G7.", destinations.contains(new Coord(Coord.COL_G, Coord.ROW_7)));
		assertTrue("Lone D4 Bishop can move to H8.", destinations.contains(new Coord(Coord.COL_H, Coord.ROW_8)));
		assertTrue("Lone D4 Bishop can move to E3.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_3)));
		assertTrue("Lone D4 Bishop can move to F2.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_2)));
		assertTrue("Lone D4 Bishop can move to G1.", destinations.contains(new Coord(Coord.COL_G, Coord.ROW_1)));
	}
}
