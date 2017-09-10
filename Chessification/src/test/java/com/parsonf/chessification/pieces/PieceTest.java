package com.parsonf.chessification.pieces;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Chessification;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;

public class PieceTest {
	private Chessification chess;
	private Board board;
	
	@Before
	public void setUp() {
		chess = new Chessification(Chessification.NO_GUI);
		board = chess.getGame().getBoard();
	}
	
	@Test
	public void lineMovement_VectorN_MoveN() {
		// Set up the test.
		board.clear();
		Piece rook = new Rook(Color.BLACK);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(rook, pos);
		// now test the function.
		Coord vector = new Coord(0, 1); // move north
		Set<Coord> destinations = new HashSet<Coord>();
		rook.lineMovement(board, pos, vector).forEach(move -> destinations.add(move.getTo()));
		assertTrue("lineMovement should return four moves.", destinations.size() == 4);
		assertTrue("lineMovement should let rook move N to D5.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_5)));
		assertTrue("lineMovement should let rook move N to D6.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_6)));
		assertTrue("lineMovement should let rook move N to D7.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_7)));
		assertTrue("lineMovement should let rook move N to D8.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_8)));
	}
	
	@Test
	public void lineMovement_VectorNE_MoveNE() {
		// Set up the test.
		board.clear();
		Piece bishop = new Bishop(Color.BLACK);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(bishop, pos);
		// now test the function.
		Coord vector = new Coord(1, 1); // move NE
		Set<Coord> destinations = new HashSet<Coord>();
		bishop.lineMovement(board, pos, vector).forEach(move -> destinations.add(move.getTo()));
		assertTrue("lineMovement should return four moves.", destinations.size() == 4);
		assertTrue("lineMovement should let bishop move NE to E5.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_5)));
		assertTrue("lineMovement should let bishop move NE to F6.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_6)));
		assertTrue("lineMovement should let bishop move NE to G7.", destinations.contains(new Coord(Coord.COL_G, Coord.ROW_7)));
		assertTrue("lineMovement should let bishop move NE to H8.", destinations.contains(new Coord(Coord.COL_H, Coord.ROW_8)));
	}
	
	@Test
	public void lineMovement_VectorE_MoveE() {
		// Set up the test.
		board.clear();
		Piece rook = new Rook(Color.BLACK);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(rook, pos);
		// now test the function.
		Coord vector = new Coord(1, 0); // move east
		Set<Coord> destinations = new HashSet<Coord>();
		rook.lineMovement(board, pos, vector).forEach(move -> destinations.add(move.getTo()));
		assertTrue("lineMovement should return four moves.", destinations.size() == 4);
		assertTrue("lineMovement should let rook move E to E4.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_4)));
		assertTrue("lineMovement should let rook move E to F4.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_4)));
		assertTrue("lineMovement should let rook move E to G4.", destinations.contains(new Coord(Coord.COL_G, Coord.ROW_4)));
		assertTrue("lineMovement should let rook move E to H4.", destinations.contains(new Coord(Coord.COL_H, Coord.ROW_4)));
	}
	
	@Test
	public void lineMovement_VectorSE_MoveSE() {
		// Set up the test.
		board.clear();
		Piece bishop = new Bishop(Color.BLACK);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(bishop, pos);
		// now test the function.
		Coord vector = new Coord(1, -1); // move SE
		Set<Coord> destinations = new HashSet<Coord>();
		bishop.lineMovement(board, pos, vector).forEach(move -> destinations.add(move.getTo()));
		assertTrue("lineMovement should return three moves.", destinations.size() == 3);
		assertTrue("lineMovement should let rook move SE to E3.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_3)));
		assertTrue("lineMovement should let rook move SE to F2.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_2)));
		assertTrue("lineMovement should let rook move SE to G1.", destinations.contains(new Coord(Coord.COL_G, Coord.ROW_1)));
	}
	
	@Test
	public void lineMovement_VectorS_MoveS() {
		// Set up the test.
		board.clear();
		Piece queen = new Queen(Color.BLACK);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(queen, pos);
		// now test the function.
		Coord vector = new Coord(0, -1); // move south
		Set<Coord> destinations = new HashSet<Coord>();
		queen.lineMovement(board, pos, vector).forEach(move -> destinations.add(move.getTo()));
		assertTrue("lineMovement should return three moves.", destinations.size() == 3);
		assertTrue("lineMovement should let rook move S to D3.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_3)));
		assertTrue("lineMovement should let rook move S to D2.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_2)));
		assertTrue("lineMovement should let rook move S to D1.", destinations.contains(new Coord(Coord.COL_D, Coord.ROW_1)));
	}
	
	@Test
	public void lineMovement_VectorSW_MoveSW() {
		// Set up the test.
		board.clear();
		Piece queen = new Queen(Color.BLACK);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(queen, pos);
		// now test the function.
		Coord vector = new Coord(-1, -1); // move SW
		Set<Coord> destinations = new HashSet<Coord>();
		queen.lineMovement(board, pos, vector).forEach(move -> destinations.add(move.getTo()));
		assertTrue("lineMovement should return three moves.", destinations.size() == 3);
		assertTrue("lineMovement should let rook move SW to C3.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_3)));
		assertTrue("lineMovement should let rook move SW to B2.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_2)));
		assertTrue("lineMovement should let rook move SW to A1.", destinations.contains(new Coord(Coord.COL_A, Coord.ROW_1)));
	}
	
	@Test
	public void lineMovement_VectorW_MoveW() {
		// Set up the test.
		board.clear();
		Piece queen = new Queen(Color.BLACK);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(queen, pos);
		// now test the function.
		Coord vector = new Coord(-1, 0); // move west
		Set<Coord> destinations = new HashSet<Coord>();
		queen.lineMovement(board, pos, vector).forEach(move -> destinations.add(move.getTo()));
		assertTrue("lineMovement should return three moves.", destinations.size() == 3);
		assertTrue("lineMovement should let rook move W to C4.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_4)));
		assertTrue("lineMovement should let rook move W to B4.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_4)));
		assertTrue("lineMovement should let rook move W to A4.", destinations.contains(new Coord(Coord.COL_A, Coord.ROW_4)));
	}
	
	@Test
	public void lineMovement_VectorNW_MoveNW() {
		// Set up the test.
		board.clear();
		Piece queen = new Queen(Color.BLACK);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(queen, pos);
		// now test the function.
		Coord vector = new Coord(-1, 1); // move NW
		Set<Coord> destinations = new HashSet<Coord>();
		queen.lineMovement(board, pos, vector).forEach(move -> destinations.add(move.getTo()));
		assertTrue("lineMovement should return three moves.", destinations.size() == 3);
		assertTrue("lineMovement should let rook move NW to C5.", destinations.contains(new Coord(Coord.COL_C, Coord.ROW_5)));
		assertTrue("lineMovement should let rook move NW to B6.", destinations.contains(new Coord(Coord.COL_B, Coord.ROW_6)));
		assertTrue("lineMovement should let rook move NW to A7.", destinations.contains(new Coord(Coord.COL_A, Coord.ROW_7)));
	}
	
	@Test
	public void lineMovement_FriendlyInPath_BlockedByFriendly() {
		// Set up the test.
		board.clear();
		Piece rook = new Rook(Color.BLACK);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(rook, pos);
		Piece friendlyPawn = new Pawn(Color.BLACK);
		Coord  blockedPos = new Coord(Coord.COL_F, Coord.ROW_4);
		board.setDownPiece(friendlyPawn, blockedPos);
		// now test the function.
		Coord vector = new Coord(1, 0); // move east
		Set<Coord> destinations = new HashSet<Coord>();
		rook.lineMovement(board, pos, vector).forEach(move -> destinations.add(move.getTo()));
		assertTrue("lineMovement should return one move.", destinations.size() == 1);
		assertTrue("lineMovement should let rook move E to E4.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_4)));
		assertFalse("lineMovement should NOT let rook move E to F4.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_4)));
	}
	
	@Test
	public void lineMovement_EnemyInPath_CapturesEnemy() {
		// Set up the test.
		board.clear();
		Piece rook = new Rook(Color.BLACK);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		board.setDownPiece(rook, pos);
		Piece enemyPawn = new Pawn(Color.WHITE);
		Coord  blockedPos = new Coord(Coord.COL_F, Coord.ROW_4);
		board.setDownPiece(enemyPawn, blockedPos);
		// now test the function.
		Coord vector = new Coord(1, 0); // move east
		Set<Coord> destinations = new HashSet<Coord>();
		rook.lineMovement(board, pos, vector).forEach(move -> destinations.add(move.getTo()));
		assertTrue("lineMovement should return two moves.", destinations.size() == 2);
		assertTrue("lineMovement should let rook move E to E4.", destinations.contains(new Coord(Coord.COL_E, Coord.ROW_4)));
		assertTrue("lineMovement should let rook move E to F4.", destinations.contains(new Coord(Coord.COL_F, Coord.ROW_4)));
		assertFalse("lineMovement should NOT let rook move E to G4.", destinations.contains(new Coord(Coord.COL_G, Coord.ROW_4)));
	}
	
	@Test
	public void lineMovement_NoPieceAtPos_ThrowsIAException() {
		// Set up the test.
		board.clear();
		Piece pawn = new Pawn(Color.BLACK);
		Coord pos = new Coord(Coord.COL_D, Coord.ROW_4);
		//board.setDownPiece(pawn, pos); // <-- when no piece is set down.
		// now test the function.
		Coord vector = new Coord(1, 0); // move east
		Set<Coord> destinations = new HashSet<Coord>();
		try {
			pawn.lineMovement(board, pos, vector).forEach(move -> destinations.add(move.getTo()));
		}
		catch (IllegalArgumentException iae) {
			assertTrue("piece.lineMovement should throw IAE when no piece at given position.", true);
		}
	}
	
	@Test
	public void lineMovement_GivenPosOffBoard_ThrowIAException() {
		Piece piece = new Queen(Color.BLACK);
		Coord vector = new Coord(0,1);
		final String rowTooLowTest = "piece.lineMovement should throw IAE with too low position row.";
		final String rowTooHighTest = "piece.lineMovement should throw IAE with too high position row.";
		final String colTooLowTest = "piece.lineMovement should throw IAE with too low position col.";
		final String colTooHighTest = "piece.lineMovement should throw IAE with too high position col.";
		for (int col = Coord.COL_MIN; col <= Coord.COL_MAX; col++) {
			try {
				piece.lineMovement(board, new Coord(col, Coord.ROW_MIN-1), vector);
				fail(rowTooLowTest);
			}
			catch (IllegalArgumentException iae) {
				assertTrue(rowTooLowTest, true);
			}
			try {
				piece.lineMovement(board, new Coord(col, Coord.ROW_MAX+1), vector);
				fail(rowTooHighTest);
			}
			catch (IllegalArgumentException iae) {
				assertTrue(rowTooHighTest, true);
			}
		}
		for (int row = Coord.ROW_MIN; row <= Coord.ROW_MAX; row++) {
			try {
				piece.lineMovement(board, new Coord(Coord.COL_MIN-1, row), vector);
				fail(colTooLowTest);
			}
			catch (IllegalArgumentException iae) {
				assertTrue(colTooLowTest, true);
			}
			try {
				piece.lineMovement(board, new Coord(Coord.COL_MAX+1, row), vector);
				fail(colTooHighTest);
			}
			catch (IllegalArgumentException iae) {
				assertTrue(colTooHighTest, true);
			}
		}
	}
	
	@Test
	public void lineMovement_VectorNoDirection_ThrowIAException() {
		final String testMessage = "piece.lineMovement should throw IAE when vector is (0,0)";
		Piece piece = new Queen(Color.BLACK);
		board.clear();
		try {
			piece.lineMovement(board, new Coord(Coord.COL_MIN, Coord.ROW_MIN), new Coord(0,0));
			fail(testMessage);
		}
		catch (IllegalArgumentException iae) {
			assertTrue(testMessage, true);
		}
	}
	
	@Test
	public void canMakeMove_NoPieceAtPos_ThrowsIAException() {
		final String testMessage = "piece.canMakeMove should throw IAE when no piece at position.";
		board.clear();
		Piece rook = new Rook(Color.WHITE);
		//board.setDownPiece(pawn, fromPos); // <-- when no piece is set down.
		Coord toPos = new Coord(Coord.COL_E, Coord.ROW_4);
		try {
			board.isValidCoordNotOccupiedByFriendly(Color.WHITE, toPos);
		} catch (IllegalArgumentException iae) {
			assertTrue(testMessage, true);
		}
	}
}
