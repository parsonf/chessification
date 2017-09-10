package com.parsonf.chessification.players;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.swing.text.ChangedCharSetException;

import org.junit.Test;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Chessification;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Game;
import com.parsonf.chessification.GameType;
import com.parsonf.chessification.Move;

public class PlayerTest {

	@Test
	public void getAllLegalMoves_CheckCheckmatedPlayer_ReturnsEmptySet() {
		// set up the test
		Chessification chess = new Chessification(Chessification.NO_GUI);
		Game game = new Game(chess, GameType.AI_IS_BLACK);
		Board board = game.getBoard();
		Player white = game.getPlayer(Color.WHITE);
		// let's check fool's mate. (2-move checkmate).
		board.move(new Move(new Coord(Coord.COL_G, Coord.ROW_2), new Coord(Coord.COL_G, Coord.ROW_4)), Move.HYPOTHETICAL);
		board.move(new Move(new Coord(Coord.COL_E, Coord.ROW_7), new Coord(Coord.COL_E, Coord.ROW_6)), Move.HYPOTHETICAL);
		board.move(new Move(new Coord(Coord.COL_F, Coord.ROW_2), new Coord(Coord.COL_F, Coord.ROW_3)), Move.HYPOTHETICAL);
		board.move(new Move(new Coord(Coord.COL_D, Coord.ROW_8), new Coord(Coord.COL_H, Coord.ROW_4)), Move.HYPOTHETICAL);
		// now the board should be in a state where white is checkmated.
		// let's make sure that getAllLegalMoves returns an empty set.
		Set<Move> moves = white.getAllLegalMoves(board, Player.CHECK_CHECK, Player.CHECK_CASTLE);
		//System.out.println("checkmated player has moves: " + moves);
		assertTrue("Checkmated player should not have any moves to make.", moves.isEmpty());
	}
}
