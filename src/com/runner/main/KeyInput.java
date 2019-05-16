/*
 * This class handles
 * all key inputs
 */

package com.runner.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	// Game handler
	private Handler handler;
	// Array of keys that are being pressed
	private boolean[] keyDown = new boolean[4]; // 0=W,1=S,2=A,3=D

	// The game
	Game game;

	public KeyInput(Game game) {
		// Loads in game handler
		this.handler = game.getHand();
		this.game = game;

		// Initializes keys being pressed
		for (int i = 0; i < keyDown.length; i++)
			keyDown[i] = false;
	}

	// Happens when keys are being pressed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		// Closes the Game
		if (key == KeyEvent.VK_ESCAPE) {
			game.getHud().writeHscore();
			System.exit(1);
		}

		// Checks for player object
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			// Do movement shit if object is player
			if (tempObject.getID() == ID.Player) {

				switch (key) {
				// Space to jump
				case KeyEvent.VK_SPACE:
					tempObject.jump();
					break;
				}
			}
		}
	}

	// Happens when key is released
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player) {
				switch (key) {
				// Up Movement
				case KeyEvent.VK_W:
					keyDown[0] = false;
					break;

				// Down Movement
				case KeyEvent.VK_S:
					keyDown[1] = false;
					break;

				// Left Movement
				case KeyEvent.VK_A:
					keyDown[2] = false;
					break;

				// Right Movement
				case KeyEvent.VK_D:
					keyDown[3] = false;
					break;
				}

				// Vertical movement
				if (!keyDown[0] && !keyDown[1])
					tempObject.setVelY(0);
				// Horizontal movement
				if (!keyDown[2] && !keyDown[3])
					tempObject.setVelX(0);
			}
		}

	}
}
