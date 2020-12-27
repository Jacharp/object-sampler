package org.ykone.object.sampler.samplers.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.samplers.ISampler;

public class DateSampler implements ISampler<Date>{

	private static final DateSampler INSTANCE = new DateSampler();
	
	private DateSampler(){
	}
	
	public static DateSampler getInstance(){
		return INSTANCE;
	}
	
	@Override
	public Date generate(Class<?> classToSample, ISampleContext sampleContext) {
		LocalDate localDate = LocalDateSampler.getInstance().generate(LocalDateSampler.class, sampleContext);
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return Date.class.equals(objectToSampleClass);
	}
}
