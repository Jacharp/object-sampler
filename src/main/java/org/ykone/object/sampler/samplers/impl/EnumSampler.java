package org.ykone.object.sampler.samplers.impl;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.samplers.ISampler;

public class EnumSampler implements ISampler<Enum<?>> {

	private static final EnumSampler INSTANCE = new EnumSampler();

	private EnumSampler(){
	}

	public static EnumSampler getInstance() {
		return INSTANCE;
	}
	
	@Override // we need the sampledClass for enum 
	public Enum<?> generate(Class<?> classToSample, ISampleContext sampleContext) {
		if(canGenerate(classToSample) ){
			int randomEnumOrdinal = IntegerSampler.getInstance().generateNumberBetween(0, classToSample.getEnumConstants().length);
			return (Enum<?>) classToSample.getEnumConstants()[randomEnumOrdinal];
		}
		return null;
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return objectToSampleClass.isEnum();
	}
}
