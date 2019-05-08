/*
 * The Backbone of the game
 */

package com.runner.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import com.runner.main.AudioPlayer;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -5888314627074306608L;

	// Width and Height of the Game/Window
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	//If game is muted
	public static boolean muted = false;

	// Games Sprite Sheet
	public static BufferedImage sprite_sheet;

	// Various Game States
	public enum STATE {
		Menu, Help, Game, Credits, GameOver;
	};

	// Current Game State
	public STATE gameState = STATE.Menu;

	private Thread thread;
	private boolean running = false;

	// Handler for the game
	private static Handler handler;

	// Game HUD
	private static HUD hud;

	// Game menu
	private Menu menu;
	
	// Game title
	private Titles title = new Titles();

	public Game() {

		// Initializes Key components
		handler = new Handler();
		menu = new Menu(this);
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(menu);
		hud = new HUD();
		
		// Load Audio
		AudioPlayer.load();
		AudioPlayer.getMusic("music");
		AudioPlayer.playing = false;
		
		// Creates the window
		new Window(WIDTH, HEIGHT, title.getTitle(), this);
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
		
		if (muted) {
			AudioPlayer.pause("music");
			AudioPlayer.playing = false;
		} else {
			if (!AudioPlayer.playing) {
				AudioPlayer.getMusic("music").loop();
				AudioPlayer.playing = true;
			}
		}
		
		
		if (checkGameOver())
			gameOver();

		if (gameState == STATE.Game) {
			handler.tick();
			hud.tick();
		} else {
			menu.tick();
			handler.tick();
		}
	}

	// Renders everything in the game
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// Fills the window one solid colour
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (gameState == STATE.Game) {
			handler.render(g);
			hud.render(g);
		}
		else
			menu.render(g);

		g.dispose();
		bs.show();
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
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player)
				if (tempObject.getY() > HEIGHT)
					return true;
		}
		return false;
	}

	// Game is over
	private void gameOver() {
		handler.object.clear();
		gameState = STATE.GameOver;
	}

	// Where everything starts
	public static void main(String agrs[]) {
		new Game();
	}

	// Returns handler
	public static Handler getHand() {
		return handler;
	}

	// Returns HUD
	public static HUD getHud() {
		return hud;
	}
}
