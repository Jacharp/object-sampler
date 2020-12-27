package org.ykone.object.sampler.samplers.impl;

import java.util.concurrent.ThreadLocalRandom;

public class IntegerSampler  extends RandomNumberGenerator<Integer>{

	private static final IntegerSampler INSTANCE = new IntegerSampler();

	private IntegerSampler(){
	}

	public static IntegerSampler getInstance() {
		return INSTANCE;
	}
	
	@Override
	public Integer generateNumberBetween(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min,max);
	}
	
	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return Integer.class.equals(objectToSampleClass) || int.class.equals(objectToSampleClass);
	}
	
	
}
