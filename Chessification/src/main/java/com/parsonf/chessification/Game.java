package com.parsonf.chessification;

import java.util.List;

import com.parsonf.chessification.players.AIPlayStyle;
import com.parsonf.chessification.players.AIPlayer;
import com.parsonf.chessification.players.Player;

public class Game {
	private Chessification chessification;
	private Player blackPlayer;
	private Player whitePlayer;
	private Board board;
	private List<Move> moves;

	// Constructors --------------------------------------------------------
	public Game(Chessification chessification, GameType gameType) {
		this.chessification = chessification;
		handleGameType(gameType);
		blackPlayer.findOpponent();
		whitePlayer.findOpponent();		
		board = new Board();
	}

	// Methods -------------------------------------------------------------
	private void handleGameType(GameType gameType) {
		switch (gameType) {
		case AI_IS_BLACK:
			whitePlayer = new Player(this, Color.WHITE);
			blackPlayer = new AIPlayer(this, Color.BLACK, new AIPlayStyle());
			break;
		case AI_IS_WHITE:
			whitePlayer = new AIPlayer(this, Color.WHITE, new AIPlayStyle());
			blackPlayer = new Player(this, Color.BLACK);
			break;
		case AI_VS_AI:
			whitePlayer = new AIPlayer(this, Color.WHITE, new AIPlayStyle());
			blackPlayer = new AIPlayer(this, Color.BLACK, new AIPlayStyle());
			break;
		case NO_AI:
			whitePlayer = new Player(this, Color.WHITE);
			blackPlayer = new Player(this, Color.BLACK);
			break;
		default:
			throw new RuntimeException("Impossible game type.");
		}
	}
	
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
