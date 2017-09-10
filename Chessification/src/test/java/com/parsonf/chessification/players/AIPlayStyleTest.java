package com.parsonf.chessification.players;

import org.junit.Before;
import org.junit.Test;

import com.parsonf.chessification.Board;
import com.parsonf.chessification.Chessification;
import com.parsonf.chessification.Color;
import com.parsonf.chessification.Coord;
import com.parsonf.chessification.Move;
import com.parsonf.chessification.pieces.Bishop;
import com.parsonf.chessification.pieces.Knight;
import com.parsonf.chessification.pieces.Queen;
import com.parsonf.chessification.pieces.Rook;

public class AIPlayStyleTest {
	private Chessification chess;
	private Board board;
	private BrianAIPlayStyle playStyle;
	
	@Before
	public void setUp() {
		chess = new Chessification(Chessification.NO_GUI);
		board = chess.getGame().getBoard();
		playStyle = new BrianAIPlayStyle();
	}

	@Test
	public void evaluateGameState_FreshBoardWhite() {
		board.reset();
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.WHITE));
		//System.out.println("white value at setup = " + value);
	}

	@Test
	public void evaluateGameState_FreshBoardBlack() {
		board.reset();
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.BLACK));
		//System.out.println("black value at setup = " + value);
	}

	@Test
	public void evaluateGameState_FreshBoardWhitePawnD4White() {
		board.reset();
		board.move(new Move(new Coord(Coord.COL_D, Coord.ROW_2), new Coord(Coord.COL_D, Coord.ROW_4)), Move.HYPOTHETICAL);
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.WHITE));
		//System.out.println("white value at setup, white pawn to d4 = " + value);
	}

	@Test
	public void evaluateGameState_FreshBoardWhitePawnD4Black() {
		board.reset();
		board.move(new Move(new Coord(Coord.COL_D, Coord.ROW_2), new Coord(Coord.COL_D, Coord.ROW_4)), Move.HYPOTHETICAL);
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.BLACK));
		//System.out.println("black value at setup, white pawn to d4 = " + value);
	}
	
	@Test
	public void evaluateGameState_JustWhitePawns() {
		board.reset();
		for (int x = Coord.COL_MIN; x <= Coord.COL_MAX; x++) {
			board.getSpace(new Coord(x, Coord.ROW_1)).pickUpPiece();
			board.getSpace(new Coord(x, Coord.ROW_7)).pickUpPiece();
			board.getSpace(new Coord(x, Coord.ROW_8)).pickUpPiece();
		}
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.WHITE));
		//System.out.println("white value just the pawns = " + value);
	}
	
	@Test
	public void evaluateGameState_JustBlackPawns() {
		board.reset();
		for (int x = Coord.COL_MIN; x <= Coord.COL_MAX; x++) {
			board.getSpace(new Coord(x, Coord.ROW_1)).pickUpPiece();
			board.getSpace(new Coord(x, Coord.ROW_2)).pickUpPiece();
			board.getSpace(new Coord(x, Coord.ROW_8)).pickUpPiece();
		}
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.BLACK));
		//System.out.println("black value just the pawns = " + value);
	}
	
	@Test
	public void evaluateGameState_JustWhiteRooks() {
		board.clear();
		Rook a1Rook = new Rook(Color.WHITE);
		Rook h1Rook = new Rook(Color.WHITE);
		board.setDownPiece(a1Rook, new Coord(Coord.COL_A, Coord.ROW_1));
		board.setDownPiece(h1Rook, new Coord(Coord.COL_H, Coord.ROW_1));
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.WHITE));
		//System.out.println("just the white rooks value = " + value);
	}
	
	@Test
	public void evaluateGameState_JustBlackRooks() {
		board.clear();
		Rook a8Rook = new Rook(Color.BLACK);
		Rook h8Rook = new Rook(Color.BLACK);
		board.setDownPiece(a8Rook, new Coord(Coord.COL_A, Coord.ROW_8));
		board.setDownPiece(h8Rook, new Coord(Coord.COL_H, Coord.ROW_8));
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.BLACK));
		//System.out.println("just the black rooks value = " + value);
	}
	
	@Test
	public void evaluateGameState_JustWhiteKnights() {
		board.clear();
		Knight b1Knight = new Knight(Color.WHITE);
		Knight g1Knight = new Knight(Color.WHITE);
		board.setDownPiece(b1Knight, new Coord(Coord.COL_B, Coord.ROW_1));
		board.setDownPiece(g1Knight, new Coord(Coord.COL_G, Coord.ROW_1));
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.WHITE));
		//System.out.println("just the white knights value = " + value);
	}
	
	@Test
	public void evaluateGameState_JustBlackKnights() {
		board.clear();
		Knight b8Knight = new Knight(Color.BLACK);
		Knight g8Knight = new Knight(Color.BLACK);
		board.setDownPiece(b8Knight, new Coord(Coord.COL_B, Coord.ROW_8));
		board.setDownPiece(g8Knight, new Coord(Coord.COL_G, Coord.ROW_8));
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.BLACK));
		//System.out.println("just the black knights value = " + value);
	}
	
	@Test
	public void evaluateGameState_JustWhiteBishops() {
		board.clear();
		Bishop c1Bishop = new Bishop(Color.WHITE);
		Bishop f1Bishop = new Bishop(Color.WHITE);
		board.setDownPiece(c1Bishop, new Coord(Coord.COL_C, Coord.ROW_1));
		board.setDownPiece(f1Bishop, new Coord(Coord.COL_F, Coord.ROW_1));
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.WHITE));
		//System.out.println("just the white bishops value = " + value);
	}
	
	@Test
	public void evaluateGameState_JustBlackBishops() {
		board.clear();
		Bishop c8Bishop = new Bishop(Color.BLACK);
		Bishop f8Bishop = new Bishop(Color.BLACK);
		board.setDownPiece(c8Bishop, new Coord(Coord.COL_C, Coord.ROW_8));
		board.setDownPiece(f8Bishop, new Coord(Coord.COL_F, Coord.ROW_8));
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.BLACK));
		//System.out.println("just the black bishops value = " + value);
	}
	
	@Test
	public void evaluateGameState_JustWhiteQueen() {
		board.clear();
		Queen queen = new Queen(Color.WHITE);
		board.setDownPiece(queen, new Coord(Coord.COL_D, Coord.ROW_1));
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.WHITE));
		//System.out.println("just the white queen value = " + value);
	}
	
	@Test
	public void evaluateGameState_JustBlackQueen() {
		board.clear();
		Queen queen = new Queen(Color.BLACK);
		board.setDownPiece(queen, new Coord(Coord.COL_D, Coord.ROW_8));
		int value = playStyle.evaluateGameState(board, chess.getGame().getPlayer(Color.BLACK));
		//System.out.println("just the black queen value = " + value);
	}
}
