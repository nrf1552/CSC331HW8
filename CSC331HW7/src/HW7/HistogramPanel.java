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

	private static final long serialVersionUID = 1L;
	private int panelHeight;
	private int panelWidth;
	private BufferedImage img;
	
	private List<Integer> reds;
	private List<Integer> greens;
	private List<Integer> blues;
	
	int avgRed = 0;
	int avgGreen = 0;
	int avgBlue = 0;

	public HistogramPanel(BufferedImage bi) {
		panelHeight = bi.getHeight();
		panelWidth = Main.DEFAULTHISTOGRAMWIDTH;
		img = bi;
		
		reds = new ArrayList<Integer>();
		greens = new ArrayList<Integer>();
		blues = new ArrayList<Integer>();
		
		for (int y = 0; y < panelHeight; y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				int rgb = img.getRGB(x, y);
				Color c = new Color(rgb);
				
				reds.add(c.getRed());
				greens.add(c.getGreen());
				blues.add(c.getBlue());
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
	
	private void setAvgPixelIntensity(){		
		for (int i:reds){
			avgRed+=i;
		}
		for (int i:greens){
			avgGreen+=i;
		}
		for (int i:blues){
			avgBlue+=i;
		}
		
		avgRed /= reds.size();
		avgGreen /= greens.size();
		avgBlue /= blues.size();
	}
	
	private void  getImageName(){
		
	}
	
	private void getImageMode(){
		
	}
	
	private void getMostFrequentPixelIntensity(){
		
	}
	
	private void getSumOfPixelCountInQuarter(){
		
	}
}
