package org.ykone.object.sampler.samplers.impl;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.samplers.ISampler;

public class StringSampler implements ISampler<String> {

	private static final StringSampler INSTANCE = new StringSampler();

	private StringSampler(){
	}

	public static StringSampler getInstance() {
		return INSTANCE;
	}
	
	@Override
	public String generate(Class<?> classToSample, ISampleContext sampleContext) {
		int stringLength = sampleContext.getSampledStringSize();
		StringBuilder stringBuilder = new StringBuilder(stringLength);
		
		for (int i=0; i<stringLength; i++){
			char randchar = CharacterSampler.getInstance().generate(Character.class, sampleContext);
			stringBuilder.append(randchar);
		}
		return stringBuilder.toString();
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return String.class.equals(objectToSampleClass);
	}
}
