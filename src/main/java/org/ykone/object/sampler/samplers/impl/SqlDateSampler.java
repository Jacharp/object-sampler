package org.ykone.object.sampler.samplers.impl;

import java.sql.Date;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.samplers.ISampler;

public class SqlDateSampler implements ISampler<Date>{

	private static final SqlDateSampler INSTANCE = new SqlDateSampler();
	
	private SqlDateSampler(){
	}
	
	public static SqlDateSampler getInstance(){
		return INSTANCE;
	}
	
	@Override
	public Date generate(Class<?> classToSample, ISampleContext sampleContext) {
		return new Date(DateSampler.getInstance().generate(Date.class, sampleContext).getTime());
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return Date.class.equals(objectToSampleClass);
	}
}
