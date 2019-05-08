/*
 * This class contains
 * the player character
 */

package com.runner.main;

import java.awt.Color;

import com.runner.main.GameObject;

public class Player extends GameObject {

	// Jump height
	public static int jHeight = 50;

	public Player(int x, int y) {
		// GameObject constructor
		super(x, y);

		// Sets ID to player
		this.id = ID.Player;

		// Sets player size
		xSize = 32;
		ySize = xSize;

		velX = 0;
		velY = 5;

		// Loads player sprite
		// sprite = ss.grabImage(1, 1, xSize, ySize);
	}

	// Player ticks
	public void tick() {
		fade();

		if (!onGround()) {
			velY = 3;
			y += velY;
		}
	}

	// Player Jumps
	public void jump() {
		if (onGround() && !inside())
			y -= jHeight;
	}

	// Checks if player is on a platform
	private boolean onGround() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Platform)
				if (getBounds().intersects(tempObject.getBounds()))
					return true;
		}

		return false;
	}

	// Checks if player is inside platform
	private boolean inside() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Platform)
				if (getBounds().intersects(tempObject.getBounds()))
					if ((getY() + getYsize() > (tempObject.getY() + tempObject.getYsize())))
						return true;
		}
		return false;
	}
}
