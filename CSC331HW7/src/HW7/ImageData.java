package HW7;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageData {
	BufferedImage image;
	BufferedImage greyScaleImage;
	BufferedImage enhancedImage;

	ArrayList<PixelData> pixels;

	int avgRed = 0;
	int avgGreen = 0;
	int avgBlue = 0;

	int h;
	int w;

	public ImageData(BufferedImage img) {
		image = img;
		h = img.getHeight();
		w = img.getWidth();

		pixels = new ArrayList<PixelData>();

		getPixels();
	}

	private void getPixels() {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				// get RGB value
				int rgb = image.getRGB(x, y);

				// convert to color
				Color c = new Color(rgb);

				// add to RGB data object list
				pixels.add(new PixelData(c, x, y));
			}
		}
	}

	private void getPixelIntensity() {

	}

	public BufferedImage getOriginalImage() {
		return image;
	}

	public BufferedImage getGreyScaleImage() {
		if (greyScaleImage == null) {
			BufferedImage gsi = new BufferedImage(h, w, BufferedImage.TYPE_INT_RGB);

			for (PixelData pixel : pixels) {
				// TU-R 601-2 luma transform
				// L = R * 299/1000 + G * 587/1000 + B * 114/1000
				int r = (int)(pixel.r * (299/1000));
				int g = (int)(pixel.g * (587/1000));
				int b = (int)(pixel.b * (114/1000));
				
				int l = (r+g+b);
				
				gsi.setRGB(pixel.x, pixel.y, l);
			}
			
			greyScaleImage = gsi;
		}

		return greyScaleImage;
	}

	// Nested class. We don't care about this outside of ImageData but it makes
	// working with pixel data easier
	class PixelData {
		public int r;
		public int g;
		public int b;
		public int x;
		public int y;
		public int rgb;

		public PixelData(Color color, int xCoord, int yCoord) {
			rgb = color.getRGB();

			r = color.getRed();
			g = color.getGreen();
			b = color.getBlue();

			x = xCoord;
			y = yCoord;
		}
	}
}
