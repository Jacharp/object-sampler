package org.ykone.object.sampler.samplers.impl;

import java.util.concurrent.ThreadLocalRandom;

public class DoubleSampler extends RandomNumberGenerator<Double> {

	private static final DoubleSampler INSTANCE = new DoubleSampler();

	private DoubleSampler(){
	}

	public static DoubleSampler getInstance() {
		return INSTANCE;
	}

	@Override
	protected Double generateNumberBetween(int min, int max) {
		return ThreadLocalRandom.current().nextDouble(min, max);
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return Double.class.equals(objectToSampleClass) || double.class.equals(objectToSampleClass);
	}
}
