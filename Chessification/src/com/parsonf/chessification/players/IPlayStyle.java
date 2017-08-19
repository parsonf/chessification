package com.parsonf.chessification.players;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Coord;

public interface IPlayStyle {

	// Methods -------------------------------------------------------------
	public int evaluateGameState(Board board, Player whoJustMoved);
	

	// Getters/Setters -----------------------------------------------------
	// the value of simply having that piece present on the board.
	public int getPawnValue();
	public int getRookValue();
	public int getKnightValue();
	public int getBishopValue();
	public int getQueenValue();
	public int getKingValue();
	// the value of how many moves that piece can make.
	public int getPawnMoveValue();
	public int getRookMoveValue();
	public int getKnightMoveValue();
	public int getBishopMoveValue();
	public int getQueenMoveValue();
	public int getKingMoveValue();
	// the value of each piece at a given position on the board (the board at any state).
	public int getPawnPositionValue(Coord pos);
	public int getRookPositionValue(Coord pos);
	public int getKnightPositionValue(Coord pos);
	public int getBishopPositionValue(Coord pos);
	public int getQueenPositionValue(Coord pos);
	public int getKingPositionValue(Coord pos);
	
}
