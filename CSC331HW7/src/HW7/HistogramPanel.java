package HW7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

import javax.swing.JPanel;

public class HistogramPanel extends JPanel {

	static final long serialVersionUID = 1L;

	int panelHeight;
	int panelWidth;

	BufferedImage img;
	ImageData data;

	int[] reds;
	int[] greens;
	int[] blues;

	int avgRed = 0;
	int avgGreen = 0;
	int avgBlue = 0;

	public HistogramPanel(ImageData imageData) {
		data = imageData;
		panelHeight = data.getHeight();
		panelWidth = Main.DEFAULTHISTOGRAMWIDTH;
		img = data.getOriginalImage();

		reds = new int[257];
		greens = new int[257];
		blues = new int[257];

		for (int y = 0; y < panelHeight; y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				int rgb = img.getRGB(x, y);
				Color c = new Color(rgb);
			}
		}

		setAvgPixelIntensity();

		setPreferredSize(new Dimension(panelWidth, panelHeight));
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(0, 0, panelWidth, panelHeight);
		// g2.drawImage(img, 0, 0, this);
	}

	private void setAvgPixelIntensity() {
		ArrayList<PixelData> pixels = data.getPixels();
	}

	private void getImageName() {

	}

	private void getImageMode() {

	}

	private void getMostFrequentPixelIntensity() {

	}

	private void getSumOfPixelCountInQuarter() {

	}
}
