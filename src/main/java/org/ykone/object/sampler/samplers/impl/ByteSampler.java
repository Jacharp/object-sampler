package org.ykone.object.sampler.samplers.impl;

import java.util.concurrent.ThreadLocalRandom;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.samplers.ISampler;

public class ByteSampler implements ISampler<Byte> {

	private static final ByteSampler INSTANCE = new ByteSampler();
	
	private ByteSampler(){
	}
	
	public static ByteSampler getInstance(){
		return INSTANCE;
	}
	
	@Override
	public Byte generate(Class<?> classToSample,ISampleContext sampleContext) {
		byte[] bytes = new byte[1];
		ThreadLocalRandom.current().nextBytes(bytes);
		return bytes[0];
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return Byte.class.equals(objectToSampleClass) || byte.class.equals(objectToSampleClass);
	}

}
