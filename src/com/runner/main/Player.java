/*
 * This class contains
 * the player character
 */

package com.runner.main;

import com.runner.main.GameObject;

public class Player extends GameObject {

	// Jump height
	public static int jHeight = 50;

	// flag for sprite animation
	private boolean first = true;

	// ints for animations
	private final int tMax = 4;
	private int timer = tMax;

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

		sprite = ss.grabImage(1, 1, xSize, ySize);

		// Loads player sprite
		// sprite = ss.grabImage(1, 1, xSize, ySize);
	}

	// Player ticks
	public void tick() {
		fade();

		if (timer >= 0)
			timer--;

		animate();

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

	// Updates player sprite
	private void animate() {
		if (!onGround() || inside()) {
			sprite = ss.grabImage(2, 1, xSize, ySize);
			timer = 0;
		}
		else if (timer <= 0)
			if (first) {
				sprite = ss.grabImage(1, 2, xSize, ySize);
				timer = tMax;
				first = false;
			} else {
				sprite = ss.grabImage(1, 1, xSize, ySize);
				timer = tMax;
				first = true;
			}
	}
}
