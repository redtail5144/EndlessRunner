/*
 * Abstract base class
 * for game objects
 */

package com.engine.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject {

	protected int x, y, // Coordinates
			xSize, ySize; // Size of object
	protected float velX, velY;
	protected ID id;
	protected Color colour;
	protected Handler handler;
	protected SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
	protected BufferedImage sprite = null;

	// constructor
	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
		// this.id = id;
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

	public abstract void tick();

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
