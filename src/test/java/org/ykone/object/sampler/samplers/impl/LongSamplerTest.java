package org.ykone.object.sampler.samplers.impl;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.context.Range;

public class LongSamplerTest {
	
	private LongSampler longSampler= LongSampler.getInstance();
	
	// Should generate an integer
	@Test
	public void shouldGenerateAnInteger(){
		// Given
		//longSampler 
		
		// When
		long generatedLong = longSampler.generate(Long.class, new ISampleContext(){
			@Override
			public Range getSampledNumberRange() {
				return new Range(10, 150);
			}
		});
		// Then
		Assertions.assertThat(generatedLong).isBetween(4L, 150L);
	}
	
}
