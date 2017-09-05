package com.parsonf.chessification;

import java.net.URL;

public class ResourceLoader {
	
	public static final String BLACK_PAWN = "./black_pawn.png";
	public static final String BLACK_ROOK = "./black_rook.png";
	public static final String BLACK_KNIGHT = "./black_knight.png";
	public static final String BLACK_BISHOP = "./black_bishop.png";
	public static final String BLACK_QUEEN = "./black_queen.png";
	public static final String BLACK_KING = "./black_king.png";
	
	public static final String WHITE_PAWN = "./white_pawn.png";
	public static final String WHITE_ROOK = "./white_rook.png";
	public static final String WHITE_KNIGHT = "./white_knight.png";
	public static final String WHITE_BISHOP = "./white_bishop.png";
	public static final String WHITE_QUEEN = "./white_queen.png";
	public static final String WHITE_KING = "./white_king.png";
	
	public static final String VACANT_SPACE = "./vacant.png";
	public static final String LIGHT_SPACE = "./space_light.png";
	public static final String DARK_SPACE = "./space_dark.png";
	public static final String VALID_MOVE = "./valid_move.png";

	private static ResourceLoader resourceLoader;

	private ResourceLoader() {

	}

	public static ResourceLoader getInstance() {
		if (resourceLoader == null) {
			resourceLoader = new ResourceLoader();
		}
		return resourceLoader;
	}

	public URL findResource(String name) {
		return getClass().getClassLoader().getResource(name);
	}
}
