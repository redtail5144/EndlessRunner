/*
 * This class contains
 * the game frame
 */

package com.runner.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends Canvas {

	private static final long serialVersionUID = -1956582493613536306L;

	// Program icon and logo
	private BufferedImage icon = null, logo = null;
	private int w, h;

	// public frame(int width, int height, String title, Game game) {
	public Window(String title, Game game) {
		// Sets up icon
		BufferedImageLoader loader = new BufferedImageLoader();
		icon = loader.loadImage("/images/icon.png");
		logo = loader.loadImage("/images/logo.png");
		splashScreen(title, game);
	}

	// The actual game frame
	private void gameWindow(String title, Game game) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// Creates the frame of the frame
		JFrame frame = new JFrame(title);
		frame.setUndecorated(true);
		frame.setSize(screenSize);
		frame.setIconImage(icon);

		// Set up other frame settings
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes X button work
		frame.setResizable(false); // Cannot resize frame
		frame.setLocationRelativeTo(null); // Starts frame in center of screen
		frame.add(game); // adds game to frame
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true); // allows user to see the frame
		frame.pack();

		game.start(); // starts the game
	}

	// Displays the Logo
	private void splashScreen(String title, Game game) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// Creates the frame of the frame
		JFrame frame = new JFrame(title);
		frame.setUndecorated(true);
		frame.setSize(screenSize);
		frame.setIconImage(icon);

		// Set up other frame settings
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes X button work
		frame.setResizable(false); // Cannot resize frame
		frame.setLocationRelativeTo(null); // Starts frame in center of screen
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true); // allows user to see the frame
		w = frame.getContentPane().getWidth();
		h = frame.getContentPane().getHeight();
		System.out.println(frame.getContentPane().getWidth());
		System.out.println(frame.getContentPane().getHeight());
		
		// Scaled image
		BufferedImage scaled = new BufferedImage(w, h, logo.getType());

		// Scales the input image to the output image
		Graphics2D g2d = scaled.createGraphics();
		g2d.drawImage(logo, 0, 0, w, h, null);
		g2d.dispose();

		logo = scaled;
		frame.getContentPane().add(new JLabel(new ImageIcon(logo)));
		
		frame.pack();

		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Closes self
		frame.setVisible(false);

		gameWindow(title, game);
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

}
