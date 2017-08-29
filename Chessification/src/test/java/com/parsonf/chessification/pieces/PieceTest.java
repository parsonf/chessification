package com.parsonf.chessification.pieces;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;

public class PieceTest {
	
	@Test
	public void lineMovement_VectorN_MoveN() {
		// TODO: impl test lineMovement_VectorN_MoveN
	}
	
	@Test
	public void lineMovement_VectorNE_MoveNE() {
		// TODO: impl test lineMovement_VectorNE_MoveNE
	}
	
	@Test
	public void lineMovement_VectorE_MoveE() {
		// TODO: impl test lineMovement_VectorE_MoveE
	}
	
	@Test
	public void lineMovement_VectorSE_MoveSE() {
		// TODO: impl test lineMovement_VectorSE_MoveSE
	}
	
	@Test
	public void lineMovement_VectorS_MoveS() {
		// TODO: impl test lineMovement_VectorS_MoveS
	}
	
	@Test
	public void lineMovement_VectorSW_MoveSW() {
		// TODO: impl test lineMovement_VectorSW_MoveSW
	}
	
	@Test
	public void lineMovement_VectorW_MoveW() {
		// TODO: impl test lineMovement_VectorW_MoveW
	}
	
	@Test
	public void lineMovement_VectorNW_MoveNW() {
		// TODO: impl test lineMovement_VectorNW_MoveNW
	}
	
	@Test
	public void lineMovement_FriendlyInPath_BlockedByFriendly() {
		// TODO: impl test lineMovement_FriendlyInPath_BlockedByFriendly
	}
	
	@Test
	public void lineMovement_EnemyInPath_CapturesEnemy() {
		// TODO: impl test lineMovement_EnemyInPath_CapturesEnemy
	}
	
	@Test
	public void lineMovement_NoPieceAtPos_ThrowsIAException() {
		// TODO: impl test lineMovement_NoPieceAtPos_ThrowsIAException
	}
	
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
	
	@Test
	public void canMakeMove_NoPieceAtPos_ThrowsIAException() {
		// TODO: impl test canMakeMove_NoPieceAtPos_ThrowsIAException
	}
	
	@Test
	public void canMakeMove_NonPawnTargetEmpty_ReturnsTrue() {
		// TODO: impl test canMakeMove_NonPawnTargetEmpty_ReturnsTrue
	}
	
	@Test
	public void canMakeMove_NonPawnTargetEnemy_ReturnsTrue() {
		// TODO: impl test canMakeMove_NonPawnTargetEnemy_ReturnsTrue
	}
	
	@Test
	public void canMakeMove_NonPawnTargetFriendly_ReturnsFalse() {
		// TODO: impl test canMakeMove_NonPawnTargetFriendly_ReturnsFalse
	}
	
	@Test
	public void canMakeMove_PawnCanCaptureLeft_ReturnsTrue() {
		// TODO: impl test canMakeMove_PawnCanCaptureLeft_ReturnsTrue
	}
	
	@Test
	public void canMakeMove_PawnCanCaptureRight_ReturnsTrue() {
		// TODO: impl test canMakeMove_PawnCanCaptureRight_ReturnsTrue
	}
	
	@Test
	public void canMakeMove_PawnCannotCaptureLeftFriendly_ReturnsFalse() {
		// TODO: impl test canMakeMove_PawnCannotCaptureLeftFriendly_ReturnsFalse()
	}
	
	@Test
	public void canMakeMove_PawnCannotCaptureRightFriendly_ReturnsFalse() {
		// TODO: impl test canMakeMove_PawnCannotCaptureRightFriendly_ReturnsFalse
	}
	
	@Test
	public void canMakeMove_PawnCannotCaptureLeftEmpty_ReturnsFalse() {
		// TODO: impl test canMakeMove_PawnCannotCaptureLeftEmpty_ReturnsFalse
	}
	
	@Test
	public void canMakeMove_PawnCannotCaptureRightEmpty_ReturnsFalse() {
		// TODO: impl test canMakeMove_PawnCannotCaptureRightEmpty_ReturnsFalse
	}
	
	@Test
	public void canMakeMove_PawnCannotMoveTwoAlreadyMoved_ReturnsFalse() {
		// TODO: impl test canMakeMove_PawnCannotMoveTwoAlreadyMoved_ReturnsFalse
	}
	
	@Test
	public void canMakeMove_PawnCannotMoveTwoBlocked_ReturnsFalse() {
		// TODO: impl test canMakeMove_PawnCannotMoveTwoBlocked_ReturnsFalse
	}
	
	@Test
	public void canMakeMove_PawnCannotMoveOneBlocked_ReturnsFalse() {
		// TODO: impl test canMakeMove_PawnCannotMoveOneBlocked_ReturnsFalse
	}
	
	@Test
	public void canMakeMove_PawnCanMoveOneEmpty_ReturnsTrue() {
		// TODO: impl test canMakeMove_PawnCanMoveOneEmpty_ReturnsTrue
	}
	
	@Test
	public void canMakeMove_PawnCanMoveTwoUnmoved_ReturnsTrue() {
		// TODO: impl test canMakeMove_PawnCanMoveOneEmpty_ReturnsTrue
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
