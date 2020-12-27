package org.ykone.object.sampler.samplers.impl;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class BigDecimalSampler extends RandomNumberGenerator<BigDecimal> {

	private static final BigDecimalSampler INSTANCE = new BigDecimalSampler();

	private BigDecimalSampler(){
	}

	public static BigDecimalSampler getInstance() {
		return INSTANCE;
	}
	
	@Override
	protected BigDecimal generateNumberBetween(int min, int max) {
		return BigDecimal.valueOf(ThreadLocalRandom.current().nextFloat());
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return BigDecimal.class.equals(objectToSampleClass);
	}
}
