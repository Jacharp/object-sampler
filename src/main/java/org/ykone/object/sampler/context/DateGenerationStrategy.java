package org.ykone.object.sampler.context;

public interface DateGenerationStrategy {
	
	/**
	 * < 0 indicates that the sampled date will be in the past (I.e sustract daysToAdd to today) 
	 * = 0 indicates that the sampling date must be Today
	 * > 0 indicates that the sampled date will be in the future (I.e today + daysToAdd) 
	 * Number of days used to compute the date 
	 */
	default public int getDaysToAdd(){
		return 0;
	}
	
	/**
	 * Indicates if a weekend should be ignored  or not if the computed date is a wekeend
	 * returns true if weekend shoulbe ignored else false
	 */
	default public boolean shouldIgnoreWeekend(){
		return false;
	}
}
