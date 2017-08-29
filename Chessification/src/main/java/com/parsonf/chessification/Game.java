package com.parsonf.chessification;

import java.util.List;

import com.parsonf.chessification.players.Player;

public class Game {
	private Player blackPlayer;
	private Player whitePlayer;
	private Board board;
	private List<Move> moves;

	// Constructors --------------------------------------------------------
	public Game() {
		blackPlayer = new Player(this, Color.BLACK);
		whitePlayer = new Player(this, Color.WHITE);
		blackPlayer.findOpponent();
		whitePlayer.findOpponent();		
		board = new Board();
	}

	// Methods -------------------------------------------------------------
	
	
	public Player getPlayer(boolean color) {
		return (color == Color.WHITE) ? whitePlayer : blackPlayer;
	}

	public Board getBoard() {
		return board;
	}

	public List<Move> getMoves() {
		return moves;
	}
	
}
