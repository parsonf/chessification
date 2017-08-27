package com.parsonf.chessification.players;

import java.util.Set;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Game;
import com.parsonf.chessification.Move;

public class AIPlayer extends Player {
	private IPlayStyle playStyle;

	// Constructors --------------------------------------------------------
	public AIPlayer(Game game, boolean color, IPlayStyle playStyle) {
		super(game, color);
		this.playStyle = playStyle;
	}
	

	// Methods -------------------------------------------------------------
	
	public Move minimaxDepthFirst(Board thinkBoard, Player player, int ply) {
		// range within supposed-best-move's score to decide to evaluate move or prune the branch.
		final int PRUNE_CUTOFF = 120;
		// how many plies to search
		final int MAX_PLY = 4;
		int scoreThisBoard;
		
		if (ply == 0) {
			scoreThisBoard = playStyle.evaluateGameState(thinkBoard, player);
			return new Move(new Coord(0,0), new Coord(0,0), scoreThisBoard);
		} else {
			Set<Move> allLegalMoves = getAllLegalMoves(thinkBoard, player, Player.CHECK_CHECK, Player.CHECK_CASTLE);
			int whatSeemsLikeTheBestMove = getBestScore(thinkBoard, player, allLegalMoves);
			int bestNodeValue = (player.getColor() == Color.BLACK) ? -900000 : 900000;
			int currentNodeValue;
			Move bestMove = null;
			int cutoffPoint = 0;
			if (player.getColor() == Color.BLACK) {
				bestNodeValue = 900000;
				cutoffPoint = whatSeemsLikeTheBestMove + (PRUNE_CUTOFF / ((MAX_PLY + 1) - ply));
			} else {
				bestNodeValue = -900000;
				cutoffPoint = whatSeemsLikeTheBestMove - (PRUNE_CUTOFF / ((MAX_PLY + 1) - ply));
			}
			if (allLegalMoves.isEmpty()) {
				scoreThisBoard = playStyle.evaluateGameState(thinkBoard, player);
				return new Move(new Coord(0,0), new Coord(0,0), scoreThisBoard);
			} else {
				for (Move move: allLegalMoves) {
					Board hypotheticalBoard = (Board) thinkBoard.clone();
					hypotheticalBoard.move(move, Move.HYPOTHETICAL);
					int moveValue = playStyle.evaluateGameState(hypotheticalBoard, player);
					if (player.getColor() == Color.BLACK) {
						if (moveValue <= cutoffPoint) {
							currentNodeValue = minimaxDepthFirst(hypotheticalBoard, player.getOpponent(), ply-1).getScore();
							if (currentNodeValue < bestNodeValue) {
								bestNodeValue = currentNodeValue;
								bestMove = move;
							}
						}
					} else {
						if (moveValue >= cutoffPoint) {
							currentNodeValue = minimaxDepthFirst(hypotheticalBoard, player.getOpponent(), ply-1).getScore();
							if (currentNodeValue > bestNodeValue) {
								bestNodeValue = currentNodeValue;
								bestMove = move;
							}
						}
					}
				}
			}
			return bestMove;
		}
	}

	/**
	 * Evaluates each move of the moves supplied on the given board and returns the best score.
	 * 
	 * @param thinkBoard
	 * @param color
	 * @param moves
	 * @return
	 */
	private int getBestScore(Board thinkBoard, Player player, Set<Move> moves) {
		// Ensure that the first move we find will be better than this!
		int score = 0;
		int bestScore = (color == Color.BLACK) ? 900000 : -900000;
		Board hypotheticalBoard = null;
		for (Move move: moves) {
			hypotheticalBoard = (Board) thinkBoard.clone();
			hypotheticalBoard.move(move, Move.HYPOTHETICAL);
			score = playStyle.evaluateGameState(hypotheticalBoard, player);
			if (color == Color.BLACK) {
				bestScore = (bestScore > score) ? score : bestScore;
			} else {
				bestScore = (bestScore < score) ? score : bestScore;
			}
		}
		return bestScore;
	}
	
	

	// Getters/Setters -----------------------------------------------------

}