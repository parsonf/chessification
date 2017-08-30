package com.parsonf.chessification.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.parsonf.chessification.Chessification;

public class Window extends JFrame {
	private static final long serialVersionUID = 1;
	
	public static final int WIDTH = 820;
	public static final int HEIGHT = 760;
	
	public static final String DARK_SPACE = "./space_dark.png";
	public static final String LIGHT_SPACE = "./space_light.png";
	public static final String VACANT_SPACE = "./vacant.png";
	
	public Window() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setTitle(Chessification.APP_TITLE);
		initComponents();
		this.setVisible(true);
	}
	
	private void initComponents() {		
		// setup layered pain
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JLayeredPane layeredPane = new JLayeredPane();
		
		// build the board
		JPanel boardContainer = new JPanel();
		boardContainer.setLayout(new BoxLayout(boardContainer, BoxLayout.Y_AXIS));
		boardContainer.setSize(new Dimension(800, 720));
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
			boardContainer.add(panel);
		}
		layeredPane.add(boardContainer, new Integer(1));
		
		// build the pieceGrid
		JPanel pieceContainer = new JPanel();
		pieceContainer.setOpaque(false);
		pieceContainer.setLayout(new BoxLayout(pieceContainer, BoxLayout.Y_AXIS));
		pieceContainer.setSize(new Dimension(800, 720));
		JButton[][] pieceGrid = new JButton[8][8];
		URL vacantUrl = getClass().getClassLoader().getResource(VACANT_SPACE);
		for (int y=0; y<8; y++) {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
			panel.setSize(800, 90);
			panel.setOpaque(false);
			for (int x=0; x<8; x++) {
				JButton button = pieceGrid[y][x] = new JButton();
				button.setPreferredSize(new Dimension(100, 90));
				button.setBorderPainted(false);
				// be transparent
				button.setOpaque(false);
				button.setContentAreaFilled(false);
				button.setBorderPainted(false);
				try {
					Image image = ImageIO.read(vacantUrl);
					button.setIcon(new ImageIcon(image));
				} catch (IOException e) {
					e.printStackTrace();
				}
				panel.add(button);
			}
			pieceContainer.add(panel);
		}
		layeredPane.add(pieceContainer, new Integer(2));
		
		// black resources
		URL blackPawnUrl = getClass().getClassLoader().getResource("./black_pawn.png");
		URL blackRookUrl = getClass().getClassLoader().getResource("./black_rook.png");
		URL blackKnightUrl = getClass().getClassLoader().getResource("./black_knight.png");
		URL blackBishopUrl = getClass().getClassLoader().getResource("./black_bishop.png");
		URL blackQueenUrl = getClass().getClassLoader().getResource("./black_queen.png");
		URL blackKingUrl = getClass().getClassLoader().getResource("./black_king.png");
		// white resources
		URL whitePawnUrl = getClass().getClassLoader().getResource("./white_pawn.png");
		URL whiteRookUrl = getClass().getClassLoader().getResource("./white_rook.png");
		URL whiteKnightUrl = getClass().getClassLoader().getResource("./white_knight.png");
		URL whiteBishopUrl = getClass().getClassLoader().getResource("./white_bishop.png");
		URL whiteQueenUrl = getClass().getClassLoader().getResource("./white_Queen.png");
		URL whiteKingUrl = getClass().getClassLoader().getResource("./white_king.png");
		// now set the board
		for(int x=0; x<8; x++) {
			pieceGrid[1][x].setIcon(new ImageIcon(blackPawnUrl));
			pieceGrid[6][x].setIcon(new ImageIcon(whitePawnUrl));
		}
		// black
		pieceGrid[0][0].setIcon(new ImageIcon(blackRookUrl));
		pieceGrid[0][1].setIcon(new ImageIcon(blackKnightUrl));
		pieceGrid[0][2].setIcon(new ImageIcon(blackBishopUrl));
		pieceGrid[0][3].setIcon(new ImageIcon(blackQueenUrl));
		pieceGrid[0][4].setIcon(new ImageIcon(blackKingUrl));
		pieceGrid[0][5].setIcon(new ImageIcon(blackBishopUrl));
		pieceGrid[0][6].setIcon(new ImageIcon(blackKnightUrl));
		pieceGrid[0][7].setIcon(new ImageIcon(blackRookUrl));
		// white
		pieceGrid[7][0].setIcon(new ImageIcon(whiteRookUrl));
		pieceGrid[7][1].setIcon(new ImageIcon(whiteKnightUrl));
		pieceGrid[7][2].setIcon(new ImageIcon(whiteBishopUrl));
		pieceGrid[7][3].setIcon(new ImageIcon(whiteQueenUrl));
		pieceGrid[7][4].setIcon(new ImageIcon(whiteKingUrl));
		pieceGrid[7][5].setIcon(new ImageIcon(whiteBishopUrl));
		pieceGrid[7][6].setIcon(new ImageIcon(whiteKnightUrl));
		pieceGrid[7][7].setIcon(new ImageIcon(whiteRookUrl));
		
		// and finish up
		this.setContentPane(layeredPane);
	}
}
