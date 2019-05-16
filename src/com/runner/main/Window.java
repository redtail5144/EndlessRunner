/*
 * This class contains
 * the game window
 */

package com.runner.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.runner.main.BufferedImageLoader;
import com.runner.main.Game;

public class Window extends Canvas {

	private static final long serialVersionUID = -1956582493613536306L;

	// Program icon
	private BufferedImage icon = null;

	public Window(int width, int height, String title, Game game) {
		// Sets up icon
		BufferedImageLoader loader = new BufferedImageLoader();
		icon = loader.loadImage("/images/icon.png");

		splashScreen(width, height, title, game);
	}

	// The actual game window
	private void gameWindow(int width, int height, String title, Game game) {
		// Creates the frame of the window
		JFrame frame = new JFrame(title);
		// frame.setIconImage(icon);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		// Set up other window settings
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes X button work
		frame.setResizable(false); // Cannot resize window
		frame.setLocationRelativeTo(null); // Starts frame in center of screen
		frame.add(game); // adds game to frame
		frame.setVisible(true); // allows user to see the window

		game.start(); // starts the game
	}

	// Displays the Logo
	private void splashScreen(int width, int height, String title, Game game) {
		// Creates the frame of the window
		JFrame window = new JFrame(title);
		// window.setIconImage(icon);

		// Inits the logo
		BufferedImage logo = null;
		BufferedImageLoader loader = new BufferedImageLoader();
		logo = loader.loadImage("/images/logo.png");

		// Displays Logo
		window.getContentPane().add(new JLabel(new ImageIcon(logo)));
		window.setVisible(true);

		// *******************FRAME*INITIALIZATION******************************

		// Set up window sizes
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes X button work
		window.setPreferredSize(new Dimension(width, height));
		window.setMaximumSize(new Dimension(width, height));
		window.setMinimumSize(new Dimension(width, height));
		window.setLocationRelativeTo(null); // Starts frame in center of screen

		// Sleeps for X seconds
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Closes self
		window.setVisible(false);

		// Opens the game
		gameWindow(width, height, title, game);
	}

}
