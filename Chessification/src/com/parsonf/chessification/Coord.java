package com.parsonf.chessification;

import java.util.Objects;

public class Coord {
	// row constants.
	public static final int ROW_1 = 1;
	public static final int ROW_2 = 2;
	public static final int ROW_3 = 3;
	public static final int ROW_4 = 4;
	public static final int ROW_5 = 5;
	public static final int ROW_6 = 6;
	public static final int ROW_7 = 7;
	public static final int ROW_8 = 8;
	public static final int ROW_MIN = ROW_1;
	public static final int ROW_MAX = ROW_8;
	// col constants.
	public static final int COL_A = 1;
	public static final int COL_B = 2;
	public static final int COL_C = 3;
	public static final int COL_D = 4;
	public static final int COL_E = 5;
	public static final int COL_F = 6;
	public static final int COL_G = 7;
	public static final int COL_H = 8;
	public static final int COL_MIN = COL_A;
	public static final int COL_MAX = COL_H;

	protected int row;
	protected int col;
	
	// constructors --------------------------------------------------------------------------
	
	public Coord(int col, int row) {
		this.col = col;
		this.row = row;
	}

	// methods --------------------------------------------------------------------------------
	
	public Coord add(Coord b) {
		return new Coord(col + b.col, row + b.row);
	}

	public Object subtract(Coord b) {
		return new Coord(col - b.col, row - b.row);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Coord)) return false;
		Coord coord = (Coord) o;
		return row == coord.row && col == coord.col;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(col, row);
	}

	// getters and setters -------------------------------------------------------------------
	public void set(int col, int row) {
		this.col = col;
		this.row = row;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
}
