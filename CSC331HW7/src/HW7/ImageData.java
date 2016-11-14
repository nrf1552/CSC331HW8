package HW7;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 * 
 * @author nickf
 *
 * Stores original, greyscale, and enhanced image data
 */

public class ImageData {
	BufferedImage originalImage;
	BufferedImage greyScaleImage;
	BufferedImage histogramEqualizedImage;
	BufferedImage sobelEdgeImage;
	
	public Histogram originalHistogram;
	public Histogram greyscaleHistogram;
	public Histogram enhancedHistogram;
	public Histogram sobelHistogram;

	ArrayList<PixelData> originalPixels;
	ArrayList<PixelData> greyscalePixels;
	ArrayList<PixelData> enhancedPixels;
	ArrayList<PixelData> sobelPixels;

	int h ;
	int w;
	
	String name;

	public ImageData(BufferedImage img) {
		originalImage = img;
		h = img.getHeight();
		w = img.getWidth();

		originalPixels = getPixelData(originalImage);
		originalHistogram = new Histogram(originalPixels);
	}

	private ArrayList<PixelData> getPixelData(BufferedImage img) {
		ArrayList<PixelData> data = new ArrayList<PixelData>();
		
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				// get RGB value
				int rgb = img.getRGB(x, y);

				// add to pixel data object list
				data.add(new PixelData(img, x, y));
			}
		}
		
		return data;
	}

	public BufferedImage getEnhancedImage() {
		if (histogramEqualizedImage == null) {

			BufferedImage ei = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

			for (PixelData px : originalPixels) {
				// TU-R 601-2 luma transform
				// L = R * 299/1000 + G * 587/1000 + B * 114/1000
				int l = px.r * 299 / 1000 + px.g * 587 / 1000 + px.b * 114 / 1000;

				// More readable than int p = (l<<16)|(l<<8)|l;
				int rgb = new Color(l, l, l).getRGB();

				// Set the greyscale pixel
				ei.setRGB(px.x, px.y, rgb);
			}

			histogramEqualizedImage = ei;
			enhancedPixels = getPixelData(histogramEqualizedImage);
			enhancedHistogram = new Histogram(enhancedPixels);
		}

		return histogramEqualizedImage;
	}
	
	public BufferedImage getSobelEnhancedImage() {
		if (sobelEdgeImage == null) {
			BufferedImage si = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			
			
			//todo: modify color from black/white to whatever is selected by user
			for (PixelData px : originalPixels) {
				//todo: modify 152 to be the threshold selected by user
				if(px.gradientEstimate > 152){
					si.setRGB(px.x, px.y, Color.black.getRGB());
				}
				else
				{
					si.setRGB(px.x, px.y, Color.white.getRGB());
				}
			}

			sobelEdgeImage = si;
			sobelPixels = getPixelData(sobelEdgeImage);
			sobelHistogram = new Histogram(sobelPixels);
		}

		return sobelEdgeImage;
	}

	public BufferedImage getOriginalImage() {
		return originalImage;
	}

	public BufferedImage getGreyScaleImage() {
		if (greyScaleImage == null) {

			BufferedImage gsi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

			for (PixelData px : originalPixels) {
				// TU-R 601-2 luma transform
				// L = R * 299/1000 + G * 587/1000 + B * 114/1000
				int l = px.r * 299 / 1000 + px.g * 587 / 1000 + px.b * 114 / 1000;

				// More readable than int p = (l<<16)|(l<<8)|l;
				int rgb = new Color(l, l, l).getRGB();

				// Set the greyscale pixel
				gsi.setRGB(px.x, px.y, rgb);
			}

			greyScaleImage = gsi;
			greyscalePixels = getPixelData(greyScaleImage);
			greyscaleHistogram = new Histogram(greyscalePixels);
		}

		return greyScaleImage;
	}

	public int getHeight() {
		return h;
	}

	public int getWidth() {
		return w;
	}
	
	public String getName(){
		return name;
	}
}