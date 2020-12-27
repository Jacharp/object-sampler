package org.ykone.object.sampler.samplers.impl;

import java.util.concurrent.ThreadLocalRandom;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.samplers.ISampler;

public class BooleanSampler implements ISampler<Boolean>{

	private static final BooleanSampler INSTANCE = new BooleanSampler();
	
	private BooleanSampler(){
	}
	
	public static BooleanSampler getInstance(){
		return INSTANCE;
	}
	
	@Override
	public Boolean generate(Class<?> classToSample, ISampleContext sampleContext) {
		return canGenerate(classToSample) ? ThreadLocalRandom.current().nextBoolean() : null;
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return Boolean.class.equals(objectToSampleClass) || boolean.class.equals(objectToSampleClass);
	}
}
