package org.ykone.object.sampler.samplers.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.samplers.ISampler;

public class LocalDateTimeSampler  implements ISampler<LocalDateTime>{

	private static final LocalDateTimeSampler INSTANCE = new LocalDateTimeSampler();

	private LocalDateTimeSampler(){
	}

	public static LocalDateTimeSampler getInstance() {
		return INSTANCE;
	}

	@Override
	public LocalDateTime generate(Class<?> classToSample, ISampleContext sampleContext) {
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = LocalDateSampler.getInstance().generate(LocalDateSampler.class, sampleContext);
		return localDate.atTime(localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), localDateTime.getNano());
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return LocalDateTime.class.equals(objectToSampleClass);
	}
}
