/*
 * Platform top
 */

package com.runner.main;

import java.awt.Color;
import java.util.Random;

import com.runner.main.Game;

public class Platform extends GameObject {

	// Random generator
	private Random ran = new Random();
	private boolean child = false, // Flag for creating next platform
			// Booleans RGB values for colours
			r = ran.nextBoolean(), b = ran.nextBoolean(), g = ran.nextBoolean();
	// RGB Values
	private int red = ran.nextInt(255), blue = ran.nextInt(255), green = ran.nextInt(255);

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
		handler.addObject(new PlatformPillar(x + (xSize / 2) - ((xSize / 4) / 2), y, this));
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

	// Fades particle though the spectrum
	public void fade() {

		// If r increase red value
		if (r) {
			red++;
			// If red is at max flag for decrease
			if (red >= 255)
				r = false;
		} else {
			// if !r decrease red value
			red--;
			// If Red is at min flag for increase
			if (red <= 0)
				r = true;
		}

		// If r increase red value
		if (g) {
			green++;
			// If red is at max flag for decrease
			if (green >= 255)
				g = false;
		} else {
			// if !r decrease red value
			green--;
			// If Red is at min flag for increase
			if (green <= 0)
				g = true;
		}
		// If r increase red value
		if (b) {
			blue++;
			// If red is at max flag for decrease
			if (blue >= 255)
				b = false;
		} else {
			// if !r decrease red value
			blue--;
			// If Red is at min flag for increase
			if (blue <= 0)
				b = true;
		}

		red = Helper.clamp(red, 0, 255);
		blue = Helper.clamp(blue, 0, 255);
		green = Helper.clamp(green, 0, 255);

		this.colour = new Color(red, green, blue);
	}

	// Empty cause platforms don't jump
	public void jump() {
		// TODO Auto-generated method stub

	}

}
