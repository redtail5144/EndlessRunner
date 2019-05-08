/*
 * This class contains all
 * the games titles
 */

package com.runner.main;

import java.util.LinkedList;
import java.util.Random;

public class Titles {

	// LinkedList of Titles
	private LinkedList<String> title = new LinkedList<String>();
	// Random number generator
	private Random r = new Random();

	public Titles() {
		title.add("Run Player Run!");
		title.add("Don't Stop Now!");
		title.add("So Many Colours!");
	}

	// Returns a random title
	public String getTitle() {
		return title.get(r.nextInt(title.size()));
	}
}
