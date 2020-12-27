package org.ykone.object.sampler.samplers.impl;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class BigIntegerSampler extends RandomNumberGenerator<BigInteger> {

	private static final BigIntegerSampler INSTANCE = new BigIntegerSampler();

	private BigIntegerSampler(){
	}

	public static BigIntegerSampler getInstance() {
		return INSTANCE;
	}
	
	@Override
	protected BigInteger generateNumberBetween(int min, int max) {
		return BigInteger.valueOf(ThreadLocalRandom.current().nextLong());
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return BigInteger.class.equals(objectToSampleClass);
	}
}
