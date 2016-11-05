package HW7;

import java.awt.Color;

public class PixelData {
	public Color color;
	public int r;
	public int g;
	public int b;
	public int x;
	public int y;
	public int rgb;

	public PixelData(int rgb, int xCoord, int yCoord) {
		color = new Color(rgb);

		this.rgb = rgb;

		r = color.getRed();
		g = color.getGreen();
		b = color.getBlue();

		x = xCoord;
		y = yCoord;
	}
}
