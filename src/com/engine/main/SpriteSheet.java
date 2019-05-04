/*
 * This class handles the
 * Game sprite sheet
 */

package com.engine.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	// Sprite sheet image
	private BufferedImage sprite;

	public SpriteSheet(BufferedImage ss) {
		this.sprite = ss;
	}

	// Returns sprite located at Column and Row
	public BufferedImage grabImage(int col, int row, int width, int height) {
		// This math is for 32 pixel sprites
		return sprite.getSubimage((row * 32) - 32, (col * 32) - 32, width, height);
	}
}
