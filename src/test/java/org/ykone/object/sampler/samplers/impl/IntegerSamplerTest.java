package org.ykone.object.sampler.samplers.impl;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.context.Range;

public class IntegerSamplerTest {
	
	private IntegerSampler integerSampler = IntegerSampler.getInstance();
	
	// Should generate an integer
	@Test
	public void shouldGenerateAnInteger(){
		// Given
		
		// When
		int generatedInteger = integerSampler.generate(Integer.class, new ISampleContext(){

			@Override
			public Range getSampledNumberRange() {
				return new Range(4,10);
			}
			
		});
		// Then
		Assertions.assertThat(generatedInteger).isBetween(4, 10);
	}
	
}
