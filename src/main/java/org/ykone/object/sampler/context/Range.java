package org.ykone.object.sampler.context;

/**
 * Range of number to generate: The max is exclusive
 *
 */
public class Range {
	
	private final int min;
	private final int max;
	
	public static Range of(int min, int max){
		return new Range(min, max);
	}
	
	public Range(int min, int max) {
		if(min<0 ){
			throw new IllegalArgumentException(String.format("Min value %d must be positive",min));
		}
		if(max<=min){
			throw new IllegalArgumentException(String.format("Max value %d must be greater than min %d", max, min));
		}
		this.min = min;
		this.max = max;
	}
	
	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
}
