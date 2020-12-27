package org.ykone.object.sampler.samplers.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.samplers.ISampler;

public class LocalDateSampler implements ISampler<LocalDate> {

	private static final LocalDateSampler INSTANCE = new LocalDateSampler();
	
	private LocalDateSampler(){
	}
	
	public static LocalDateSampler getInstance(){
		return INSTANCE;
	}
	
	@Override
	public LocalDate generate(Class<?> classToSample, ISampleContext sampleContext) {
		int daysToAdd = sampleContext.getDateGenerationStrategy().getDaysToAdd();
		LocalDate computedDate = LocalDate.now();
		if(daysToAdd != 0){
			computedDate = computedDate.plusDays(daysToAdd);
		}
		// Check now if computed date is a weekend and weekend must be ignored
		boolean shouldIgnoreWeekend = sampleContext.getDateGenerationStrategy().shouldIgnoreWeekend() ; 
		if(shouldIgnoreWeekend && isWeekend(computedDate)){
			daysToAdd = daysToAdd <0 ? -2 : +2; // 2 in order to by pass weekend day if for example computed day is a Saturday
			computedDate = computedDate.plusDays(daysToAdd);
		}
		return computedDate;
	}
	
	private boolean isWeekend(LocalDate localDate){
		return (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY);
	}
	
	@Override
	public boolean canGenerate(Class<?> objectToSampleClass) {
		return LocalDate.class.equals(objectToSampleClass);
	}
}
