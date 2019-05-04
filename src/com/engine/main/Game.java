/*
 * The Backbone of the game
 */

package com.engine.main;

import java.awt.Canvas;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -5888314627074306608L;

	// Width and Height of the Game/Window
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	// Games Sprite Sheet
	public static BufferedImage sprite_sheet;

	private Thread thread;
	private boolean running = false;

	// Handler for the game
	private static Handler handler;

	// Game HUD
	private HUD hud;

	// Game menu
	private Menu menu;

	public Game() {

		// Initializes Key components
		handler = new Handler();
		menu = new Menu(this);
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(menu);
		AudioPlayer.load();
		hud = new HUD();

		// Creates the window
		new Window(WIDTH, HEIGHT, "Title", this);
	}

	// Starts everything
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	// Stops everything
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Causes the game to tick
	private void tick() {

	}

	// Renders everything in the game
	private void render() {

	}

	// Game loop
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				tick();
				delta--;
			}

			if (running)
				render();

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	// Checks if game is over
	private boolean checkGameOver() {
		return false;
	}

	// Game is over
	private void gameOver() {

	}

	// Where everything starts
	public static void main(String agrs[]) {
		new Game();
	}

	// Returns handler
	public static Handler getHand() {
		return handler;
	}

}
