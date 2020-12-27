package org.ykone.object.sampler.samplers.impl;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ykone.object.sampler.context.ISampleContext;

public class BooleanSamplerTest {
	
	private BooleanSampler booleanSampler = BooleanSampler.getInstance();
	
	@Test
	public void shouldGenerateBoolean(){
		// Given ==> booleanSampler 
		
		// When
		Boolean generatedBoolean = booleanSampler.generate(Boolean.class, new ISampleContext(){
		});
		
		// Then
		Assertions.assertThat(generatedBoolean).isNotNull();
	}
}
