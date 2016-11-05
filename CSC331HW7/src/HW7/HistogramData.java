package HW7;

import java.util.Arrays;

public class HistogramData {
	int[] reds;
	int[] greens;
	int[] blues;

	public HistogramData(int[] reds, int[] greens, int[] blues) {
		this.reds = reds;
		this.greens = greens;
		this.blues = blues;
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
		return getMostFrequent(reds);
	}

	public int getMostFrequentGreen() {
		return getMostFrequent(greens);
	}

	public int getMostFrequentBlue() {
		return getMostFrequent(blues);
	}

	// helper methods
	private int getMostFrequent(int[] ints){
		int[] sorted = ints;
		
		Arrays.sort(sorted);
		return sorted.length-1;
	}
	private int getAverage(int[] ints) {
		int sum = 0;

		for (int i : ints) {
			sum += i;
		}

		return sum / ints.length;
	}
}
