package HW8;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author nickf
 *
 * Gets name, mode, average and most-frequent intensity
 *
 */
public class HistogramPanel extends JPanel {

	static final long serialVersionUID = 1L;

	int panelHeight;
	int panelWidth;

	ImageData data;


	public HistogramPanel(ImageData imageData) {
		data = imageData;
		panelHeight = data.getHeight();
		panelWidth = Main.DEFAULTHISTOGRAMWIDTH;
		setLayout(new GridLayout(8,2));
		setPreferredSize(new Dimension(panelWidth, panelHeight));
		
		add(new JLabel("Name: ", SwingConstants.RIGHT));
		add(new JLabel("NAME"));
		
		add(new JLabel("Mode: ", SwingConstants.RIGHT));
		add(new JLabel("RGB"));
		
		add(new JLabel("Average Intensity - Red:", SwingConstants.RIGHT));
		add(new JLabel(Integer.toString(data.originalHistogram.getAverageRed())));
		
		add(new JLabel("Average Intensity - Green:", SwingConstants.RIGHT));
		add(new JLabel(Integer.toString(data.originalHistogram.getAverageGreen())));
		
		add(new JLabel("Average Intensity - Blue:", SwingConstants.RIGHT));
		add(new JLabel(Integer.toString(data.originalHistogram.getAverageBlue())));
		
		add(new JLabel("Most Frequent Intensity - Red: ", SwingConstants.RIGHT));
		add(new JLabel(Integer.toString(data.originalHistogram.getMostFrequentRed())));
		
		add(new JLabel("Most Frequent Intensity - Green: ", SwingConstants.RIGHT));
		add(new JLabel(Integer.toString(data.originalHistogram.getMostFrequentGreen())));
		
		add(new JLabel("Most Frequent Intensity - Blue: ", SwingConstants.RIGHT));
		add(new JLabel(Integer.toString(data.originalHistogram.getMostFrequentBlue())));
	}

	public void paintComponent(Graphics g) {

	}
}
