package com.parsonf.chessification;

import java.util.concurrent.TimeUnit;

import com.parsonf.chessification.gui.Window;
import com.parsonf.chessification.players.AIPlayer;

public class Chessification {

	public static final String APP_TITLE = "Chessification";
	public Window window;
	public Game game;
	private boolean whoseTurnItIs;
	
	public Chessification() {
		window = new Window(this);
		game = new Game(this, GameType.AI_VS_AI);
		whoseTurnItIs = Color.WHITE;
	}
	
	public void takeTurn() {
		AIPlayer aiWhite = (AIPlayer) game.getPlayer(Color.WHITE);
		AIPlayer aiBlack = (AIPlayer) game.getPlayer(Color.BLACK);
		Move move = null;
		if (whoseTurnItIs == Color.BLACK) {
			move = aiBlack.minimaxDepthFirst(game.getBoard().copy(), aiBlack, 4);
			game.getBoard().move(move, Move.ACTUAL);
			window.makeChessMove(move);
		} else {
			move = aiWhite.minimaxDepthFirst(game.getBoard().copy(), aiWhite, 4);
			game.getBoard().move(move, Move.ACTUAL);
			window.makeChessMove(move);
		}
		whoseTurnItIs = !whoseTurnItIs;
	}

	public static void main(String[] args) {
		new Chessification();
	}
	
	public void resetLogicalBoardForNewGame() {
		game.resetBoardForNewGame();
	}
}