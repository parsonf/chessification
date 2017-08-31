package com.parsonf.chessification.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.parsonf.chessification.Chessification;
import com.parsonf.chessification.ResourceLoader;

public class Window extends JFrame {
	private static final long serialVersionUID = 1;
	
	public static final int WIDTH = 1220;
	public static final int HEIGHT = 760;
	
	private Chessification chessification;
	
	private JLayeredPane layeredPane;
	private JButton[][] pieceGrid;
	private JButton[][] boardGrid;
	
	public Window(Chessification chessification) {
		this.chessification = chessification;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setTitle(Chessification.APP_TITLE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		initComponents();
		this.setVisible(true);
	}
	
	private void initComponents() {
		JPanel optionsPanel = buildOptionsPanel();
		this.getContentPane().add(optionsPanel);
		JPanel boardPanel = buildBoardPanel();
		this.getContentPane().add(boardPanel);
	}
	
	private JPanel buildOptionsPanel() {
		JPanel optionsPanel = new JPanel();
		optionsPanel.setPreferredSize(new Dimension(400, 720));
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		optionsPanel.setBorder(BorderFactory.createTitledBorder(loweredetched, "Options"));
		JButton newGameButton = new JButton("Play as White Against AI");
		newGameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				chessification.newGame();
				setPiecesForStandardGame();
			}
		});
		optionsPanel.add(newGameButton);
		return optionsPanel;
	}

	private JPanel buildBoardPanel() {
		layeredPane = new JLayeredPane();
		
		// build the boardGrid
		JPanel boardContainer = new JPanel();
		boardContainer.setLayout(new BoxLayout(boardContainer, BoxLayout.Y_AXIS));
		boardContainer.setSize(new Dimension(800, 720));
		boardGrid = new JButton[8][8];
		ResourceLoader loader = ResourceLoader.getInstance();
		URL darkUrl = loader.findResource(ResourceLoader.DARK_SPACE);
		URL lightUrl = loader.findResource(ResourceLoader.LIGHT_SPACE);
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
		pieceGrid = new JButton[8][8];
		URL vacantUrl = loader.findResource(ResourceLoader.VACANT_SPACE);
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
		layeredPane.setPreferredSize(new Dimension(800, 720));
		
		// and finish up
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panel.add(layeredPane);
		return panel;
	}
	
	private void setPiecesForStandardGame() {
		// now set the board
		ResourceLoader loader = ResourceLoader.getInstance();
		for(int x=0; x<8; x++) {
			pieceGrid[1][x].setIcon(new ImageIcon(loader.findResource(ResourceLoader.BLACK_PAWN)));
			pieceGrid[2][x].setIcon(new ImageIcon(loader.findResource(ResourceLoader.VACANT_SPACE)));
			pieceGrid[3][x].setIcon(new ImageIcon(loader.findResource(ResourceLoader.VACANT_SPACE)));
			pieceGrid[4][x].setIcon(new ImageIcon(loader.findResource(ResourceLoader.VACANT_SPACE)));
			pieceGrid[5][x].setIcon(new ImageIcon(loader.findResource(ResourceLoader.VACANT_SPACE)));
			pieceGrid[6][x].setIcon(new ImageIcon(loader.findResource(ResourceLoader.WHITE_PAWN)));
		}
		// black non-pawns
		pieceGrid[0][0].setIcon(new ImageIcon(loader.findResource(ResourceLoader.BLACK_ROOK)));
		pieceGrid[0][1].setIcon(new ImageIcon(loader.findResource(ResourceLoader.BLACK_KNIGHT)));
		pieceGrid[0][2].setIcon(new ImageIcon(loader.findResource(ResourceLoader.BLACK_BISHOP)));
		pieceGrid[0][3].setIcon(new ImageIcon(loader.findResource(ResourceLoader.BLACK_QUEEN)));
		pieceGrid[0][4].setIcon(new ImageIcon(loader.findResource(ResourceLoader.BLACK_KING)));
		pieceGrid[0][5].setIcon(new ImageIcon(loader.findResource(ResourceLoader.BLACK_BISHOP)));
		pieceGrid[0][6].setIcon(new ImageIcon(loader.findResource(ResourceLoader.BLACK_KNIGHT)));
		pieceGrid[0][7].setIcon(new ImageIcon(loader.findResource(ResourceLoader.BLACK_ROOK)));
		// white non-pawns
		pieceGrid[7][0].setIcon(new ImageIcon(loader.findResource(ResourceLoader.WHITE_ROOK)));
		pieceGrid[7][1].setIcon(new ImageIcon(loader.findResource(ResourceLoader.WHITE_KNIGHT)));
		pieceGrid[7][2].setIcon(new ImageIcon(loader.findResource(ResourceLoader.WHITE_BISHOP)));
		pieceGrid[7][3].setIcon(new ImageIcon(loader.findResource(ResourceLoader.WHITE_QUEEN)));
		pieceGrid[7][4].setIcon(new ImageIcon(loader.findResource(ResourceLoader.WHITE_KING)));
		pieceGrid[7][5].setIcon(new ImageIcon(loader.findResource(ResourceLoader.WHITE_BISHOP)));
		pieceGrid[7][6].setIcon(new ImageIcon(loader.findResource(ResourceLoader.WHITE_KNIGHT)));
		pieceGrid[7][7].setIcon(new ImageIcon(loader.findResource(ResourceLoader.WHITE_ROOK)));
	}
}
