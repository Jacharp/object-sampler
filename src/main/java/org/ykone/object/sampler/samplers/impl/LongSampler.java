package org.ykone.object.sampler.samplers.impl;

import java.util.concurrent.ThreadLocalRandom;

public class LongSampler extends RandomNumberGenerator<Long> {

	private static final LongSampler INSTANCE = new LongSampler();

	private LongSampler(){
	}

	public static LongSampler getInstance() {
		return INSTANCE;
	}
	
	@Override
	protected Long generateNumberBetween(int min, int max) {
		return ThreadLocalRandom.current().nextLong(min,max);
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return Long.class.equals(objectToSampleClass) || long.class.equals(objectToSampleClass);
	}
}
