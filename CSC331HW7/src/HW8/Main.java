package HW8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

/**
 * 
 * @author nickf
 *
 * Creates the main frame for viewing and runs the program
 */
public class Main {

	public static Integer DEFAULTIMAGEHEIGHT = 900;
	public static Integer DEFAULTIMAGEWIDTH = 600;
	public static Integer DEFAULTHISTOGRAMWIDTH = 400;

	public Integer selectedNumber;
	public Integer selectedNumberOfPanels;
	public Boolean isAddSubtract;
	
	public ArrayList<ImageData> images;

	JFrame frame;
	ImageData img;
	JPanel panelContainer;

	ImagePanel imagePanel;
	HistogramPanel histogramPanel;
	EnhancedImagePanel enchancedImagePanel;

	public Main() {
		// Initialize Images list
		images = new ArrayList<ImageData>();
		
		// Instantiate JFrame
		frame = new JFrame("Homework 7");

		// Set initial properties
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		// Add menu
		frame.setJMenuBar(new ViewerMenu().menu(this));

		// Add container for all image components
		panelContainer = new JPanel(new BorderLayout(10,10));
		refreshImageContainer(img);
		frame.add(panelContainer, BorderLayout.CENTER);

		// Show it
		frame.setVisible(true);
		frame.pack();
	}

	public void changeImage(BufferedImage image) {
		img = new ImageData(image);

		refreshImageContainer(img);
	}

	private void refreshImageContainer(ImageData imageData) {
		// if image is null/not yet initialized, set to default size
		if (imageData == null) {
			int w = (DEFAULTIMAGEWIDTH * 2) + DEFAULTHISTOGRAMWIDTH;
			
			panelContainer.setPreferredSize(
					new Dimension(w, DEFAULTIMAGEHEIGHT));
		} else {
			panelContainer.removeAll();

			imagePanel = new ImagePanel(img);
			histogramPanel = new HistogramPanel(img);
			enchancedImagePanel = new EnhancedImagePanel(img);

			panelContainer.add(imagePanel, BorderLayout.WEST);
			panelContainer.add(histogramPanel, BorderLayout.CENTER);
			panelContainer.add(enchancedImagePanel, BorderLayout.EAST);

			panelContainer.setPreferredSize(new Dimension((imageData.getWidth() * 2) + DEFAULTHISTOGRAMWIDTH, imageData.getHeight()));

			frame.pack();
			panelContainer.revalidate();
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}
}