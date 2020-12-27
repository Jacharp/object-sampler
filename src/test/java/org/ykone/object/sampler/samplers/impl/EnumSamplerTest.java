package org.ykone.object.sampler.samplers.impl;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.ykone.object.sampler.context.ISampleContext;
import org.ykone.object.sampler.samplers.examples.EnumToSample;

public class EnumSamplerTest {
	private EnumSampler enumSampler = EnumSampler.getInstance();
	
	@Test
	public void shouldGenerateEnum(){
		
		// Given EnumToSample
		
		// When
		EnumToSample generatedEnum = (EnumToSample)enumSampler.generate(EnumToSample.class, new ISampleContext(){});
		
		// Then
		Assertions.assertThat(generatedEnum).isNotNull();
		Assertions.assertThat(EnumToSample.values()).contains(generatedEnum);
	}
	
	@Test
	public void shouldReturnNullIfClassToSampleIsNotAnEnum(){
		
		// Given EnumToSample
		
		// When
		Enum<?> generatedEnum = enumSampler.generate(String.class, new ISampleContext(){});
		
		// Then
		Assertions.assertThat(generatedEnum).isNull();
	}
}
