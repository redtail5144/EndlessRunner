/*
 * This class "handles"
 * all game objects
 */

package com.runner.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	// Linked list of all game objects
	LinkedList<GameObject> object = new LinkedList<GameObject>();

	// Goes through each game objects and makes them tick
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}

	// Renders each game object
	public void render(Graphics g) {

		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	// Adds object to the list
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	// Removes object from the list
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	// Clears the list of everything but the player
	public void clearEnemys() {
		for (int i = 0; i < object.size(); i++)
			if (object.get(i).getID() != ID.Player) {
				removeObject(object.get(i));
				i--;
			}
	}
}
