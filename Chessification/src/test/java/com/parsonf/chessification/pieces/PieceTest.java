package com.parsonf.chessification.pieces;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.pieces.Piece;
import com.parsonf.chessification.pieces.Queen;

public class PieceTest {
	
	@Test
	public void lineMovement_GivenPosOffBoard_ThrowIAException() {
		Piece piece = new Queen(Color.BLACK);
		Board board = new Board();
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
		try {
			piece.lineMovement(new Board(), new Coord(Coord.COL_MIN, Coord.ROW_MIN), new Coord(0,0));
			fail(testMessage);
		}
		catch (IllegalArgumentException iae) {
			assertTrue(testMessage, true);
		}
	}
}
