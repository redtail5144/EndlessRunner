/*
 * This class contains
 * the player character
 */

package com.runner.main;

import java.awt.Color;

import com.runner.main.GameObject;

public class Player extends GameObject {
	
	public static int jHeight = 20;

	public Player(int x, int y) {
		// GameObject constructor
		super(x, y);

		// Sets ID to player
		this.id = ID.Player;

		// Sets player colour
		colour = Color.green;

		// Sets player size
		xSize = 32;
		ySize = xSize;

		// Loads player sprite
		// sprite = ss.grabImage(1, 1, xSize, ySize);
	}

	// Player ticks
	public void tick() {
		//collided();
	}

	// checks if player collided with an object
	private void collided() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);

			// If player collides with something other than self
			if (temp != this)
				if (getBounds().intersects(temp.getBounds()))
					collision(temp);
		}
	}
	
	public void jump() {
		
	}

	// Collision with object
	private void collision(GameObject object) {
		switch (object.getID()) {
		default:
		}
	}

}
