package HW7;

import java.awt.Color;
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

	public ImagePanel(ImageData imageData) {
		panelHeight = imageData.getHeight();
		panelWidth = imageData.getWidth();

		originalImage = imageData.getOriginalImage();
		greyscaleImage = imageData.getGreyScaleImage();
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

	private void toggleImage() {
		isOriginal = !isOriginal;

		if (isOriginal) {
			displayedImage = originalImage;
		} else {
			displayedImage = greyscaleImage;
		}

		repaint();
	}
}