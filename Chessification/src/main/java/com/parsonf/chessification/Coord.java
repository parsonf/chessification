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
	
	/**
	 * Returns a new Coord that adds the coord provided to this coord.
	 * This coord remains unaffected.
	 * 
	 * @param b
	 * @return
	 */
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
	
	@Override
	public String toString() {
		return getColLetter(col) + String.valueOf(row);
	}
	
	private String getColLetter(int colAsNumber) {
		String letter = "";
		switch (colAsNumber) {
		case 1:
			letter = "A";
			break;
		case 2:
			letter = "B";
			break;
		case 3:
			letter = "C";
			break;
		case 4:
			letter = "D";
			break;
		case 5:
			letter = "E";
			break;
		case 6:
			letter = "F";
			break;
		case 7:
			letter = "G";
			break;
		case 8:
			letter = "H";
			break;
		default:
			throw new RuntimeException("Invalid colAsNumber");
		}
		return letter;
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
