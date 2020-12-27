package org.ykone.object.sampler.samplers.impl;

import java.util.ArrayList;
import java.util.List;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.exception.NoSamplerFoundException;
import org.ykone.object.sampler.samplers.ISampler;

public class SamplerFactory {

	// @SuppressWarnings("rawtypes") // TODO Should not be static must be linked to the context
	// private static Map<Class, ISampler> samplersStore = new HashMap<>();

	private static List<ISampler<?>> samplersStore = new ArrayList<>();

	static {
		register(BooleanSampler.getInstance(), ByteSampler.getInstance(), CharacterSampler.getInstance());
		register(DateSampler.getInstance(), DoubleSampler.getInstance(), FloatSampler.getInstance());
		register(IntegerSampler.getInstance(), LocalDateSampler.getInstance(), LocalDateTimeSampler.getInstance());
		register(LongSampler.getInstance(), StringSampler.getInstance(), EnumSampler.getInstance(), ShortSampler.getInstance());
		register(BigDecimalSampler.getInstance(), BigIntegerSampler.getInstance(), SqlDateSampler.getInstance());
		register(CalendarSampler.getInstance(), FileSampler.getInstance());
	}

	private SamplerFactory() {
	}

	public static <T> ISampler<T> getSampler(Class<T> classToSample, ISampleContext sampleContext) {

		// TODO optimize to avoid a loop: Put samplers in a Map where key = sampledClass and value = ISampler.
		ISampler<T> sampler = getSampler(classToSample, sampleContext.getCustomSamplers());// Check custom samplers first
		if (sampler == null) { // then Check built-in samplers
			sampler = getSampler(classToSample, samplersStore);
		}
		
		return sampler;
	}

	private static <T> ISampler<T> getSampler(Class<T> sampledClazz, List<ISampler<?>> samplers) {
		for (ISampler<?> sample : samplers) {
			if (sample.canGenerate(sampledClazz)) {
				return (ISampler<T>) sample;
			}
		}
		return null;
	}


	public static void register(ISampler<?>... samplers) {
		for (ISampler<?> sampler : samplers) {
			samplersStore.add(sampler);
		}
	}

	/**
	 * 
	 * @param classToSample : Class for which to create an instance with value 
	 * @param sampleContext : a context allowing to know how to sample
	 * @return create an instance for input class <code>classToSample</code> if the class is known among available samplers else throws <code>NoSamplerFoundException</code> 
	 * @throws NoSamplerFoundException
	 */
	public static <T> T generate(Class<T> classToSample, ISampleContext sampleContext) throws NoSamplerFoundException {
		ISampler<T> sampler = getSampler(classToSample, sampleContext);
		if (sampler == null) {
			throw new NoSamplerFoundException("There is no sampler defined for class " + classToSample);
		}
		return sampler.generate(classToSample, sampleContext);
	}
	
	public static <T> boolean canSample(Class<T> classToSample, ISampleContext sampleContext){
		return getSampler(classToSample, sampleContext) != null;
	}
}
