package com.parsonf.chessification.players;

import java.util.Set;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Game;
import com.parsonf.chessification.Move;

public class AIPlayer extends Player {
	// range within supposed-best-move's score to decide to evaluate move or prune the branch.
	private final int PRUNE_CUTOFF = 120;
	// how many plies to search
	private final int MAX_PLY = 1;
	private AIPlayStyle playStyle;
	
	// Constructors --------------------------------------------------------
	public AIPlayer(Game game, boolean color, AIPlayStyle playStyle) {
		super(game, color);
		this.playStyle = playStyle;
	}
	
	// Methods -------------------------------------------------------------
	
	public Move getBestMove(Board board) {
		Move bestMove = null;
		Set<Move> allLegalMoves = getAllLegalMoves(board, Player.CHECK_CHECK, Player.CHECK_CASTLE);
		Integer bestMoveScore = null;
		for (Move move : allLegalMoves) {
			Board thinkBoard = board.copy();
			thinkBoard.move(move, Move.ACTUAL);
			int moveScore = minimaxDepthFirst(thinkBoard, this.getOpponent(), MAX_PLY);
			bestMoveScore = (bestMoveScore == null) ? moveScore : bestMoveScore;
			if (moveScore >= bestMoveScore) {
				bestMoveScore = moveScore;
				bestMove = move;
			}
		}
		
		return bestMove;
	}
	
	private int minimaxDepthFirst(Board thinkBoard, Player player, int ply) {
		int bestScore = 0;
		
		if (ply == 0) {
			// we have searched deep enough into the future for a good move, it's time to head back.
			bestScore = playStyle.evaluateGameState(thinkBoard, player);
		} else {
			Set<Move> allLegalMoves = player.getAllLegalMoves(thinkBoard, Player.CHECK_CHECK, Player.CHECK_CASTLE);
			if (allLegalMoves.isEmpty()) {
				return playStyle.evaluateGameState(thinkBoard, player);
			} else {
				// this player has some moves available.
				// let's determine a cutoff point and only search the moves that
				// fall within that cutoff point.
				thinkBoard.setScore((player.getColor() == Color.BLACK) ? 900000 : -900000);
				int whatSeemsLikeTheBestScore = getBestMoveScore(thinkBoard, player, allLegalMoves);
				int pruneCutoffForBlack = whatSeemsLikeTheBestScore + (PRUNE_CUTOFF / ((MAX_PLY + 1) - ply));
				int pruneCutoffForWhite = whatSeemsLikeTheBestScore - (PRUNE_CUTOFF / ((MAX_PLY + 1) - ply));
				int cutoffPoint = (player.getColor() == Color.BLACK) ? pruneCutoffForBlack : pruneCutoffForWhite;
				// we've determined a cutoff point. so, let's loop through each move,
				// and only search deeper on moves that fall within the cutoff.
				for (Move move: allLegalMoves) {
					Board hypotheticalBoard = thinkBoard.copy();
					hypotheticalBoard.move(move, Move.ACTUAL);
					hypotheticalBoard.setScore(playStyle.evaluateGameState(hypotheticalBoard, player));
					if (player.getColor() == Color.BLACK) {
						if (hypotheticalBoard.getScore() <= cutoffPoint) {
							hypotheticalBoard.setScore(minimaxDepthFirst(hypotheticalBoard, player.getOpponent(), ply-1));
							if (hypotheticalBoard.getScore() < bestScore) {
								bestScore = hypotheticalBoard.getScore();
							}
						}
					} else {
						if (hypotheticalBoard.getScore() >= cutoffPoint) {
							hypotheticalBoard.setScore(minimaxDepthFirst(hypotheticalBoard, player.getOpponent(), ply-1));
							if (hypotheticalBoard.getScore() > bestScore) {
								bestScore = hypotheticalBoard.getScore();
							}
						}
					}
				}
			}
		}
		return bestScore;
	}

	/**
	 * Evaluates each move of the moves supplied on the given board and returns the best score.
	 * 
	 * @param thinkBoard
	 * @param color
	 * @param moves
	 * @return
	 */
	private int getBestMoveScore(Board thinkBoard, Player player, Set<Move> moves) {
		// Ensure that the first move we find will be better than this!
		int score = 0;
		int bestScore = (color == Color.BLACK) ? 900000 : -900000;
		Board hypotheticalBoard = null;
		for (Move move: moves) {
			hypotheticalBoard = thinkBoard.copy();
			hypotheticalBoard.move(move, Move.ACTUAL);
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