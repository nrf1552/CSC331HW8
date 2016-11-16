package HW8;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author nickf
 *
 * Counts each occurrence of RGB pixels, calculates average intensity, and finally gets average and most frequent pixels
 */
public class Histogram {
	public int[] reds;
	public int[] greens;
	public int[] blues;
	
	int averageIntensity;
	
	ArrayList<PixelData> pixels;

	public Histogram(ArrayList<PixelData> pixels) {
		int sum = 0;
		
		this.pixels = pixels;
		
		reds = new int[256];
		greens = new int[256];
		blues = new int[256];

		for (PixelData p : pixels) {
			reds[p.r] += 1;
			greens[p.g] += 1;
			blues[p.b] += 1;
			
			sum += p.averageIntensity;
		}
		
		averageIntensity = sum/pixels.size();
	}

	public int getAverageRed() {
		return getAverage(reds);
	}

	public int getAverageGreen() {
		return getAverage(greens);
	}

	public int getAverageBlue() {
		return getAverage(blues);
	}

	public int getMostFrequentRed() {
		return pixels.get(getMostFrequentIndex(reds)).r;
	}

	public int getMostFrequentGreen() {
		return pixels.get(getMostFrequentIndex(greens)).g;
	}

	public int getMostFrequentBlue() {
		return pixels.get(getMostFrequentIndex(blues)).b;
	}
	
	public int getAveragePixelIntensity(){
		return averageIntensity;
	}

	// helper methods
 	private int getMostFrequentIndex(int[] ints) {
		int count = 0;
		int mostFrequent = 0;
		int tempCount = 0;

		for (int i : ints) {
			tempCount = 0;

			for (int t : ints) {
				if (i == t) {
					tempCount += 1;
				}
			}

			if (tempCount > count) {
				mostFrequent = i;
				count = tempCount;
			}
		}

		return mostFrequent;
	}

	private int getAverage(int[] ints) {
		int sum = 0;

		for (int i : ints) {
			sum += i;
		}

		return sum / ints.length;
	}
}
