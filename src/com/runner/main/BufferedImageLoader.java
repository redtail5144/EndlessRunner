/*	
 * This Class handles loading and
 * 	Buffered Images
 */

package com.runner.main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BufferedImageLoader {

	// Imaged to be loaded
	BufferedImage image;

	// Loads image located at path
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
