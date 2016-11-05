package HW7;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageData {
	BufferedImage originalImage;
	BufferedImage greyScaleImage;
	BufferedImage enhancedImage;

	ArrayList<PixelData> pixels;

	int[] reds;
	int[] greens;
	int[] blues;

	int h;
	int w;

	public ImageData(BufferedImage img) {
		originalImage = img;
		h = img.getHeight();
		w = img.getWidth();

		pixels = new ArrayList<PixelData>();
		reds = new int[257];
		greens = new int[257];
		blues = new int[257];

		initPixels();
	}

	private void initPixels() {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				// get RGB value
				int rgb = originalImage.getRGB(x, y);

				// count occurrences of each color
				Color c = new Color(rgb);
				reds[c.getRed()] += 1;
				greens[c.getGreen()] += 1;
				blues[c.getBlue()] += 1;

				// add to pixel data object list
				pixels.add(new PixelData(rgb, x, y));
			}
		}
	}

	public ArrayList<PixelData> getPixels() {
		return pixels;
	}

	public BufferedImage getEnhancedImage() {
		if (enhancedImage == null) {

			BufferedImage ei = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

			for (PixelData px : pixels) {
				// TU-R 601-2 luma transform
				// L = R * 299/1000 + G * 587/1000 + B * 114/1000
				int l = px.r * 299 / 1000 + px.g * 587 / 1000 + px.b * 114 / 1000;

				// More readable than int p = (l<<16)|(l<<8)|l;
				int rgb = new Color(l, l, l).getRGB();

				// Set the greyscale pixel
				ei.setRGB(px.x, px.y, rgb);
			}

			enhancedImage = ei;
		}

		return enhancedImage;
	}

	public BufferedImage getOriginalImage() {
		return originalImage;
	}

	public BufferedImage getGreyScaleImage() {
		if (greyScaleImage == null) {

			BufferedImage gsi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

			for (PixelData px : pixels) {
				// TU-R 601-2 luma transform
				// L = R * 299/1000 + G * 587/1000 + B * 114/1000
				int l = px.r * 299 / 1000 + px.g * 587 / 1000 + px.b * 114 / 1000;

				// More readable than int p = (l<<16)|(l<<8)|l;
				int rgb = new Color(l, l, l).getRGB();

				// Set the greyscale pixel
				gsi.setRGB(px.x, px.y, rgb);
			}

			greyScaleImage = gsi;
		}

		return greyScaleImage;
	}

	public int getHeight() {
		return h;
	}

	public int getWidth() {
		return w;
	}
}
