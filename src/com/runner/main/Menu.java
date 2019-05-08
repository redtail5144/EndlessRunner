package com.runner.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.runner.main.Game.STATE;

public class Menu extends MouseAdapter {

	// Size of menu boxes
	private int xSize = 200, ySize = 64;
	// The game
	private Game game;
	// Game's handler
	private Handler handler;
	// Icons
	private BufferedImage speak = null, speakM = null;
	// Image loader
	BufferedImageLoader loader = new BufferedImageLoader();

	public Menu(Game game) {
		this.game = game;
		this.handler = game.getHand();

		speak = loader.loadImage("/images/speaker.png");
		speakM = loader.loadImage("/images/speakerMuted.png");
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		switch (game.gameState) {
		case Menu:
			// Play button
			if (mouseOver(mx, my, (Game.WIDTH / 2) - (xSize / 2), 100, xSize, ySize)) {
				game.getHud().setScore(0);
				game.gameState = STATE.Game;

				// Spawns shit
				handler.addObject(new Platform(0, Game.HEIGHT / 2 + 100, Game.WIDTH));
				handler.addObject(new Player(Game.WIDTH / 2, Game.HEIGHT / 2));
			}

			// Help Button
			if (mouseOver(mx, my, (Game.WIDTH / 2) - (xSize / 2), 200, xSize, ySize))
				game.gameState = STATE.Help;

			// Quit Button
			if (mouseOver(mx, my, (Game.WIDTH / 2) - (xSize / 2), 300, xSize, ySize))
				System.exit(1);

			// Sound Button
			if (mouseOver(mx, my, game.getWidth() - 55, game.getHeight() - 55, 50, 50))
				game.muted = !game.muted;

			// Credits Button
			if (mouseOver(mx, my, 2, game.getHeight() - 55, 110, 50))
				game.gameState = STATE.Credits;

			break;

		case GameOver:
			// AudioPlayer.pause("gameMusic");
			// if (!game.muted)
			// AudioPlayer.getMusic("menuMusic").loop();
		case Help:
			// Help Button
			if (mouseOver(mx, my, (Game.WIDTH / 2) - (xSize / 2), 300, xSize, ySize))
				game.gameState = STATE.Menu;
			break;

		case Credits:
			// Back Button
			if (mouseOver(mx, my, (Game.WIDTH / 2) - (xSize / 2), 300, xSize, ySize))
				game.gameState = STATE.Menu;
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width)
			if (my > y && my < y + height)
				return true;

		return false;
	}

	public void tick() {

	}

	public void renderCredits(Graphics g) {
		Font fnt = new Font("arial", 1, 50), fnt2 = new Font("arial", 1, 30), fnt3 = new Font("arial", 1, 20);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 70), "Credits", fnt);

		// My Credits
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 190), "Programming and \"Art\" by:", fnt3);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 230), "Austin Campbell", fnt3);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 270), "Twitter.com/Redtail5144", fnt3);

		// Music Credits
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 370), "Music by: ", fnt3);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 420), "Jayden Rutschke", fnt3);

		// Website
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 560), "StudioWithAHat.com", fnt3);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 660), "Back", fnt2);
		g.drawRect((Game.WIDTH / 2) - (xSize / 2), 300, xSize, ySize);
	}

	public void renderMenu(Graphics g) {
		Font fnt = new Font("arial", 1, 50), fnt2 = new Font("arial", 1, 30);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 70), "Menu", fnt);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 260), "Play", fnt2);
		g.drawRect((Game.WIDTH / 2) - (xSize / 2), 100, xSize, ySize);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 460), "Help", fnt2);
		g.drawRect((Game.WIDTH / 2) - (xSize / 2), 200, xSize, ySize);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 660), "Quit", fnt2);
		g.drawRect((Game.WIDTH / 2) - (xSize / 2), 300, xSize, ySize);

		// Speaker icon
		if (game.muted)
			g.drawImage(speakM, game.getWidth() - 55, game.getHeight() - 55, null);
		else
			g.drawImage(speak, game.getWidth() - 55, game.getHeight() - 55, null);
		g.drawRect(game.getWidth() - 55, game.getHeight() - 55, 50, 50);

		g.drawString("Credits", 4, game.getHeight() - 20);
		g.drawRect(2, game.getHeight() - 55, 110, 50);
	}

	public void renderHelp(Graphics g) {
		Font fnt = new Font("arial", 1, 50), fnt2 = new Font("arial", 1, 30), fnt3 = new Font("arial", 1, 20);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 70), "Help", fnt);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 270), "Use Space to jump on the platforms.", fnt3);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 320), "You can ride on the inside of the platforms,", fnt3);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 370), "But cannot jump while doing so.", fnt3);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 420), "Escape to Close.", fnt3);

		g.setFont(fnt2);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 660), "Back", fnt2);
		g.drawRect((Game.WIDTH / 2) - (xSize / 2), 300, xSize, ySize);
	}

	public void renderGameOver(Graphics g) {
		Font fnt = new Font("arial", 1, 50), fnt2 = new Font("arial", 1, 30), fnt3 = new Font("arial", 1, 20);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 70), "Rekt", fnt);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 370), "HA! You got fucking rekt scrub!", fnt3);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 420), "Final Score: " + game.getHud().getScore(), fnt3);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, 660), "Back", fnt2);
		g.drawRect((Game.WIDTH / 2) - (xSize / 2), 300, xSize, ySize);
	}

	public void renderSelect(Graphics g) {
		Font fnt = new Font("arial", 1, 50), fnt2 = new Font("arial", 1, 30);
		g.setFont(fnt);
		g.drawString("Difficulty", 220, 70);

		g.setFont(fnt2);
		g.drawString("Normal", 270, 140);
		g.drawRect((Game.WIDTH / 2) - (xSize / 2), 100, xSize, ySize);

		g.drawString("Hard", 285, 240);
		g.drawRect((Game.WIDTH / 2) - (xSize / 2), 200, xSize, ySize);

		g.drawString("Back", 285, 340);
		g.drawRect((Game.WIDTH / 2) - (xSize / 2), 300, xSize, ySize);
	}

	public void render(Graphics g) {
		g.setColor(Color.white);

		switch (game.gameState) {

		case Menu:
			renderMenu(g);
			break;

		case Help:
			renderHelp(g);
			break;

		case GameOver:
			renderGameOver(g);
			break;

		case Credits:
			renderCredits(g);
			break;

		default:
		}
	}
}
