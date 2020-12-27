package org.ykone.object.sampler.samplers.impl;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.context.Range;

public class StringSamplerTest {
	private StringSampler stringSampler = StringSampler.getInstance();
	
	@Test
	public void shouldGenerateString(){
		
		// Given stringSampler 
		final int StringLength = 5;
		
		// When
		String generatedString = stringSampler.generate(String.class, new ISampleContext(){

			@Override
			public int getSampledStringSize() {
				return StringLength;
			}

			@Override
			public Range getSampledNumberRange() {
				return null; // No need 
			}
		});
		
		// Then
		Assertions.assertThat(generatedString).isNotEmpty();
		Assertions.assertThat(generatedString).hasSize(StringLength);
	}
}
