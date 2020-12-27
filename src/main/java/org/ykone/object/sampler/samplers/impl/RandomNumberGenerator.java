package org.ykone.object.sampler.samplers.impl;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.samplers.ISampler;

/**
 * This class is the entry point to generate all number i.E all class extending Number class
 *
 * @param <T>
 */
public abstract class RandomNumberGenerator<T> implements ISampler<T>{
	
	@Override
	public T generate(Class<?> classToSample, ISampleContext sampleContext){
		int min = sampleContext.getSampledNumberRange().getMin();
		int max = sampleContext.getSampledNumberRange().getMax(); 
		return generateNumberBetween(min,max);
	}
	
	protected abstract T generateNumberBetween(int min, int max);
	
	/*@Override // TODO :must be the unique entry point  to handle all numbers generation
	public boolean canSample(Class<?> objectToSampleClass) {
		return Number.class.isAssignableFrom(objectToSampleClass);
	}*/
}
