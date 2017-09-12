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
	
	public Space copy() {
		Space space = new Space();
		space.occupied = this.occupied;
		space.piece = this.piece.copy();
		return space;
	}
	
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
		occupied = false;
		if (this.piece == null) {
			return null;
		} else {
			Piece piece = this.piece.copy();
			this.piece = null;
			return piece;
		}
	}
	
	@Override
	public String toString() {
		if (occupied) {
			return "[" + piece.toString() + "]";
		} else {
			return "[  ]";
		}
	}
}
