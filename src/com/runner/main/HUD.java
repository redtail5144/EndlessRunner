/*
 * This class contains
 * the game HUD
 */

package com.runner.main;

import java.awt.Graphics;

public class HUD {

	private int xPos = 15, yPos = 15, gVal = 255, score = 0;

	// The HUD ticks
	public void tick() {
		score++;
	}

	// Renders the HUD
	public void render(Graphics g) {
		g.drawString("Score: " + score, xPos, yPos);
	}

}
