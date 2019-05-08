/*
 * Platform top
 */

package com.runner.main;

import java.awt.Color;

import com.runner.main.Game;

public class Platform extends GameObject {

	private boolean child = false; // Flag for creating next platform
		

	public Platform(int x, int y) {
		// GameObject constructor
		super(x, y);

		// Keeps platform on screen
		y = Helper.clamp(y, Game.WIDTH - 10, 32);

		// Sets ID to platform
		this.id = ID.Platform;

		// Sets platform colour
		colour = Color.white;

		// Sets platform size
		// Randomly determines platform size
		xSize = ran.nextInt(200);
		ySize = 16;

		// Keeps xSize within reasonable boundries
		xSize = Helper.clamp(xSize, 64, 200);

		velX = 3;

		// Spawns a pillar centred on platform
		//handler.addObject(new PlatformPillar(x + (xSize / 2) - ((xSize / 4) / 2), y, this));
	}

	public void tick() {
		// Fades through the spectrum
		fade();

		// Moves
		x -= velX;

		// If it has reached 20 px past edge of screen spawn new pillar
		if (x + xSize + 20 <= Game.WIDTH && !child) {
			child = true;
			handler.addObject(new Platform(Game.WIDTH, y + (Helper.coin() * Player.jHeight)));
		}

		// Dies once reaches edge of screen
		if (x + xSize < 0)
			handler.removeObject(this);
	}

	// Empty cause platforms don't jump
	public void jump() {
		// TODO Auto-generated method stub

	}

}
