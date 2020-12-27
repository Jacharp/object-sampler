package org.ykone.object.sampler.samplers;

import org.ykone.object.sampler.context.ISampleContext;

public interface ISampler <T> {
	
   //  We specially need the parameter classToSample for {EnumSampler}. 
	// TODO : This method should be improved, maybe add it to the class to sample to sampleContext 
	// Or maybe remove canGenerate method and just call generate method which should do a check before generation a value.
	// Else it returns Optional.ofEmpty if it cannot not generated
   /*
    * Generate a value for a given class according to context
    */
	T generate(Class<?> classToSample, ISampleContext sampleContext);
	
	/**
	 * Indicates if this sampler can generate a value for a given class
	 * @param objectToSampleClass : Class for which to generate a sample
	 * @return true if this sampler can generate a value for a given class else false
	 * 
	 */
	boolean canGenerate(Class<?> objectToSampleClass); 
}
