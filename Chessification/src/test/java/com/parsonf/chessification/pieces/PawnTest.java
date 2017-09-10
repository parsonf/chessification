package com.parsonf.chessification.pieces;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Chessification;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Move;

public class PawnTest {
	private Chessification chess;
	private Board board;
	
	@Before
	public void setUp() {
		chess = new Chessification(Chessification.NO_GUI);
		board = chess.getGame().getBoard();
	}
	
	@Test
	public void getAvailableMoves_BlackCanCaptureInBlackDirection_CaptureMoveListed() {
		final String testMessage = "Black pawn should be able to capture enemy piece";
		board.clear();
		Pawn blackPawn = new Pawn(Color.BLACK);
		Coord blackPawnPos = new Coord(Coord.COL_D, Coord.ROW_5);
		board.setDownPiece(blackPawn, blackPawnPos);
		Rook whiteRook = new Rook(Color.WHITE);
		Coord whiteRookPos = new Coord(Coord.COL_E, Coord.ROW_4);
		board.setDownPiece(whiteRook, whiteRookPos);
		Set<Move> moves = blackPawn.getAvailableMoves(board, blackPawnPos);
		assertTrue(testMessage, moves.contains(new Move(blackPawnPos, whiteRookPos)));
	}
	
	@Test
	public void getAvailableMoves_BlackPawnCanMoveInBlackDirection_OneSpaceDownListed() {
		final String testMessage = "Black pawn should be able to move one space in black direction";
		board.clear();
		Pawn blackPawn = new Pawn(Color.BLACK);
		Coord blackPawnPos = new Coord(Coord.COL_D, Coord.ROW_5);
		board.setDownPiece(blackPawn, blackPawnPos);
		Pawn whitePawn = new Pawn(Color.WHITE);
		Coord whitePawnPos = new Coord(Coord.COL_D, Coord.ROW_3);
		board.setDownPiece(whitePawn, whitePawnPos);
		Set<Move> moves = blackPawn.getAvailableMoves(board, blackPawnPos);
		assertTrue(testMessage, moves.size() == 1);
		assertTrue(testMessage, moves.contains(new Move(blackPawnPos, new Coord(Coord.COL_D, Coord.ROW_4))));
	}
	
	@Test
	public void getAvailableMoves_WhiteCanCaptureInWhiteDirection_CaptureMoveListed() {
		final String testMessage = "White pawn should be able to capture enemy piece";
		board.clear();
		Pawn whitePawn = new Pawn(Color.WHITE);
		Coord whitePawnPos = new Coord(Coord.COL_D, Coord.ROW_3);
		board.setDownPiece(whitePawn, whitePawnPos);
		Rook blackRook = new Rook(Color.BLACK);
		Coord blackRookPos = new Coord(Coord.COL_E, Coord.ROW_4);
		board.setDownPiece(blackRook, blackRookPos);
		Set<Move> moves = whitePawn.getAvailableMoves(board, whitePawnPos);
		assertTrue(testMessage, moves.contains(new Move(whitePawnPos, blackRookPos)));
	}
	
	@Test
	public void getAvailableMoves_WhitePawnCanMoveInWhiteDirection_OneSpaceUpListed() {
		final String testMessage = "White pawn should be able to move one space in white direction";
		board.clear();
		Pawn whitePawn = new Pawn(Color.WHITE);
		Coord whitePawnPos = new Coord(Coord.COL_D, Coord.ROW_3);
		board.setDownPiece(whitePawn, whitePawnPos);
		Pawn blackPawn = new Pawn(Color.BLACK);
		Coord blackPawnPos = new Coord(Coord.COL_D, Coord.ROW_5);
		board.setDownPiece(blackPawn, blackPawnPos);
		Set<Move> moves = whitePawn.getAvailableMoves(board, whitePawnPos);
		assertTrue(testMessage, moves.size() == 1);
		assertTrue(testMessage, moves.contains(new Move(whitePawnPos, new Coord(Coord.COL_D, Coord.ROW_4))));
	}

}
