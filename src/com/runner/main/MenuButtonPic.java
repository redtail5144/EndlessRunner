package com.runner.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MenuButtonPic {

	// Pos and size of button
	private int x, y, width, height;

	// Image of buttton
	private BufferedImage pic;

	public MenuButtonPic(int x, int y, int width, int height, BufferedImage pic) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.pic = pic;
		scaleImage();
	}

	public void draw(Graphics g) {
		g.drawImage(pic, x, y, null);
		g.drawRect(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void scaleImage() {
		// Scaled image
		BufferedImage scaled = new BufferedImage(width, height, pic.getType());

		// Scales the input image to the output image
		Graphics2D g2d = scaled.createGraphics();
		g2d.drawImage(pic, 0, 0, width, height, null);
		g2d.dispose();

		pic = scaled;
	}

}
