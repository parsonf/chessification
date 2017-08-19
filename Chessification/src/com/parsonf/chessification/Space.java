package com.parsonf.chessification;

import com.parsonf.chessification.pieces.Piece;

public class Space {
	public static final Space VACANT = null;
	
	private boolean occupied;
	private Piece piece;
	
	// constructors --------------------------------------------------------------------------
	
	public Space() {
		this(null);
	}
	
	public Space(Piece piece) {
		this.piece = piece;
		this.occupied = (piece == null) ? false : true;
	}
	
	
	// getters and setters -------------------------------------------------------------------
	
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
		occupied = true;
	}
	public Piece pickUpPiece() {
		Piece piece = this.piece;
		this.piece = null;
		occupied = false;
		return piece;
	}
}
