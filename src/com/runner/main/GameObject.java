/*
 * Abstract base class
 * for game objects
 */

package com.runner.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class GameObject {

	protected int x, y, // Coordinates
			xSize, ySize; // Size of object
	protected float velX, velY;
	protected ID id;
	protected Color colour;
	protected Handler handler;
	protected SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
	protected BufferedImage sprite = null;
	protected Random ran = new Random();
	// Booleans RGB values for colours
	private boolean r = ran.nextBoolean(), b = ran.nextBoolean(), g = ran.nextBoolean();
	// RGB Values
	private int red = ran.nextInt(255), blue = ran.nextInt(255), green = ran.nextInt(255);

	// constructor
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
		this.handler = Game.getHand();
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, xSize, ySize);
	}

	public void render(Graphics g) {
		if (sprite == null) {
			g.setColor(colour);
			g.fillRect(x, y, xSize, ySize);
		} else
			g.drawImage(sprite, x, y, null);

		/*
		 * Shows hit boxes Graphics2D g2d = (Graphics2D) g; g.setColor(Color.yellow);
		 * g2d.draw(getBounds());
		 */
	}

	// Fades colour though the spectrum
	public void fade() {

		// If r increase red value
		if (r) {
			red += 2;
			// If red is at max flag for decrease
			if (red >= 255)
				r = false;
		} else {
			// if !r decrease red value
			red -= 2;
			// If Red is at min flag for increase
			if (red <= 0)
				r = true;
		}

		// If r increase red value
		if (g) {
			green += 2;
			// If red is at max flag for decrease
			if (green >= 255)
				g = false;
		} else {
			// if !r decrease red value
			green -= 2;
			// If Red is at min flag for increase
			if (green <= 0)
				g = true;
		}
		// If r increase red value
		if (b) {
			blue += 2;
			// If red is at max flag for decrease
			if (blue >= 255)
				b = false;
		} else {
			// if !r decrease red value
			blue -= 2;
			// If Red is at min flag for increase
			if (blue <= 0)
				b = true;
		}

		red = Helper.clamp(red, 0, 255);
		blue = Helper.clamp(blue, 0, 255);
		green = Helper.clamp(green, 0, 255);

		this.colour = new Color(red, green, blue);
	}

	public abstract void tick();

	public abstract void jump();

	// *********SETTERS************************
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setVelX(float x) {
		this.velX = x;
	}

	public void setVelY(float y) {
		this.velY = y;
	}

	public void setID(ID id) {
		this.id = id;
	}

	// ***************GETTERS********************
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public ID getID() {
		return this.id;
	}

	public float getVelX() {
		return this.velX;
	}

	public float getVelY() {
		return this.velY;
	}

	public int getXsize() {
		return this.xSize;
	}

	public int getYsize() {
		return this.ySize;
	}

}
