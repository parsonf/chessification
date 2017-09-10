package com.parsonf.chessification;

import java.util.Objects;

public class Move {
	public static final boolean HYPOTHETICAL = false;
	public static final boolean ACTUAL = true;
	private Coord from;
	private Coord to;

	// constructors --------------------------------------------------------------------------
	
	public Move(int fromX, int fromY, int toX, int toY) {
		this(new Coord(fromX, fromY), new Coord(toX, toY));
	}
	
	public Move(Coord from, Coord to) {
		this.from = from;
		this.to = to;
	}
	
	public Move(Coord from, Coord to, int score) {
		this.from = from;
		this.to = to;
	}
	// methods --------------------------------------------------------------------------------
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Move)) return false;
		Move move = (Move) o;
		return Objects.equals(from, move.from) && Objects.equals(to, move.to);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(from, to);
	}
	
	@Override
	public String toString() {
		return from.toString() + " -> " + to.toString();
	}

	// getters and setters -------------------------------------------------------------------
	public Coord getFrom() {
		return from;
	}

	public void setFrom(Coord from) {
		this.from = from;
	}

	public Coord getTo() {
		return to;
	}

	public void setTo(Coord to) {
		this.to = to;
	}
}
