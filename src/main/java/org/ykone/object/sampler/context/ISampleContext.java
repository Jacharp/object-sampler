package org.ykone.object.sampler.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.ykone.object.sampler.samplers.ISampler;

public interface ISampleContext {
	
	/**
	 * The length of the sampled string
	 * @return the length of string when sampling a string field
	 */
	default int getSampledStringSize(){
		return ThreadLocalRandom.current().nextInt(1,3);
	}
	
	/**
	 * 
	 * @return the size of the number to sample (for int, long, double, float)
	 */
	default Range getSampledNumberRange(){
		return Range.of(1, Short.MAX_VALUE); // max is exclusive
	}
	
	/**
	 * List of fields names to ignore during sampling
	 * @return
	 */
	default Set<String> getIgnoreFields(){
		return new HashSet<>();
	}
	
	/**
	 * List of customs to use during sampling
	 * If a custom sampler is defined it will be used instead of predefined sampler
	 */
	default List<ISampler<?>> getCustomSamplers(){
		return new ArrayList<>();
	}
	
	default DateGenerationStrategy getDateGenerationStrategy(){
		return DefaultDateGenerationStrategy.getInstance();
	}
	
	/**
	 * Specifies number of items to generate for the array or collection
	 * Put only positive value
	 * @return the size of items to generate in  array or collection(list, set), Map 
	 */
	default int getCollectionOrArraySize(){
		return ThreadLocalRandom.current().nextInt(2,5);
	}
	
	
	/**
	 * For each interface as a key, specified the implementation to use as sample
	 * This implementation must have default constructor
	 * @return the interface implementation to  use as value and as key the interface
	 */
	default Map<Class<?>, Class<?>> getInterfaceImplementationToUse(){
		 Map<Class<?>, Class<?>> defaultImplementationFor = new HashMap<>();
		 
		 defaultImplementationFor.put(List.class, ArrayList.class);
		 defaultImplementationFor.put(Set.class, HashSet.class);
		 defaultImplementationFor.put(Map.class, HashMap.class);
		 
		 return defaultImplementationFor;
	}
}
