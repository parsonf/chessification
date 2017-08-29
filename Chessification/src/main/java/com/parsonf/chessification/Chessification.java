package com.parsonf.chessification;

import com.parsonf.chessification.gui.Window;

public class Chessification {

	public static final String APP_TITLE = "Chessification";
	public Window window;
	public Game game;
	
	public Chessification() {
		window = new Window();
		game = new Game();
	}
	
	public static void main(String[] args) {
		new Chessification();
	}
}