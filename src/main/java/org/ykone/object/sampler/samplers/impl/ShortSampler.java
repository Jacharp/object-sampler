package org.ykone.object.sampler.samplers.impl;

import java.util.concurrent.ThreadLocalRandom;

public class ShortSampler extends RandomNumberGenerator<Short> {

	private static final ShortSampler INSTANCE = new ShortSampler();

	private ShortSampler(){
	}

	public static ShortSampler getInstance() {
		return INSTANCE;
	}
	
	@Override
	protected Short generateNumberBetween(int min, int max) {
		min= min > Short.MIN_VALUE ? Short.MIN_VALUE : min; 
		max= max > Short.MAX_VALUE ? Short.MAX_VALUE : max; 
		return (short)ThreadLocalRandom.current().nextInt(min,max);
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return Short.class.equals(objectToSampleClass) || short.class.equals(objectToSampleClass);
	}
	
}
