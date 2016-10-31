package HW7;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * 
 * @author Nick Fields
 *
 */

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int panelHeight;
	private int panelWidth;
	private BufferedImage originalImage;
	private BufferedImage greyscaleImage;
	private BufferedImage displayedImage;
	private boolean isOriginal;

	public ImagePanel(BufferedImage bi) {
		panelHeight = bi.getHeight();
		panelWidth = bi.getWidth();
		
		originalImage = bi;
		greyscaleImage = getGreyScaleImage(originalImage);
		displayedImage = originalImage;
		isOriginal = true;

		setPreferredSize(new Dimension(panelWidth, panelHeight));
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				toggleImage();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(0, 0, panelWidth, panelHeight);
		g2.drawImage(displayedImage, 0, 0, this);
	}

	private BufferedImage getGreyScaleImage(BufferedImage img) {
		int h = img.getHeight();
		int w = img.getWidth();
		BufferedImage bi = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int color = img.getRGB(x, y);
				int l = calculateLuminance(color);

				bi.setRGB(x, y, l);
			}
		}

		return bi;
	}

	private int calculateLuminance(int rgbValue) {
		// TU-R 601-2 luma transform
		// L = R * 299/1000 + G * 587/1000 + B * 114/1000
		int r = (int)(rgbValue * .299);
		int g = (int)(rgbValue * .587);
		int b = (int)(rgbValue * .114);
		int l = (r + g + b);

		return l;
	}
	
	private void toggleImage(){
		isOriginal = !isOriginal;
		
		if(isOriginal){
			displayedImage = originalImage;
		} else {
			displayedImage = greyscaleImage;
		}
		
		repaint();
	}
}