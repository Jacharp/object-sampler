package org.ykone.object.sampler.samplers.impl;

import java.time.LocalDateTime;
import java.util.Calendar;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.samplers.ISampler;

public class CalendarSampler implements ISampler<Calendar>{

	private static final CalendarSampler INSTANCE = new CalendarSampler();
	
	private CalendarSampler(){
	}
	
	public static CalendarSampler getInstance(){
		return INSTANCE;
	}
	
	@Override
	public Calendar generate(Class<?> classToSample, ISampleContext sampleContext) {
		LocalDateTime localDateTime = LocalDateTimeSampler.getInstance().generate(LocalDateSampler.class, sampleContext);
		Calendar generatedCalendar = Calendar.getInstance();
		generatedCalendar.set(localDateTime.getYear(), localDateTime.getMonth().getValue(),
				localDateTime.getDayOfYear(),localDateTime.getHour() , localDateTime.getMinute(), 
				localDateTime.getSecond());
		return generatedCalendar;
	}

	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return Calendar.class.equals(objectToSampleClass);
	}
}
