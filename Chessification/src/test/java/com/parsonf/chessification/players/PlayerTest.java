package com.parsonf.chessification.players;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Game;
import com.parsonf.chessification.Move;

public class PlayerTest {

	@Test
	public void getAllLegalMoves_CheckCheckmatedPlayer_ReturnsEmptySet() {
		Game game = new Game();
		Board board = game.getBoard();
		Player white = game.getPlayer(Color.WHITE);
		// let's check fool's mate. (2-move checkmate).
		board.move(new Move(new Coord(Coord.COL_G, Coord.ROW_2), new Coord(Coord.COL_G, Coord.ROW_4)), true);
		board.move(new Move(new Coord(Coord.COL_E, Coord.ROW_7), new Coord(Coord.COL_E, Coord.ROW_6)), true);
		board.move(new Move(new Coord(Coord.COL_F, Coord.ROW_2), new Coord(Coord.COL_F, Coord.ROW_3)), true);
		board.move(new Move(new Coord(Coord.COL_D, Coord.ROW_8), new Coord(Coord.COL_H, Coord.ROW_4)), true);
		// now the board should be in a state where white has no moves.
		// in other words, white is checkmated.
		// let's make sure that getAllLegalMoves returns an empty set.
		Set<Move> moves = white.getAllLegalMoves(board, white, Player.CHECK_CHECK, Player.CHECK_CASTLE);
		assertTrue("Checkmated player should not have any moves to make.", moves.isEmpty());
	}
}
