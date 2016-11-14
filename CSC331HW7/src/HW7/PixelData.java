package HW7;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * 
 * @author nickf
 *
 *         Nobody knows/cares about this class outside of the ImageData class
 */
public class PixelData {
	public Color color;
	public int r;
	public int g;
	public int b;
	public int x;
	public int y;
	public int rgb;
	public int averageIntensity;
	public int gradientEstimate;
	public Border border;

	BufferedImage img;

	public PixelData(BufferedImage image, int xCoord, int yCoord) {
		img = image;

		int rgb = img.getRGB(xCoord, yCoord);

		color = new Color(rgb);

		this.rgb = rgb;

		r = color.getRed();
		g = color.getGreen();
		b = color.getBlue();

		x = xCoord;
		y = yCoord;

		averageIntensity = (r + g + b) / 3;

		border = new Border(img, x, y);
		gradientEstimate = calculateGradientEstimate(border);
	}
	
	public int calculateGradientEstimate(Border border){
		int i = 0;
		
		int yGradient = 
				 (border.topLeft.getRed() *  1)
				+(border.topCenter.getRed() * 2)
				+(border.topRight.getRed() * 1)
				+(border.midLeft.getRed() *  0)
				+(border.midCenter.getRed() * 0)
				+(border.midRight.getRed() * 0)
				+(border.bottomLeft.getRed() *  -1)
				+(border.bottomCenter.getRed() * -2)
				+(border.bottomRight.getRed() * -1);

		int xGradient = 
				 (border.topLeft.getRed() *  1)
				+(border.topCenter.getRed() * 0)
				+(border.topRight.getRed() * -1)
				+(border.midLeft.getRed() *  2)
				+(border.midCenter.getRed() * 0)
				+(border.midRight.getRed() * -2)
				+(border.bottomLeft.getRed() *  1)
				+(border.bottomCenter.getRed() * 0)
				+(border.bottomRight.getRed() * -1);
		
		int gradientThreshold = (xGradient + yGradient)/8;
		
		return i;
	}

	public class Border {

		public Color topLeft;
		public Color topCenter;
		public Color topRight;
		public Color midLeft;
		public Color midCenter;
		public Color midRight;
		public Color bottomLeft;
		public Color bottomCenter;
		public Color bottomRight;

		public Border(BufferedImage img, int x, int y) {
			if (x == 0) {
				x += 2;
			}
			if (y == 0) {
				y += 2;
			}
			if (x == img.getWidth()) {
				x -= 2;
			}
			if (y == img.getHeight()) {
				y -= 2;
			}

			topLeft = new Color(img.getRGB(x - 1, y - 1));
			topCenter = new Color(img.getRGB(x, y - 1));
			topRight = new Color(img.getRGB(x + 1, y - 1));

			midLeft = new Color(img.getRGB(x - 1, y));
			midCenter = new Color(img.getRGB(x, y));
			midRight = new Color(img.getRGB(x + 1, y));

			bottomLeft = new Color(img.getRGB(x - 1, y + 1));
			bottomCenter = new Color(img.getRGB(x, y + 1));
			bottomRight = new Color(img.getRGB(x + 1, y + 1));
		}
	}
}
