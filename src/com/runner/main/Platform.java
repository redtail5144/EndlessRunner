/*
 * Platform top
 */

package com.runner.main;

import java.awt.Color;

import com.runner.main.Game;

public class Platform extends GameObject {

	private boolean child = false; // Flag for creating next platform

	public Platform(int x, int y, float speed) {
		// GameObject constructor
		super(x, y);

		// Keeps platform on screen
		setY(Helper.clamp(y, 32, Game.HEIGHT - 100));

		// Sets ID to platform
		this.id = ID.Platform;

		// Sets platform size
		// Randomly determines platform xSize
		xSize = ran.nextInt(200);
		ySize = 16;

		// Keeps xSize within reasonable boundries
		xSize = Helper.clamp(xSize, 160, 200);

		// Each platform speeds up slightly
		velX = speed;

		// Spawns a pillar centred on platform
		handler.addObject(new PlatformPillar(x + (xSize / 2) - ((xSize / 4) / 2), y, this));
	}

	// First Platform
	public Platform(int x, int y, int size) {
		// GameObject constructor
		super(x, y);

		// Sets ID to platform
		this.id = ID.Platform;

		// Sets platform size
		xSize = size;
		ySize = 16;

		velX = 6;

		// Spawns a pillar centred on platform
		handler.addObject(new PlatformPillar(x + (xSize / 2) - ((xSize / 4) / 2), y, this));
	}

	public void tick() {
		// Fades through the spectrum
		fade();

		// Moves
		x -= velX;

		// If it has reached 30 px past edge of screen spawn new pillar
		if (x + xSize + 30 <= Game.WIDTH && !child) {
			child = true;
			handler.addObject(new Platform(Game.WIDTH, y + (Helper.coin() * (Player.jHeight / 2)), velX + 0.02f));
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
