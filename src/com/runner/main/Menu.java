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

	// Position of title
	private int pos;

	// Size of text
	private int textSize;

	private int textSpace;
	
	// Spaces between buttons
	private int space;

	// Fonts menu uses
	private Font fnt, fnt2, fnt3;

	// The game
	private Game game;

	// Game's handler
	private Handler handler;

	// Icons
	private BufferedImage speak = null, speakM = null;

	// Image loader
	BufferedImageLoader loader = new BufferedImageLoader();

	// Buttons
	MenuButton play, help, quit, back, credits;

	// Sound Button
	MenuButtonPic sound, soundM;

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
			if (mouseOver(mx, my, play)) {
				// if (mouseOver(mx, my, play.x, play.y, play.width, play.height)) {
				game.getHud().setScore(0);
				game.gameState = STATE.Game;

				// Spawns shit
				handler.addObject(new Platform(0, Game.HEIGHT / 2 + 100, Game.WIDTH));
				handler.addObject(new Player(Game.WIDTH / 2, Game.HEIGHT / 2));
			}

			// Help Button
			if (mouseOver(mx, my, help))
				game.gameState = STATE.Help;

			// Quit Button
			if (mouseOver(mx, my, quit)) {
				game.getHud().writeHscore();
				System.exit(1);
			}

			// Sound Button
			if (mouseOver(mx, my, sound) || mouseOver(mx, my, soundM))
				game.muted = !game.muted;

			// Credits Button
			if (mouseOver(mx, my, credits))
				game.gameState = STATE.Credits;

			break;

		case GameOver:
			// AudioPlayer.pause("gameMusic");
			// if (!game.muted)
			// AudioPlayer.getMusic("menuMusic").loop();
		case Help:
		case Credits:
			// Back Button
			if (mouseOver(mx, my, back))
				game.gameState = STATE.Menu;
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, MenuButton button) {
		if (mx > button.getX() && mx < button.getX() + button.getWidth())
			if (my > button.getY() && my < button.getY() + button.getHeight())
				return true;

		return false;
	}

	private boolean mouseOver(int mx, int my, MenuButtonPic button) {
		if (mx > button.getX() && mx < button.getX() + button.getWidth())
			if (my > button.getY() && my < button.getY() + button.getHeight())
				return true;

		return false;
	}

	public void tick() {

	}

	// Renders credit screen
	public void renderCredits(Graphics g) {
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, pos), "Credits", fnt);

		// My Credits
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, textSpace + (2 * pos)), "Programming and \"Art\" by:", fnt3);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, (2 * textSpace) + (2 * pos)), "Austin Campbell", fnt3);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, (3 * textSpace) + (2 * pos)), "Twitter.com/Redtail5144", fnt3);

		// Music Credits
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, (4 * textSpace) + (2 * pos)), "Music by: ", fnt3);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, (5 * textSpace) + (2 * pos)), "Jayden Rutschke", fnt3);

		// Website
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, (6 * textSpace) + (2 * pos)), "StudioWithAHat.com", fnt3);

		back.draw(g, fnt2);
	}

	// Renders the menu
	public void renderMenu(Graphics g) {
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, pos), "Menu", fnt);
		play.draw(g, fnt2);
		help.draw(g, fnt2);
		quit.draw(g, fnt2);

		// Speaker icon
		if (game.muted)
			soundM.draw(g);
		else
			sound.draw(g);

		credits.draw(g, fnt2);
	}

	// Renders help screen
	public void renderHelp(Graphics g) {
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, pos), "Help", fnt);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, textSpace + (2 * pos)), "Use Space to jump on the platforms.", fnt3);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, (2 * textSpace) + (2 * pos)), "You can ride on the inside of the platforms,",
				fnt3);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, (3 * textSpace) + (2 * pos)), "But cannot jump while doing so.", fnt3);
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, (4 * textSpace) + (2 * pos)), "Escape to Close.", fnt3);

		back.draw(g, fnt2);
	}

	// Renders gameover screen
	public void renderGameOver(Graphics g) {
		Helper.xCenterString(g, new Rectangle(Game.WIDTH, pos), "Rekt", fnt);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, textSpace + (2 * pos)), "HA! You got fucking rekt scrub!", fnt3);

		Helper.xCenterString(g, new Rectangle(Game.WIDTH, (2 * textSpace) + (2 * pos)), "Final Score: " + game.getHud().getScore(), fnt3);

		back.draw(g, fnt2);
	}

	// General render method
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

	// Syncs with game size
	public void setSize(int x, int y) {
		xSize = x / 4;
		ySize = y / 8;
		pos = y / 7;
		space = ySize * 2;
		textSize = game.WIDTH / 13;
		textSpace = space - (pos / 2);

		fnt = new Font("arial", 1, textSize);
		fnt2 = new Font("arial", 1, textSize - 20);
		fnt3 = new Font("arial", 1, textSize - 80);

		play = new MenuButton((game.WIDTH / 2) - (xSize / 2), game.WIDTH / 8, xSize, ySize, "Play");
		help = new MenuButton((game.WIDTH / 2) - (xSize / 2), (game.WIDTH / 8) + space, xSize, ySize, "Help");
		quit = new MenuButton((game.WIDTH / 2) - (xSize / 2), (game.WIDTH / 8) + (2 * space), xSize, ySize, "Quit");
		back = new MenuButton((game.WIDTH / 2) - (xSize / 2), (game.WIDTH / 8) + (2 * space), xSize, ySize, "Back");
		sound = new MenuButtonPic((game.WIDTH - (game.WIDTH / 13)) - 5, (game.HEIGHT - (game.WIDTH / 13)) - 5,
				game.WIDTH / 13, game.WIDTH / 13, speak);
		soundM = new MenuButtonPic(sound.getX(), sound.getY(), sound.getWidth(), sound.getHeight(), speakM);
		credits = new MenuButton(2, sound.getY(), xSize, ySize, "Credits");
	}
	
	//Gets menu text size
	public int getTextSize() {
		return textSize;
	}
}
