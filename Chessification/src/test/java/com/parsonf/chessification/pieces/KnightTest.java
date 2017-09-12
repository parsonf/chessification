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
	
	@Test
	public void getAvailableMoves_KnightBlockedFriendlyEachDestination_CantMove() {
		final String testMessage = "Knight with friendly on each destination cannot move.";
		board.clear();
		Knight knight = new Knight(Color.BLACK);
		Coord knightPos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(knight, knightPos);
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_B, Coord.ROW_5));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_C, Coord.ROW_6));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_E, Coord.ROW_6));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_F, Coord.ROW_5));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_F, Coord.ROW_3));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_E, Coord.ROW_2));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_C, Coord.ROW_2));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_B, Coord.ROW_3));
		
		Set<Coord> destinations = new HashSet<Coord>();
		knight.getAvailableMoves(board, knightPos).forEach(move -> destinations.add(move.getTo()));
		assertTrue(testMessage, destinations.isEmpty());
	}
	
	@Test
	public void getAvailableMoves_KnightBlockedEnemiesAtEachDestination_CanCaptureAll() {
		final String testMessage = "Knight with enemies on each destination can still move to each and capture.";
		board.clear();
		Knight knight = new Knight(Color.WHITE);
		Coord knightPos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(knight, knightPos);
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_B, Coord.ROW_5));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_C, Coord.ROW_6));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_E, Coord.ROW_6));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_F, Coord.ROW_5));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_F, Coord.ROW_3));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_E, Coord.ROW_2));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_C, Coord.ROW_2));
		board.setDownPiece(new Pawn(Color.BLACK), new Coord(Coord.COL_B, Coord.ROW_3));
		
		Set<Coord> destinations = new HashSet<Coord>();
		knight.getAvailableMoves(board, knightPos).forEach(move -> destinations.add(move.getTo()));
		assertTrue(testMessage, destinations.size() == 8);
		assertTrue("D4 knight can move to C6.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_6)));
		assertTrue("D4 knight can move to E6.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_6)));
		assertTrue("D4 knight can move to E2.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_2)));
		assertTrue("D4 knight can move to C2.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_2)));
		assertTrue("D4 knight can move to B3.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_3)));
		assertTrue("D4 knight can move to B5.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_5)));
		assertTrue("D4 knight can move to F3.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_3)));
		assertTrue("D4 knight can move to F5.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_5)));
	}
	
	@Test
	public void getAvailableMoves_KnightsInTheCorners_OnlyHaveTwoMoves() {
		final String testMessage = "Knights in the corners only have two moves and it doesn't break horribly.";
		board.clear();
		Knight a1Knight = new Knight(Color.WHITE);
		Knight a8Knight = new Knight(Color.BLACK);
		Knight h1Knight = new Knight(Color.WHITE);
		Knight h8Knight = new Knight(Color.BLACK);
		Coord a1 = new Coord(Coord.COL_A, Coord.ROW_1);
		Coord a8 = new Coord(Coord.COL_A, Coord.ROW_8);
		Coord h1 = new Coord(Coord.COL_H, Coord.ROW_1);
		Coord h8 = new Coord(Coord.COL_H, Coord.ROW_8);
		board.setDownPiece(a1Knight, a1);
		board.setDownPiece(a8Knight, a8);
		board.setDownPiece(h1Knight, h1);
		board.setDownPiece(h8Knight, h8);
		
		assertTrue(testMessage, a1Knight.getAvailableMoves(board, a1).size() == 2);
		assertTrue(testMessage, a8Knight.getAvailableMoves(board, a8).size() == 2);
		assertTrue(testMessage, h1Knight.getAvailableMoves(board, h1).size() == 2);
		assertTrue(testMessage, h8Knight.getAvailableMoves(board, h8).size() == 2);
	}

}
