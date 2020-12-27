package org.ykone.object.sampler.samplers.impl;

import java.util.concurrent.ThreadLocalRandom;

public class FloatSampler extends RandomNumberGenerator<Float> {

	private static final FloatSampler INSTANCE = new FloatSampler();

	private FloatSampler(){
	}

	public static FloatSampler getInstance() {
		return INSTANCE;
	}
	
	@Override
	protected Float generateNumberBetween(int min, int max) {
		return ThreadLocalRandom.current().nextFloat();
	}
	
	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return Float.class.equals(objectToSampleClass) || float.class.equals(objectToSampleClass);
	}
}
