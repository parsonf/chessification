package com.parsonf.chessification.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.parsonf.chessification.Chessification;

public class Window extends JFrame {
	private static final long serialVersionUID = 1;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	
	public static final String DARK_SPACE = "./space_dark.png";
	public static final String LIGHT_SPACE = "./space_light.png";
	
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension(dim.width, dim.height));
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setTitle(Chessification.APP_TITLE);
		initComponents();
		this.setVisible(true);
	}
	
	private void initComponents() {		
		// setup main window
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setSize(new Dimension(800, 720));
		
		// build the board
		JButton[][] boardGrid = new JButton[8][8];
		URL darkUrl = getClass().getClassLoader().getResource(DARK_SPACE);
		URL lightUrl = getClass().getClassLoader().getResource(LIGHT_SPACE);
		boolean darkSpace = false;
		for (int y=0; y<8; y++) {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
			panel.setSize(800, 90);
			darkSpace = !darkSpace;
			for (int x=0; x<8; x++) {
				darkSpace = !darkSpace;
				JButton button = boardGrid[y][x] = new JButton();
				button.setPreferredSize(new Dimension(100, 90));
				button.setBorderPainted(false);
				try {
					Image image = ImageIO.read(darkSpace ? darkUrl : lightUrl);
					button.setIcon(new ImageIcon(image));
				} catch (IOException e) {
					e.printStackTrace();
				}
				panel.add(button);
			}
			container.add(panel);
		}
		this.getContentPane().add(container);
		this.pack();
	}
}
