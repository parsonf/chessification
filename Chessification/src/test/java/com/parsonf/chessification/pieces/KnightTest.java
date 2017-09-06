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

public class KnightTest {
	private Chessification chess;
	private Board board;
	
	@Before
	public void setUp() {
		chess = new Chessification(Chessification.NO_GUI);
		board = chess.getGame().getBoard();
	}
	
	@Test
	public void getAvailableMoves_LoneD4Knight_ReturnsMoves() {
		board.clear();
		Knight knight = new Knight(Color.BLACK);
		Coord knightPos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(knight, knightPos);
		Set<Coord> destinations = new HashSet<Coord>();
		knight.getAvailableMoves(board, knightPos).forEach(move -> destinations.add(move.getTo()));
		// There should be eight available moves.
		assertTrue("knight.getAvailableMoves should have 8 available moves for lone knight.", destinations.size() == 8);
		assertTrue("Lone d4 knight can move to C6.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_6)));
		assertTrue("Lone d4 knight can move to E6.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_6)));
		assertTrue("Lone d4 knight can move to E2.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_2)));
		assertTrue("Lone d4 knight can move to C2.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_2)));
		assertTrue("Lone d4 knight can move to B3.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_3)));
		assertTrue("Lone d4 knight can move to B5.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_5)));
		assertTrue("Lone d4 knight can move to F3.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_3)));
		assertTrue("Lone d4 knight can move to F5.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_5)));
	}

}
