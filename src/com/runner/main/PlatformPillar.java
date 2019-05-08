/*
 * Pillar for platforms
 */

package com.runner.main;

public class PlatformPillar extends GameObject {

	// Pillar top
	private Platform top;

	public PlatformPillar(int x, int y, Platform p) {
		// GameObject constructor
		super(x, y);

		this.id = ID.Pillar;

		// Sets top as p
		top = p;

		// Sets colour to same as top
		colour = top.colour;

		// xSize is 1/4 of top
		xSize = top.xSize / 4;
		// ySize is equal to distance between top
		// and bottom of screen
		ySize = Game.HEIGHT - top.y;

		// vel is same as top
		velX = top.velX;
	}

	public void tick() {
		// moves
		x -= velX;

		// vel is same as top
		velX = top.velX;

		// Updates colour to same as top
		colour = top.colour;

		// Breaks without this
		y = top.y; // Don't know why

		// Deletes self once off screen
		if (x + xSize < 0)
			handler.removeObject(this);
	}

	// Empty cause pillars don't jump
	public void jump() {
	}

}
