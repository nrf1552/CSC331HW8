package HW7;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
/**
 * 
 * @author nickf
 *
 * This displays the enhanced image. I admit that this isn't complete.
 *
 */
public class EnhancedImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int panelHeight;
	private int panelWidth;
	private BufferedImage img;

	public EnhancedImagePanel(ImageData imageData) {
		panelHeight = imageData.getHeight();
		panelWidth = imageData.getWidth();
		img = imageData.getOriginalImage();
		setPreferredSize(new Dimension(panelWidth, panelHeight));
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(0, 0, panelWidth, panelHeight);
		g2.drawImage(img, 0, 0, this);
	}
}
