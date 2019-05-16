/*
 * This class contains
 * the game HUD
 */

package com.runner.main;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class HUD {

	private int xPos = 15, yPos = 15, score = 0;
	private static int hScore = 0;

	public HUD() {
		hScore = readHscore();
	}

	// The HUD ticks
	public void tick() {
		score++;
	}

	// Renders the HUD
	public void render(Graphics g) {
		g.drawString("Score: " + score, xPos, yPos);
		g.drawString("High Score: " + hScore, xPos, yPos + 10);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int i) {
		score = i;
	}

	public int getHscore() {
		return hScore;
	}

	public void setHscore(int i) {
		hScore = i;
	}

	public void saveHscore() {
		if (score > hScore)
			hScore = score;
	}

	// Saves High Score to file
	public static void writeHscore() {
		try {
			FileWriter fileWriter = new FileWriter("data.dat");
			fileWriter.write(String.valueOf(hScore));
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Reads high Score from file
	public static int readHscore() {
		// pass the path to the file as a parameter
		int r = 0;
		File file = new File("data.dat");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine())
				r = sc.nextInt();

			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return r;
	}

}
