package com.parsonf.chessification;

import java.util.HashSet;
import java.util.Set;

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
			move = aiBlack.getBestMove(game.getBoard());
		} else {
			move = aiWhite.getBestMove(game.getBoard());
		}
		game.getBoard().move(move, Move.ACTUAL);
		if (gui) {
			window.makeChessMove(convertLogicalMoveToGUIMove(move));
		}
		whoseTurnItIs = !whoseTurnItIs;
	}

	public static void main(String[] args) {
		new Chessification(true);
	}
	
	public void resetLogicalBoardForNewGame() {
		game.resetLogicalBoardForNewGame();
	}
	
	public Game getGame() {
		return game;
	}

	public Set<Coord> getMovesForPieceAtCoord(Coord guiCoord) {
		Set<Coord> coords = game.getBoard().getMovesForPieceAtCoord(convertGUICoordToLogicalCoord(guiCoord));
		Set<Coord> guiCoords = new HashSet<Coord>();
		for (Coord coord : coords) {
			guiCoords.add(convertLogicalCoordToGUICoord(coord));
		}
		return guiCoords;
	}
	
	protected Coord convertLogicalCoordToGUICoord(Coord logicalCoord) {
		return new Coord(8 - logicalCoord.getRow(), logicalCoord.getCol() - 1);
	}
	protected Coord convertGUICoordToLogicalCoord(Coord guiCoord) {
		return new Coord(1 + guiCoord.getRow(), 8 - guiCoord.getCol());
	}
	protected Move convertLogicalMoveToGUIMove(Move logicalMove) {
		return new Move(convertLogicalCoordToGUICoord(logicalMove.getFrom()), 
						convertLogicalCoordToGUICoord(logicalMove.getTo()));
	}
	protected Move convertGUIMoveToLogicalMove(Move guiMove) {
		return new Move(convertGUICoordToLogicalCoord(guiMove.getFrom()),
						convertGUICoordToLogicalCoord(guiMove.getTo()));
	}
	
}