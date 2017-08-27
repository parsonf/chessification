package com.parsonf.chessification;

import java.util.Objects;

public class Move {
	public static final boolean HYPOTHETICAL = true;
	public static final boolean ACTUAL = false;
	private Coord from;
	private Coord to;
	private int score;

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
		this.score = score;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
