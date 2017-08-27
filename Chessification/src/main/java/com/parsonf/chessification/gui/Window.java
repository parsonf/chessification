package com.parsonf.chessification.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.parsonf.chessification.Chess;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(WIDTH, HEIGHT));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setTitle(Chess.APP_TITLE);
		this.setVisible(true);
	}
}
