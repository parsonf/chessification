package com.parsonf.chessification;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChessificationTest {
	private Chessification chess;
	// a logical coord is [ 1-8 or A-H for col, left to right][1-8 for row, bottom to top]
	// logical coord grid
	//  1,8  2,8  3,8  4,8  5,8  6,8  7,8  8,8
	//  1,7  2,7  3,7  4,7  5,7  6,7  7,7  8,7
	//  1,6  2,6  3,6  4,6  5,6  6,6  7,6  8,6
	//  1,5  2,5  3,5  4,5  5,5  6,5  7,5  8,5
	//  1,4  2,4  3,4  4,4  5,4  6,4  7,4  8,4
	//  1,3  2,3  3,3  4,3  5,3  6,3  7,3  8,3
	//  1,2  2,2  3,2  4,2  5,2  6,2  7,2  8,2
	//  1,1  2,1  3,1  4,1  5,1  6,1  7,1  8,1
	//  gui coord grid
	//  0,0  0,1  0,2  0,3  0,4  0,5  0,6  0,7
	//  1,0  1,1  1,2  1,3  1,4  1,5  1,6  1,7
	//  2,0  2,1  2,2  2,3  2,4  2,5  2,6  2,7
	//  3,0  3,1  3,2  3,3  3,4  3,5  3,6  3,7
	//  4,0  4,1  4,2  4,3  4,4  4,5  4,6  4,7
	//  5,0  5,1  5,2  5,3  5,4  5,5  5,6  5,7
	//  6,0  6,1  6,2  6,3  6,4  6,5  6,6  6,7
	//  7,0  7,1  7,2  7,3  7,4  7,5  7,6  7,7
	

	@Before
	public void setUp() {
		chess = new Chessification(Chessification.NO_GUI);
	}
	
	@Test
	public void convertLogicalCoordToGUICoord_corners() {
		Coord logicalCoord = new Coord(Coord.COL_A, Coord.ROW_8);
		Coord expectedGuiCoord = new Coord(0,0);
		Coord guiCoord = chess.convertLogicalCoordToGUICoord(logicalCoord);
		//System.out.println("expectedguicoord: " + expectedGuiCoord + ", guiCoord: " + guiCoord);
		assertTrue("Convert logical coord to gui coord.", guiCoord.equals(expectedGuiCoord));
		// TODO other corners
	}
	
	@Test
	public void convertGUICoordToLogicalCoord_corners() {
		final String testMessage = "Convert logical coord to gui coord.";
		Coord guiCoord = new Coord(0,0);
		Coord expectedLogicalCoord = new Coord(Coord.COL_A, Coord.ROW_8);
		Coord logicalCoord = chess.convertGUICoordToLogicalCoord(guiCoord);
		System.out.println("expectedlogicalcoord: " + expectedLogicalCoord + ", logicalCoord: " + logicalCoord);
		assertTrue(testMessage, logicalCoord.equals(expectedLogicalCoord));
		
		guiCoord = new Coord(0,7);
		expectedLogicalCoord = new Coord(Coord.COL_H, Coord.ROW_8);
		logicalCoord = chess.convertGUICoordToLogicalCoord(guiCoord);
		System.out.println("expectedlogicalcoord: " + expectedLogicalCoord + ", logicalCoord: " + logicalCoord);
		assertTrue(testMessage, logicalCoord.equals(expectedLogicalCoord));
		
		guiCoord = new Coord(7,7);
		expectedLogicalCoord = new Coord(Coord.COL_H, Coord.ROW_1);
		logicalCoord = chess.convertGUICoordToLogicalCoord(guiCoord);
		System.out.println("expectedlogicalcoord: " + expectedLogicalCoord + ", logicalCoord: " + logicalCoord);
		assertTrue(testMessage, logicalCoord.equals(expectedLogicalCoord));
		
		guiCoord = new Coord(7,0);
		expectedLogicalCoord = new Coord(Coord.COL_A, Coord.ROW_1);
		logicalCoord = chess.convertGUICoordToLogicalCoord(guiCoord);
		System.out.println("expectedlogicalcoord: " + expectedLogicalCoord + ", logicalCoord: " + logicalCoord);
		assertTrue(testMessage, logicalCoord.equals(expectedLogicalCoord));
	}
	
	@Test
	public void convertLogicalMoveToGUIMove_testAllValidMoves() {
		
	}
	
	@Test
	public void convertGUIMoveToLogicalMove_testAllValidMoves() {
		
	}

	@Test
	public void getMovesForPieceAtCoord_test() {
		
	}
}
