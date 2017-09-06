package com.parsonf.chessification;

import com.parsonf.chessification.gui.Window;
import com.parsonf.chessification.players.AIPlayer;

public class Chessification {

	public static final String APP_TITLE = "Chessification";
	
	public static final boolean LOAD_GUI = true;
	public static final boolean NO_GUI = false;
	
	public Window window;
	private Game game;
	private boolean whoseTurnItIs;
	private boolean gui;
	
	public Chessification(boolean gui) {
		this.gui = gui;
		window = (gui) ? new Window(this) : null;
		game = new Game(this, GameType.AI_VS_AI);
		whoseTurnItIs = Color.WHITE;
	}
	
	public void takeTurn() {
		AIPlayer aiWhite = (AIPlayer) game.getPlayer(Color.WHITE);
		AIPlayer aiBlack = (AIPlayer) game.getPlayer(Color.BLACK);
		Move move = null;
		if (whoseTurnItIs == Color.BLACK) {
			move = aiBlack.minimaxDepthFirst(game.getBoard().copy(), aiBlack, 4);
		} else {
			move = aiWhite.minimaxDepthFirst(game.getBoard().copy(), aiWhite, 4);
		}
		game.getBoard().move(move, Move.ACTUAL);
		if (gui) {
			window.makeChessMove(move);
		}
		whoseTurnItIs = !whoseTurnItIs;
	}

	public static void main(String[] args) {
		new Chessification(true);
	}
	
	public void resetLogicalBoardForNewGame() {
		game.resetBoardForNewGame();
	}
	
	public Game getGame() {
		return game;
	}
}