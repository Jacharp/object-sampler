package org.ykone.object.sampler.samplers.impl;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.context.Range;
import org.ykone.object.sampler.samplers.ISampler;

public class CharacterSampler implements ISampler<Character> {

	private static final CharacterSampler INSTANCE = new CharacterSampler();
	
	private static final Range charRange = Range.of(65, 123);
	
	private CharacterSampler(){
	}
	
	public static CharacterSampler getInstance(){
		return INSTANCE;
	}
	
	@Override
	public Character generate(Class<?> classToSample,ISampleContext sampleContext) {
		return (char) IntegerSampler.getInstance().generateNumberBetween(charRange.getMin(), charRange.getMax()).intValue();
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return Character.class.equals(objectToSampleClass) || char.class.equals(objectToSampleClass);
	}
}
